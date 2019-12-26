package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private List<String> petSearchType = new ArrayList<>();
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
        final ArrayAdapter<CharSequence> breedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.breed_array, android.R.layout.simple_spinner_item);
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
                petTypeBox(dog, cat, rabbit);




                //Log.d("Test3", dogBox.getText().toString());
            }
        });

        return temp;
    }

    private void petTypeBox(String dog, String cat, String rabbit) {
        if(dogBox.isChecked()) petSearchType.add(dog);
        else petSearchType.remove(dog);

        if(catBox.isChecked()) petSearchType.add(cat);
        else petSearchType.remove(cat);

        if(rabbitBox.isChecked()) petSearchType.add(rabbit);
        else petSearchType.remove(rabbit);
        for(int i = 0; i < petSearchType.size(); i++){
            db.collection("Pet")
                    .whereIn("Type", Arrays.asList(petSearchType.get(i)))
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("Test1", document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.d("Test2", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }


    }

}
