package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PetInfoShelterFragment extends Fragment {
    private ArrayList<PetSearch> petInfoList;
    private ViewFlipper viewFlipper;
    private Button btnInfo, btnStory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pet_info_shelter, null);
        if(getArguments() != null){
            petInfoList = (ArrayList<PetSearch>)getArguments().getSerializable("petInfo");
        }

        viewFlipper = view.findViewById(R.id.view_flipper_shelter);
        btnInfo = view.findViewById(R.id.info);
        btnStory = view.findViewById(R.id.story);
        for (int i=0; i<petInfoList.size(); i++){
            Log.d("petInfo", petInfoList.get(i).getName());
        }

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.view_info)));
            }
        });

        btnStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(view.findViewById(R.id.story)));
            }
        });
        return view;
    }
}
