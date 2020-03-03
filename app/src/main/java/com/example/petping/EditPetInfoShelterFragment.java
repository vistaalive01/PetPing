package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditPetInfoShelterFragment extends Fragment {
    private ArrayList<PetSearch> petInfoList;
    private EditText name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_pet_info_shelter, null);

        if(getArguments() != null){
            petInfoList = (ArrayList<PetSearch>)getArguments().getSerializable("petEditInfo");
        }

        name = view.findViewById(R.id.name);
        for (int i=0; i<petInfoList.size(); i++){
            name.setText(petInfoList.get(i).getName());
        }

        return view;
    }
}
