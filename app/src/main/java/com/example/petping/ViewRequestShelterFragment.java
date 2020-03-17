package com.example.petping;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewRequestShelterFragment extends Fragment {
    private ArrayList<HomeShelter> adoptionList = new ArrayList<>();
    private ImageView petImage, petImageQA, adoptImageQA;
    private Button btnAdopter, btnBasicQ, btnPet, btnSaveInfo;
    private ViewFlipper viewFlipper;
    private String uID, petID;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView adoptName, adoptNid, adoptDOB, adoptTel, adoptAddr, adoptJob, adoptSalary, adoptStatus;
    private TextView petName, petBreed, petAge, petSex, petColour, petMarking;
    private TextView petWeight, petSize, petCharacter, petFoundLoc;
    private TextView qOne, qTwo, qThree, qFour, qFive;
    private TextView aOne, aTwo, aThree, aFour, aFive;
    private TextView qSix, qSeven, qEight, qNine, qTen, qEleven;
    private TextView aSix, aSeven, aEight, aNine, aTen, aEleven;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view_request_shelter, null);
        if(getArguments() != null){
            adoptionList = (ArrayList<HomeShelter>)getArguments().getSerializable("homeShelter");
        }
        for(int i=0; i<adoptionList.size(); i++){
            uID = adoptionList.get(i).getuID();
            petID = adoptionList.get(i).getPetID();
//            Log.d("AdoptionList", adoptionList.get(i).getuID());
        }
        btnAdopter = view.findViewById(R.id.btn_adopter_info);
        btnBasicQ = view.findViewById(R.id.btn_basic_q);
        btnPet = view.findViewById(R.id.btn_pet_info);
        btnSaveInfo = view.findViewById(R.id.save_info);
        viewFlipper = view.findViewById(R.id.view_flipper_request);

        adoptName = view.findViewById(R.id.adopter_name);
        adoptNid = view.findViewById(R.id.adopter_nid);
        adoptDOB = view.findViewById(R.id.adopter_dob);
        adoptTel = view.findViewById(R.id.adopter_tel);
        adoptAddr = view.findViewById(R.id.adopter_address);
        adoptJob = view.findViewById(R.id.adopter_job);
        adoptSalary = view.findViewById(R.id.adopter_salary);
        adoptStatus = view.findViewById(R.id.adopter_status);

        petImage = view.findViewById(R.id.pet_image);
        petName = view.findViewById(R.id.pet_name);
        petBreed = view.findViewById(R.id.pet_breed);
        petAge = view.findViewById(R.id.pet_age);
        petSex = view.findViewById(R.id.pet_sex);
        petColour = view.findViewById(R.id.pet_colour);
        petMarking = view.findViewById(R.id.pet_marking);
        petWeight = view.findViewById(R.id.pet_weight);
        petSize = view.findViewById(R.id.pet_size);
        petCharacter = view.findViewById(R.id.pet_character);
        petFoundLoc = view.findViewById(R.id.pet_found_location);

        petImageQA = view.findViewById(R.id.pet_image_qa);
        adoptImageQA = view.findViewById(R.id.adopt_image_qa);
        qOne = view.findViewById(R.id.q_one);
        qTwo = view.findViewById(R.id.q_two);
        qThree = view.findViewById(R.id.q_three);
        qFour = view.findViewById(R.id.q_four);
        qFive = view.findViewById(R.id.q_five);
        qSix = view.findViewById(R.id.q_six);
        qSeven = view.findViewById(R.id.q_seven);
        qEight = view.findViewById(R.id.q_eight);
        qNine = view.findViewById(R.id.q_nine);
        qTen = view.findViewById(R.id.q_ten);
        qEleven = view.findViewById(R.id.q_eleven);

        aOne = view.findViewById(R.id.a_one);
        aTwo = view.findViewById(R.id.a_two);
        aThree = view.findViewById(R.id.a_three);
        aFour = view.findViewById(R.id.a_four);
        aFive = view.findViewById(R.id.a_five);
        aSix = view.findViewById(R.id.a_six);
        aSeven = view.findViewById(R.id.a_seven);
        aEight = view.findViewById(R.id.a_eight);
        aNine = view.findViewById(R.id.a_nine);
        aTen = view.findViewById(R.id.a_ten);
        aEleven = view.findViewById(R.id.a_eleven);

        btnAdopter.setTypeface(null, Typeface.BOLD);
        btnBasicQ.setTypeface(null, Typeface.NORMAL);
        btnPet.setTypeface(null, Typeface.NORMAL);

        db.collection("User")
                .document(uID)
                .collection("Information")
                .document("Information")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        Log.d("Flipper", documentSnapshot.getData().toString());
                        adoptName.setText(documentSnapshot.get("Name").toString());
                        adoptNid.setText(documentSnapshot.get("NID").toString());
                        adoptDOB.setText(documentSnapshot.get("DOB").toString());
                        adoptTel.setText(documentSnapshot.get("TelNo").toString());
                        adoptAddr.setText(documentSnapshot.get("Address").toString());
                        adoptJob.setText(documentSnapshot.get("Job").toString());
                        adoptSalary.setText(documentSnapshot.get("Salary").toString());
                    }
                });

        db.collection("RequestAdoption")
                .document(uID)
                .collection("Adoption")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Glide.with(getContext())
                                        .load(document.get("petURL"))
                                        .into(petImage);
                                Glide.with(getContext())
                                        .load(document.get("petURL"))
                                        .into(petImageQA);
                                Glide.with(getContext())
                                        .load(document.get("petURL"))
                                        .into(adoptImageQA);
                                petName.setText(document.get("petName").toString());
                                petBreed.setText(document.get("petBreed").toString());
                                petAge.setText(document.get("petAge").toString());
                                petSex.setText(document.get("petSex").toString());
                                petColour.setText(document.get("petColor").toString());
                                petMarking.setText(document.get("petMarking").toString());
                                petWeight.setText(document.get("petWeight").toString());
                                petSize.setText(document.get("petSize").toString());
                                petCharacter.setText(document.get("petCharacter").toString());
                                petFoundLoc.setText(document.get("petFoundLoc").toString());
                                adoptStatus.setText(document.get("petStatus").toString());
                            }
                        }
                    }
                });

        db.collection("Information")
                .document("BasicQ")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        qOne.setText(String.valueOf(documentSnapshot.get("one")));
                        qTwo.setText(String.valueOf(documentSnapshot.get("two")));
                        qThree.setText(String.valueOf(documentSnapshot.get("three")));
                        qFour.setText(String.valueOf(documentSnapshot.get("four")));
                        qFive.setText(String.valueOf(documentSnapshot.get("five")));

                        qSix.setText(String.valueOf(documentSnapshot.get("six")));
                        qSeven.setText(String.valueOf(documentSnapshot.get("seven")));
                        qEight.setText(String.valueOf(documentSnapshot.get("eight")));
                        qNine.setText(String.valueOf(documentSnapshot.get("nine")));
                        qTen.setText(String.valueOf(documentSnapshot.get("ten")));
                        qEleven.setText(String.valueOf(documentSnapshot.get("eleven")));
                    }
                });

        db.collection("RequestAdoption")
                .document(uID)
                .collection("BasicQ")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Answer", document.getData().toString());
                                aOne.setText(document.get("one").toString());
                                aTwo.setText(document.get("two").toString());
                                aThree.setText(document.get("three").toString());
                                aFour.setText(document.get("four").toString());
                                aFive.setText(document.get("five").toString());

                                aSix.setText(document.get("six").toString());
                                aSeven.setText(document.get("seven").toString());
                                aEight.setText(document.get("eight").toString());
                                aNine.setText(document.get("nine").toString());
                                aTen.setText(document.get("ten").toString());
                                aEleven.setText(document.get("eleven").toString());
                            }
                        }
                    }
                });


        btnAdopter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.adopter_info)));
                btnAdopter.setTypeface(null, Typeface.BOLD);
                btnBasicQ.setTypeface(null, Typeface.NORMAL);
                btnPet.setTypeface(null, Typeface.NORMAL);

