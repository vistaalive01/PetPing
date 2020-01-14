package com.example.petping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PetSearchResult extends Fragment {
    private ArrayList<PetSearch> petSearchList ;
    private ArrayList<PetSearch> petSearchListS;
    private ArrayList<PetSearch> petSearchListM;
    private ArrayList<PetSearch> petSearchListL;
    private ListView listView;
    private PetListViewAdapter petAdapter;
    private Button btnS, btnM, btnL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View temp = inflater.inflate(R.layout.fragment_pet_search_result, null);

        if(getArguments() != null){
            petSearchList = (ArrayList<PetSearch>)getArguments().getSerializable("petL");
        }
        listView = temp.findViewById(R.id.listView_pet);

        petAdapter = new PetListViewAdapter(getContext(), petSearchList);
        listView.setAdapter(petAdapter);
        Log.d("PetList2", petSearchList.toString());


        btnS = temp.findViewById(R.id.adapter_sizeS);
        btnM = temp.findViewById(R.id.adapter_sizeM);
        btnL = temp.findViewById(R.id.adapter_sizeL);

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petSearchListS = new ArrayList<>();
                for(int i=0; i<petSearchList.size(); i++){
                    if(petSearchList.get(i).getSize().equals("S")){
                        PetSearch petSizeS = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl());
                        petSearchListS.add(petSizeS);
                    }
                }
                petAdapter = new PetListViewAdapter(getContext(), petSearchListS);
                listView.setAdapter(petAdapter);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petSearchListM = new ArrayList<>();
                for(int i=0; i<petSearchList.size(); i++){
                    if(petSearchList.get(i).getSize().equals("M")){
                        PetSearch petSizeM = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl());
                        petSearchListM.add(petSizeM);
                    }
                }
                petAdapter = new PetListViewAdapter(getContext(), petSearchListM);
                listView.setAdapter(petAdapter);
            }
        });

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petSearchListL = new ArrayList<>();
                for(int i=0; i<petSearchList.size(); i++){
                    if(petSearchList.get(i).getSize().equals("L")){
                        PetSearch petSizeL = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl());
                        petSearchListL.add(petSizeL);
                    }
                }
                petAdapter = new PetListViewAdapter(getContext(), petSearchListL);
                listView.setAdapter(petAdapter);
            }
        });

        return temp;
    }

}
