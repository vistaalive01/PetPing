package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class SearchFragment extends Fragment {
    private ImageButton btnDog;
    private ImageButton btnCat;



    private RadioGroup radioGroupType;
    private RadioButton dogBtn;
    private RadioButton catBtn;
    private RadioButton rabbitBtn;
    private RadioButton radioButtonType;

//    private Spinner spinColor;
    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private Button searchButton;
    private RadioGroup radioGroupSex;
    private RadioButton radioButton;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String type;
    private String sex;

    private PetSearch petSearch;
    private ArrayList<PetSearch> petList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, null);
        btnDog = view.findViewById(R.id.btn_dog);
        btnCat = view.findViewById(R.id.btn_cat);

        //Show Pet that match with each user instead of random
        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Pet")
                .whereEqualTo("Type", "สุนัข")
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
        });

        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Pet")
                        .whereEqualTo("Type", "แมว")
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
        });
//
//        //Type
//        dogBtn = temp.findViewById(R.id.rd_dog);
//        catBtn = temp.findViewById(R.id.rd_cat);
//        rabbitBtn = temp.findViewById(R.id.rd_rabbit);
//        radioGroupType = temp.findViewById(R.id.rd_type);
//
//        //Sex
//        maleBtn = temp.findViewById(R.id.rd_male);
//        femaleBtn = temp.findViewById(R.id.rd_female);
//        radioGroupSex = temp.findViewById(R.id.rd_sex);
//

//
//        searchButton = temp.findViewById(R.id.pet_search);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int radioType = radioGroupType.getCheckedRadioButtonId();
//                radioButtonType = temp.findViewById(radioType);
//                if(radioButtonType == temp.findViewById(R.id.rd_dog)){
//                    type = dogBtn.getText().toString();
//                    textS.setText("1-5 กิโลกรัม");
//                    textM.setText("5-10 กิโลกรัม");
//                    textL.setText("10 กิโลกรัมชึ้นไป");
//                    textS.setVisibility(View.VISIBLE);
//                    textM.setVisibility(View.VISIBLE);
//                    textL.setVisibility(View.VISIBLE);
//                }
//                if(radioButtonType == temp.findViewById(R.id.rd_cat)){
//                    type = catBtn.getText().toString();
//                }
//                if(radioButtonType == temp.findViewById(R.id.rd_rabbit)){
//                    type = rabbitBtn.getText().toString();
//                }
//
//                if(sizeS.isChecked()){
//                    petSearchSize.add("S");
//                    petSearchSize.add("s");
//                }
//                if(sizeM.isChecked()){
//                    petSearchSize.add("M");
//                    petSearchSize.add("m");
//                }
//                if(sizeL.isChecked()){
//                    petSearchSize.add("L");
//                    petSearchSize.add("l");
//                }
//
//                int radioSex = radioGroupSex.getCheckedRadioButtonId();
//                radioButton = temp.findViewById(radioSex);
//                if(radioButton == temp.findViewById(R.id.rd_male)){
//                    sex = maleBtn.getText().toString();
//                }
//                else if(radioButton == temp.findViewById(R.id.rd_female)){
//                    sex = femaleBtn.getText().toString();
//                }
//                searchPetResult();
//            }
//        });

        return view;
    }


//    private void searchPetResult(){
//        if(type == null){
//            Toast.makeText(getContext(),"กรุณาเลือกประเภทด้วยค่ะ",Toast.LENGTH_LONG).show();
//        }
//        //Choose every filter
//        if (type != null && sex != null && !petSearchSize.isEmpty()) {
//            db.collection("Pet")
//                .whereEqualTo("Type", this.type)
//                .whereEqualTo("Sex", this.sex)
//                .whereIn("Size", this.petSearchSize)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            setValue(task);
//                        } else {
//                            Log.d("Error", "Error getting documents: ", task.getException());
//                        }
//                    }
//
//                });
//        }
//
//        //Not Choose "Sex"
//        if (type != null && sex == null&& !petSearchSize.isEmpty()) {
//            db.collection("Pet")
//                    .whereEqualTo("Type", this.type)
//                    .whereIn("Size", this.petSearchSize)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                setValue(task);
//                            } else {
//                                Log.d("Error", "Error getting documents: ", task.getException());
//                            }
//                        }
//                    });
//        }
//
//        //Not Choose "Size"
//        if (type != null && sex != null && petSearchSize.isEmpty()) {
//            db.collection("Pet")
//                    .whereEqualTo("Type", this.type)
//                    .whereEqualTo("Sex", this.sex)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                setValue(task);
//                            } else {
//                                Log.d("Error", "Error getting documents: ", task.getException());
//                            }
//                        }
//                    });
//        }
//
//        //Not Choose "Sex" and "Size"
//        if (type != null && sex == null && petSearchSize.isEmpty()) {
//            db.collection("Pet")
//                    .whereEqualTo("Type", this.type)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                setValue(task);
//                            } else {
//                                Log.d("Error", "Error getting documents: ", task.getException());
//                            }
//                        }
//                    });
//        }
//
//    }
//
    private void setValue(Task<QuerySnapshot> task) {

        for (QueryDocumentSnapshot document : task.getResult()) {
            petSearch = new PetSearch(document.getId(), document.get("Name").toString(), document.get("Type").toString(),
                    document.get("Color").toString(), document.get("Sex").toString(), document.get("Age").toString(),
                    document.get("Breed").toString(), document.get("Size").toString(), document.get("Image").toString(),
                    document.get("Weight").toString(), document.get("Character").toString(), document.get("Marking").toString(),
                    document.get("Health").toString(), document.get("OriginalLocation").toString(), document.get("Status").toString(),
                    document.get("Story").toString());
            petList.add(petSearch);
        }

        PetSearchResult petSearchResult = new PetSearchResult();
        Bundle bundle = new Bundle();
        bundle.putSerializable("petL", petList);
        petSearchResult.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(getId(), petSearchResult);
        ft.commit();
    }

}