//                db.collection("User")
//                        .document(uID)
//                        .collection("Information")
//                        .document("Information")
//                        .get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
////                                Log.d("Flipper", documentSnapshot.getData().toString());
//                                adoptName.setText(documentSnapshot.get("Name").toString());
//                                adoptNid.setText(documentSnapshot.get("NID").toString());
//                                adoptDOB.setText(documentSnapshot.get("DOB").toString());
//                                adoptTel.setText(documentSnapshot.get("TelNo").toString());
//                                adoptAddr.setText(documentSnapshot.get("Address").toString());
//                                adoptJob.setText(documentSnapshot.get("Job").toString());
//                                adoptSalary.setText(documentSnapshot.get("Salary").toString());
//                            }
//                        });
//                db.collection("RequestAdoption")
//                        .document(uID)
//                        .collection("Adoption")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if(task.isSuccessful()){
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                       adoptStatus.setText(document.get("petStatus").toString());
//                                    }
//                                }
//                            }
//                        });
            }
        });

        btnBasicQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.basic_q)));
                btnAdopter.setTypeface(null, Typeface.NORMAL);
                btnBasicQ.setTypeface(null, Typeface.BOLD);
                btnPet.setTypeface(null, Typeface.NORMAL);

//                db.collection("Information")
//                        .document("BasicQ")
//                        .get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                qOne.setText(String.valueOf(documentSnapshot.get("one")));
//                                qTwo.setText(String.valueOf(documentSnapshot.get("two")));
//                                qThree.setText(String.valueOf(documentSnapshot.get("three")));
//                                qFour.setText(String.valueOf(documentSnapshot.get("four")));
//                                qFive.setText(String.valueOf(documentSnapshot.get("five")));
//
//                                qSix.setText(String.valueOf(documentSnapshot.get("six")));
//                                qSeven.setText(String.valueOf(documentSnapshot.get("seven")));
//                                qEight.setText(String.valueOf(documentSnapshot.get("eight")));
//                                qNine.setText(String.valueOf(documentSnapshot.get("nine")));
//                                qTen.setText(String.valueOf(documentSnapshot.get("ten")));
//                                qEleven.setText(String.valueOf(documentSnapshot.get("eleven")));
//                            }
//                        });
//
//                db.collection("RequestAdoption")
//                        .document(uID)
//                        .collection("BasicQ")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if(task.isSuccessful()){
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        Log.d("Answer", document.getData().toString());
//                                        aOne.setText(document.get("one").toString());
//                                        aTwo.setText(document.get("two").toString());
//                                        aThree.setText(document.get("three").toString());
//                                        aFour.setText(document.get("four").toString());
//                                        aFive.setText(document.get("five").toString());
//
//                                        aSix.setText(document.get("six").toString());
//                                        aSeven.setText(document.get("seven").toString());
//                                        aEight.setText(document.get("eight").toString());
//                                        aNine.setText(document.get("nine").toString());
//                                        aTen.setText(document.get("ten").toString());
//                                        aEleven.setText(document.get("eleven").toString());
//                                    }
//                                }
//                            }
//                        });
            }
        });

        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.pet_info)));
                btnAdopter.setTypeface(null, Typeface.NORMAL);
                btnBasicQ.setTypeface(null, Typeface.NORMAL);
                btnPet.setTypeface(null, Typeface.BOLD);
//                db.collection("RequestAdoption")
//                        .document(uID)
//                        .collection("Adoption")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if(task.isSuccessful()){
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        Glide.with(getContext())
//                                                .load(document.get("petURL"))
//                                                .into(petImage);
//                                        petName.setText(document.get("petName").toString());
//                                        petBreed.setText(document.get("petBreed").toString());
//                                        petAge.setText(document.get("petAge").toString());
//                                        petSex.setText(document.get("petSex").toString());
//                                        petColour.setText(document.get("petColor").toString());
//                                        petMarking.setText(document.get("petMarking").toString());
//                                        petWeight.setText(document.get("petWeight").toString());
//                                        petSize.setText(document.get("petSize").toString());
//                                        petCharacter.setText(document.get("petCharacter").toString());
//                                        petFoundLoc.setText(document.get("petFoundLoc").toString());
//                                    }
//                                }
//                            }
//                        });
            }
        });

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}