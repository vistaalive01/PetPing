package com.example.petping;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class SearchFragment extends Fragment {

    private RadioGroup radioGroupType;
    private RadioButton dogBtn;
    private RadioButton catBtn;
    private RadioButton rabbitBtn;
    private RadioButton radioButtonType;

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

    private String type;
    private String color;
    private String sex;
    private int i;
    private List<String> searchResult = new ArrayList<>();
    private ArrayList<String> petSearchAge = new ArrayList<>();


    private String petID;
    private String petName;
    private String petAge;
    private String petColor;
    private String petSex;
    private String petBreed;
    private String petType;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View temp = inflater.inflate(R.layout.fragment_search, null);

        //Type
        dogBtn = temp.findViewById(R.id.rd_dog);
        catBtn = temp.findViewById(R.id.rd_cat);
        rabbitBtn = temp.findViewById(R.id.rd_rabbit);
        radioGroupType = temp.findViewById(R.id.rd_type);

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


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Type
                int radioType = radioGroupType.getCheckedRadioButtonId();
                radioButtonType = temp.findViewById(radioType);
                if(radioButtonType == temp.findViewById(R.id.rd_dog)){
                    type = dogBtn.getText().toString();
                }
                if(radioButtonType == temp.findViewById(R.id.rd_cat)){
                    type = catBtn.getText().toString();
                }
                if(radioButtonType == temp.findViewById(R.id.rd_rabbit)){
                    type = rabbitBtn.getText().toString();
                }

                petColorChoose();
                petAgeChoose();

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

    private void petAgeChoose() {
        if (ageFivetoTen.isChecked()) {
            petSearchAge.add("6 ปี");
            petSearchAge.add("7 ปี");
            petSearchAge.add("8 ปี");
            petSearchAge.add("9 ปี");
            petSearchAge.add("10 ปี");
        }
    }




    private void searchPetResult(){
        //Choose every filter
        if (!petSearchAge.isEmpty() && type != null && !color.equals("เลือกสี") && sex != null) {
            for(i = 0; i < petSearchAge.size(); i++){
                db.collection("Pet")
                    .whereEqualTo("Color", color)
                    .whereEqualTo("Sex", sex)
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Age", petSearchAge.get(i))
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                setValue(task);
                            } else {
                                Log.d("Error", "Error getting documents: ", task.getException());
                            }
                        }

                    });
            }
        }

        //Not Choose "Color"
        if (type != null && color.equals("เลือกสี") && sex != null && !petSearchAge.isEmpty()) {
            for(i = 0; i < petSearchAge.size(); i++){
                db.collection("Pet")
                        .whereEqualTo("Sex", sex)
                        .whereEqualTo("Type", type)
                        .whereEqualTo("Age", petSearchAge.get(i))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    setValue(task);
                                } else {
                                    Log.d("Error", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        }
        //Not Choose "Sex"
        if (type != null && !color.equals("เลือกสี") && sex == null&& !petSearchAge.isEmpty()) {
            for(i = 0; i < petSearchAge.size(); i++){
                db.collection("Pet")
                        .whereEqualTo("Color", color)
                        .whereEqualTo("Type", type)
                        .whereEqualTo("Age", petSearchAge.get(i))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    setValue(task);
                                } else {
                                    Log.d("Error", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        }

        //Not Choose "Color" and "Sex"
        if (type != null && color.equals("เลือกสี") && sex == null && !petSearchAge.isEmpty()) {
            for(i = 0; i < petSearchAge.size(); i++){
                db.collection("Pet")
                        .whereEqualTo("Type", type)
                        .whereEqualTo("Age", petSearchAge.get(i))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    setValue(task);
                                } else {
                                    Log.d("Error", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        }
    }

    private void setValue(Task<QuerySnapshot> task) {
        ArrayList<PetSearch> petList = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            PetSearch petSearch = new PetSearch(document.getId(), document.get("Name").toString(), document.get("Type").toString(),
                    document.get("Color").toString(), document.get("Sex").toString(), document.get("Age").toString(), document.get("Breed").toString());
            petList.add(petSearch);
            Log.d("DataTest", String.valueOf(document.getData()));
        }
        Log.d("PetList", petList.toString());

        PetSearchResult petSearchResult = new PetSearchResult();
        Bundle bundle = new Bundle();
        bundle.putSerializable("petL", petList);
        petSearchResult.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(getId(), petSearchResult).commit();
    }

}