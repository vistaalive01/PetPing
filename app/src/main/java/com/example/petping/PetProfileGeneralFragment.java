package com.example.petping;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PetProfileGeneralFragment extends Fragment {
    private ArrayList<PetSearch> petProfileList;
    private ImageView imageView, imageSex;
    private TextView infoName, infoAge, infoBreed;
    private TextView infoColor, infoSize, infoMarking, infoChar;
    private TextView infoWeight, infoFoundLoc, infoStatus;
    private ViewFlipper viewFlipper;
    private Button btnGeneral, btnStory, btnShelter;
    private Button btnAdopt;
    private ArrayList<PetSearch> petItem;

    private ToggleButton toggleButtonFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pet_profile_general, null);
        if(getArguments() != null){
            petProfileList = (ArrayList<PetSearch>)getArguments().getSerializable("petProfile");
        }

        viewFlipper = view.findViewById(R.id.view_flipper_info);
        btnGeneral = view.findViewById(R.id.button_general);
        btnStory = view.findViewById(R.id.button_story);
        btnShelter = view.findViewById(R.id.button_shelter);
        btnAdopt = view.findViewById(R.id.btn_adopt);

        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewFlipper.showPrevious();
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.scrollView_pet_general)));
            }
        });

        btnStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewFlipper.showNext();
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.scrollView_pet_story)));
            }
        });

        btnShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewFlipper.showNext();
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.scrollView_pet_shelter)));
            }
        });

        imageView = view.findViewById(R.id.img_pet_profile);
        infoName = view.findViewById(R.id.info_name);
        infoAge = view.findViewById(R.id.info_age);
        infoBreed = view.findViewById(R.id.info_breed);
        infoColor = view.findViewById(R.id.info_colour);
        infoSize = view.findViewById(R.id.info_size);
        infoMarking = view.findViewById(R.id.info_marking);
        infoChar = view.findViewById(R.id.info_character);
        infoWeight = view.findViewById(R.id.info_weight);
        infoFoundLoc = view.findViewById(R.id.info_location);
        infoStatus = view.findViewById(R.id.info_status);
        imageSex = view.findViewById(R.id.img_info_sex);

        petItem = new ArrayList<>();
        for(int i=0; i<petProfileList.size(); i++){
            Glide.with(getContext())
                    .load(petProfileList.get(i).getUrl())
                    .into(imageView);
            infoName.setText(petProfileList.get(i).getName());
            infoAge.setText(petProfileList.get(i).getAge());
            infoBreed.setText(petProfileList.get(i).getBreed());
            infoColor.setText(petProfileList.get(i).getColour());
            infoSize.setText(petProfileList.get(i).getSize());
            infoMarking.setText(petProfileList.get(i).getMarking());
            infoChar.setText(petProfileList.get(i).getCharacter());
            infoWeight.setText(petProfileList.get(i).getWeight());
            infoFoundLoc.setText(petProfileList.get(i).getFoundLoc());
            infoStatus.setText(petProfileList.get(i).getStatus());
            if(petProfileList.get(i).getSex().equals("ผู้")){
                imageSex.setImageResource(R.drawable.sex_male);
            }
            else {
                imageSex.setImageResource(R.drawable.sex_female);
            }

            toggleButtonFav = view.findViewById(R.id.toggle_favorite);
            toggleButtonFav.setButtonDrawable(R.drawable.ic_favorite_border_black_24dp);
            toggleButtonFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked == true){
                        toggleButtonFav.setChecked(true);
                        toggleButtonFav.setButtonDrawable(R.drawable.ic_favorite_red_24dp);
                        isStateSaved();
                    } else if (isChecked == false){
                        toggleButtonFav.setChecked(false);
                        toggleButtonFav.setButtonDrawable(R.drawable.ic_favorite_border_black_24dp);
                        isStateSaved();
                    }
                }
            });

            PetSearch petProfile = new PetSearch(petProfileList.get(i).getID(), petProfileList.get(i).getName(),
                    petProfileList.get(i).getType(), petProfileList.get(i).getColour(), petProfileList.get(i).getSex(),
                    petProfileList.get(i).getAge(), petProfileList.get(i).getBreed(), petProfileList.get(i).getSize(), petProfileList.get(i).getUrl(),
                    petProfileList.get(i).getWeight(), petProfileList.get(i).getCharacter(), petProfileList.get(i).getMarking(),
                    petProfileList.get(i).getHealth(), petProfileList.get(i).getFoundLoc(), petProfileList.get(i).getStatus(),
                    petProfileList.get(i).getStory());
            petItem.add(petProfile);
        }

        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdoptionRegulationFragment adoptionRegulation = new AdoptionRegulationFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("petProfile", petItem);

                adoptionRegulation.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), adoptionRegulation);
                ft.commit();


//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(getId(), new AdoptionRegulationFragment());
//                ft.commit();
            }
        });

        return view;
    }


}
