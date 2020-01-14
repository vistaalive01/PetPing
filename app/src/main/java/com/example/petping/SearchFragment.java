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
import android.widget.Toast;

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
    private ArrayList<String> petSearchAge = new ArrayList<>();
    private ArrayList<String> ageSearch = new ArrayList<>();
    private PetSearch petSearch;


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

                color = spinColor.getSelectedItem().toString();
                petAgeChoose();

                int radioSex = radioGroupSex.getCheckedRadioButtonId();
                radioButton = temp.findViewById(radioSex);
                if(radioButton == temp.findViewById(R.id.rd_male)){
                    sex = maleBtn.getText().toString();
                }
                if(radioButton == temp.findViewById(R.id.rd_female)){
                    sex = femaleBtn.getText().toString();
                }

                searchPetResult();
            }
        });

        return temp;
    }


    private void petAgeChoose() {
        if (ageLeastOne.isChecked()) {
            petSearchAge.add("0 ปี");
        }
        if (ageOnetoFive.isChecked()) {
            petSearchAge.add("1 ปี");
            petSearchAge.add("2 ปี");
            petSearchAge.add("3 ปี");
            petSearchAge.add("4 ปี");
            petSearchAge.add("5 ปี");
        }
        if (ageFivetoTen.isChecked()) {
            petSearchAge.add("6 ปี");
            petSearchAge.add("7 ปี");
            petSearchAge.add("8 ปี");
            petSearchAge.add("9 ปี");
            petSearchAge.add("10 ปี");
        }
        if (ageTentoFiveteen.isChecked()) {
            petSearchAge.add("11 ปี");
            petSearchAge.add("12 ปี");
            petSearchAge.add("13 ปี");
            petSearchAge.add("14 ปี");
            petSearchAge.add("15 ปี");
        }
    }


    private void searchPetResult(){
        if(type == null){
            Toast.makeText(getContext(),"กรุณาเลือกประเภทด้วยค่ะ",Toast.LENGTH_LONG).show();
        }
        //Choose every filter
        if (!petSearchAge.isEmpty() && type != null && !color.equals("เลือกสี") && sex != null) {
            db.collection("Pet")
                .whereEqualTo("Type", type)
                .whereEqualTo("Color", color)
                .whereEqualTo("Sex", sex)
                .whereIn("Age", petSearchAge)
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

        //Not Choose "Color"
        if (type != null && color.equals("เลือกสี") && sex != null && !petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Sex", sex)
                    .whereIn("Age", petSearchAge)
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
        //Not Choose "Sex"
        if (type != null && !color.equals("เลือกสี") && sex == null&& !petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Color", color)
                    .whereIn("Age", petSearchAge)
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

        //Not Choose "Age"
        if (type != null && !color.equals("เลือกสี") && sex != null && petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Color", color)
                    .whereEqualTo("Sex", sex)
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

        //Not Choose "Color" and "Sex"
        if (type != null && color.equals("เลือกสี") && sex == null && !petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereIn("Age", petSearchAge)
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

        //Not Choose "Color" and "Age"
        if (type != null && color.equals("เลือกสี") && sex != null && petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Sex", sex)
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

        //Not Choose "Sex" and "Age"
        if (type != null && !color.equals("เลือกสี") && sex == null && petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
                    .whereEqualTo("Color", color)
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

        //Not Choose "Color", "Sex" and "Age"
        if (type != null && color.equals("เลือกสี") && sex == null && petSearchAge.isEmpty()) {
            db.collection("Pet")
                    .whereEqualTo("Type", type)
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

    private void setValue(Task<QuerySnapshot> task) {
        ArrayList<PetSearch> petList = new ArrayList<>();

        for (QueryDocumentSnapshot document : task.getResult()) {
            petSearch = new PetSearch(document.getId(), document.get("Name").toString(), document.get("Type").toString(),
                    document.get("Color").toString(), document.get("Sex").toString(), document.get("Age").toString(),
                    document.get("Breed").toString(), document.get("Size").toString(), document.get("Image").toString());
            petList.add(petSearch);
        }

        Log.d("Size ", String.valueOf(petList.size()));
        PetSearchResult petSearchResult = new PetSearchResult();
        Bundle bundle = new Bundle();
        bundle.putSerializable("petL", petList);
        petSearchResult.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(getId(), petSearchResult);
        ft.commit();

    }

}