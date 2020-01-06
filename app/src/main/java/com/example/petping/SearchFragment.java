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
import android.widget.RadioGroup;
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
    private RadioGroup radioGroupSex;
    private RadioButton radioButton;
    private CheckBox ageLeastOne;
    private CheckBox ageOnetoFive;
    private CheckBox ageFivetoTen;
    private CheckBox ageTentoFiveteen;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String breed;
    private String color;
    private String sex;
    private int i;
    private List<String> petSearchType;
    private List<String> searchResult = new ArrayList<>();
    private PetSearch petSearch;
    private ArrayAdapter<CharSequence> breedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View temp = inflater.inflate(R.layout.fragment_search, null);

        //Type
        dogBox = temp.findViewById(R.id.cb_dog);
        catBox = temp.findViewById(R.id.cb_cat);
        rabbitBox = temp.findViewById(R.id.cb_rabbit);
        otherBox = temp.findViewById(R.id.cb_other);

        //Sex
        maleBtn = temp.findViewById(R.id.rd_male);
        femaleBtn = temp.findViewById(R.id.rd_female);
        radioGroupSex = temp.findViewById(R.id.rd_sex);

        //Color
        spinColor = temp.findViewById(R.id.color_spin);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.color_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinColor.setAdapter(colorAdapter);
        searchButton = temp.findViewById(R.id.pet_search);

        //Age
        ageLeastOne = temp.findViewById(R.id.cb_age_least1y);
        ageOnetoFive = temp.findViewById(R.id.cb_age_1to5y);
        ageFivetoTen = temp.findViewById(R.id.cb_age_5to10y);
        ageTentoFiveteen = temp.findViewById(R.id.cb_age_10to15y);

//        spinBreed = temp.findViewById(R.id.breed_spin);
//        breedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.breed_array, android.R.layout.simple_spinner_item);
//        breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinBreed.setAdapter(breedAdapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dog = dogBox.getText().toString();
                String cat = catBox.getText().toString();
                String rabbit = rabbitBox.getText().toString();

                petTypeChoose(dog, cat, rabbit);
//                petBreedChoose();
                petColorChoose();

                int radioSex = radioGroupSex.getCheckedRadioButtonId();
                radioButton = temp.findViewById(radioSex);
                if(radioButton == temp.findViewById(R.id.rd_male)){
                    sex = maleBtn.getText().toString();
                    searchResult.add(sex);
                }
                if(radioButton == temp.findViewById(R.id.rd_female)){
                    sex = femaleBtn.getText().toString();
                    searchResult.add(sex);
                }
                searchPetResult();
                searchResult.clear();
            }
        });

        return temp;
    }


    private void petColorChoose() {
        color = spinColor.getSelectedItem().toString();
        if(color != "อื่นๆ"){
            searchResult.add(color);
        }
        else searchResult.remove(color);
    }


//    private void petBreedChoose() {
//
//        breed = spinBreed.getSelectedItem().toString();
//        if(breed != "อื่นๆ"){
//            searchResult.add(breed);
//        }
//        else searchResult.remove(breed);
//
//    }

    private void petTypeChoose(String dog, String cat, String rabbit) {
        petSearchType = new ArrayList<>();
        if(dogBox.isChecked()){
            petSearchType.add(dog);
            searchResult.add(dog);
        }

        if(catBox.isChecked()){
            petSearchType.add(cat);
            searchResult.add(cat);
        }

        if(rabbitBox.isChecked()) {
            petSearchType.add(rabbit);
            searchResult.add(rabbit);
        }

    }

    private void searchPetResult(){
        //Choose every filter
        if (!petSearchType.isEmpty() && !color.equals("เลือกสี") && sex != null) {
            for(i = 0; i < petSearchType.size(); i++){
                db.collection("Pet")
                        .whereEqualTo("Color", color)
                        .whereEqualTo("Sex", sex)
                        .whereIn("Type", Arrays.asList(petSearchType.get(i)))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
//                                    petSearch = new PetSearch(petSearchType.get(i), color, sex,"age");
//                                    Log.d("DataTest2", petSearch.getType());

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("DataTest", document.getId()+ " => " + document.getString(sex));
                                    }
                                } else {
                                    Log.d("Error", "Error getting documents: ", task.getException());
                                }
                            }
                        });

            }

        }
        //Choose Type
        if(!petSearchType.isEmpty() ){
            //Not Choose Colour
            if(color.equals("เลือกสี") && sex != null){
                for(int i = 0; i < searchResult.size(); i++){
                    db.collection("Pet")
                            .whereEqualTo("Sex", sex)
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
            //Not Choose Sex
            if(!color.equals("เลือกสี") && sex == null){
                for(int i = 0; i < searchResult.size(); i++){
                    db.collection("Pet")
                            .whereEqualTo("Color", color)
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

    }

}
