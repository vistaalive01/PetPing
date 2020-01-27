package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatusFragment extends Fragment {
    private ArrayList<PetSearch> petProfileList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_status, null);
        if(getArguments() != null){
            petProfileList = (ArrayList<PetSearch>)getArguments().getSerializable("petProfile");
        }
        Log.d("Status", petProfileList.toString());
        return view;
    }
}
