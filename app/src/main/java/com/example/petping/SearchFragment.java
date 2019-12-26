package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private CheckBox dogBox;
    private CheckBox catBox;
    private CheckBox rabbitBox;
    private CheckBox otherBox;
    private Spinner spinBreed;
    private Spinner spinColor;
    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private Button searchButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String breed;
    private List<String> petSearchType = new ArrayList<>();
    private List<String> breedSearchType = new ArrayList<>();
    private List<String> searchResult = new ArrayList<>();
    private ArrayAdapter<CharSequence> breedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View temp = inflater.inflate(R.layout.fragment_search, null);

        dogBox = temp.findViewById(R.id.cb_dog);
        catBox = temp.findViewById(R.id.cb_cat);
        rabbitBox = temp.findViewById(R.id.cb_rabbit);
        otherBox = temp.findViewById(R.id.cb_other);

        maleBtn = temp.findViewById(R.id.rd_male);
        femaleBtn = temp.findViewById(R.id.rd_female);

        searchButton = temp.findViewById(R.id.pet_search);

        spinBreed = temp.findViewById(R.id.breed_spin);
        breedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.breed_array, android.R.layout.simple_spinner_item);
        breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBreed.setAdapter(breedAdapter);

        spinColor = temp.findViewById(R.id.color_spin);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.color_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinColor.setAdapter(colorAdapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dog = dogBox.getText().toString();
                String cat = catBox.getText().toString();
                String rabbit = rabbitBox.getText().toString();
                petTypeChoose(dog, cat, rabbit);

                String breed1 = breedAdapter.getItem(0).toString();
                String breed2 = breedAdapter.getItem(1).toString();
                String breed3 = breedAdapter.getItem(2).toString();
                petBreedChoose();
                searchPetResult();
                searchResult.clear();
            }
        });

        return temp;
    }



   private void petBreedChoose() {
        spinBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                breed = breedAdapter.getItem(position).toString();
                breedSearchType.add(breed);
                searchResult.add(breed);
                Log.d("Breed", breed);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void petTypeChoose(String dog, String cat, String rabbit) {
        if(dogBox.isChecked()){
            petSearchType.add(dog);
            searchResult.add(dog);
        }
        else{
            petSearchType.remove(dog);
        }

        if(catBox.isChecked()){
            petSearchType.add(cat);
            searchResult.add(cat);
        }
        else{
            petSearchType.remove(cat);
        }

        if(rabbitBox.isChecked()) {
            petSearchType.add(rabbit);
            searchResult.add(rabbit);
        }
        else {
            petSearchType.remove(rabbit);
        }

    }

    private void searchPetResult(){

        for(int i = 0; i < searchResult.size(); i++){
            db.collection("Pet")
                    .whereEqualTo("Breed","ไทย")
                    .whereIn("Type", Arrays.asList(searchResult.get(i)))
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("DataTest", document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.d("Error", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }

    }

}
