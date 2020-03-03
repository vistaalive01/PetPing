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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PetInfoShelterFragment extends Fragment {
    private ArrayList<PetSearch> petInfoList;
    private ViewFlipper viewFlipper;
    private Button btnInfo, btnStory;
    private ImageView image;
    private TextView name, sex, breed, age, colour, marking, weight;
    private  TextView size, character, foundLoc, status, story;
    private Button btnEditInfo, btnDeleteInfo;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int i;
    private ArrayList<PetSearch> petItem = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pet_info_shelter, null);
        if(getArguments() != null){
            petInfoList = (ArrayList<PetSearch>)getArguments().getSerializable("petInfo");
        }

        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        sex = view.findViewById(R.id.sex);
        breed = view.findViewById(R.id.breed);
        colour = view.findViewById(R.id.colour);
        age = view.findViewById(R.id.age);
        marking = view.findViewById(R.id.marking);
        weight = view.findViewById(R.id.weight);
        size = view.findViewById(R.id.size);
        character = view.findViewById(R.id.character);
        foundLoc = view.findViewById(R.id.found_location);
        status = view.findViewById(R.id.status);
        story = view.findViewById(R.id.story);

        btnEditInfo = view.findViewById(R.id.edit_info);
        btnDeleteInfo = view.findViewById(R.id.delete_info);

        viewFlipper = view.findViewById(R.id.view_flipper_shelter);
        btnInfo = view.findViewById(R.id.btn_info);
        btnStory = view.findViewById(R.id.btn_story);

        btnInfo.setTypeface(null, Typeface.BOLD);
        btnStory.setTypeface(null, Typeface.NORMAL);


        for (i = 0; i < petInfoList.size(); i++) {

            Glide.with(getContext())
                    .load(petInfoList.get(i).getUrl())
                    .into(image);
            name.setText(petInfoList.get(i).getName());
            sex.setText(petInfoList.get(i).getSex());
            breed.setText(petInfoList.get(i).getBreed());
            colour.setText(petInfoList.get(i).getColour());
            age.setText(petInfoList.get(i).getAge());
            marking.setText(petInfoList.get(i).getMarking());
            weight.setText(petInfoList.get(i).getWeight());
            size.setText(petInfoList.get(i).getSize());
            character.setText(petInfoList.get(i).getCharacter());
            foundLoc.setText(petInfoList.get(i).getFoundLoc());
            status.setText(petInfoList.get(i).getStatus());
        }
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.view_info)));
                btnInfo.setTypeface(null, Typeface.BOLD);
                btnStory.setTypeface(null, Typeface.NORMAL);
                for (i = 0; i < petInfoList.size(); i++) {
                    db.collection("Pet").document(petInfoList.get(i).getID())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Glide.with(getContext())
                                            .load(petInfoList.get(i).getUrl())
                                            .into(image);
                                    name.setText(petInfoList.get(i).getName());
                                    sex.setText(petInfoList.get(i).getSex());
                                    breed.setText(petInfoList.get(i).getBreed());
                                    colour.setText(petInfoList.get(i).getColour());
                                    age.setText(petInfoList.get(i).getAge());
                                    marking.setText(petInfoList.get(i).getMarking());
                                    weight.setText(petInfoList.get(i).getWeight());
                                    size.setText(petInfoList.get(i).getSize());
                                    character.setText(petInfoList.get(i).getCharacter());
                                    foundLoc.setText(petInfoList.get(i).getFoundLoc());
                                    status.setText(petInfoList.get(i).getStatus());
                                }
                            });
                }
            }
        });

        btnStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.story)));
                btnStory.setTypeface(null, Typeface.BOLD);
                btnInfo.setTypeface(null, Typeface.NORMAL);
                for (i = 0; i < petInfoList.size(); i++) {
                    db.collection("Pet").document(petInfoList.get(i).getID())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    story.setText(petInfoList.get(i).getStory());
                                }
                            });
                }
            }
        });

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditPetInfoShelterFragment petInfoShelterFragment = new  EditPetInfoShelterFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("petEditInfo", petInfoList);
                petInfoShelterFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(),  petInfoShelterFragment);
                ft.commit();
            }
        });
        btnDeleteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
