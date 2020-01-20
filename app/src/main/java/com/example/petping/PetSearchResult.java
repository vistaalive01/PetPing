package com.example.petping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class PetSearchResult extends Fragment implements DialogFiltering.filterSelected{
    private ArrayList<PetSearch> petSearchList;
    private ArrayList<PetSearch> petSearchFilter;
    private ListView listView;
    private PetListViewAdapter petAdapter;
    private Button btnS, btnM, btnL, btnTotal;
    private ArrayList<PetSearch> petItem;
    private Button btnFiltering;

    public TextView resultFound;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View temp = inflater.inflate(R.layout.fragment_pet_search_result, null);

        if(getArguments() != null){
            petSearchList = (ArrayList<PetSearch>)getArguments().getSerializable("petL");
        }

        resultFound = temp.findViewById(R.id.result_found);
        int count = 0;
        for(int i=0; i<petSearchList.size(); i++){
            count++;
        }
        resultFound.setText(String.valueOf(count));

        //Show pet list after searching
        listView = (ListView) temp.findViewById(R.id.listView_pet);
        petAdapter = new PetListViewAdapter(getContext(), petSearchList);
        listView.setAdapter(petAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               petItem = new ArrayList<>();
                PetProfileGeneralFragment petProfile = new PetProfileGeneralFragment();
                Bundle bundle = new Bundle();
                petItem.add(petSearchList.get(position));
                bundle.putSerializable("petProfile", petItem);

                petProfile.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), petProfile);
                ft.commit();
            }
        });

        //Filtering part
        btnFiltering = temp.findViewById(R.id.adapter_filter);
        btnFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                petFilterList = new ArrayList<>();
                DialogFiltering dialog = new DialogFiltering();
                dialog.setTargetFragment(PetSearchResult.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });
//
//        btnS = temp.findViewById(R.id.adapter_sizeS);
//        btnM = temp.findViewById(R.id.adapter_sizeM);
//        btnL = temp.findViewById(R.id.adapter_sizeL);
//        btnTotal = temp.findViewById(R.id.adapter_size_total);
//        btnTotal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                petAdapter = new PetListViewAdapter(getContext(), petSearchList);
//                listView.setAdapter(petAdapter);
//            }
//        });
//
//        btnS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                petSearchListS = new ArrayList<>();
//                for(int i=0; i<petSearchList.size(); i++){
//                    if(petSearchList.get(i).getSize().equals("S")){
//                        PetSearch petSizeS = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
//                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
//                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl(),
//                                petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
//                                petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
//                                petSearchList.get(i).getStory());
//                        petSearchListS.add(petSizeS);
//                    }
//                }
//                petAdapter = new PetListViewAdapter(getContext(), petSearchListS);
//                listView.setAdapter(petAdapter);
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        petItem = new ArrayList<>();
//                        PetProfileGeneralFragment petProfile = new PetProfileGeneralFragment();
//                        Bundle bundle = new Bundle();
//                        petItem.add(petSearchListS.get(position));
//                        bundle.putSerializable("petProfile", petItem);
//
//                        petProfile.setArguments(bundle);
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(getId(), petProfile);
//                        ft.commit();
//                    }
//                });
//            }
//        });
//
//        btnM.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                petSearchListM = new ArrayList<>();
//                for(int i=0; i<petSearchList.size(); i++){
//                    if(petSearchList.get(i).getSize().equals("M")){
//                        PetSearch petSizeM = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
//                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
//                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl(),
//                                petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
//                                petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
//                                petSearchList.get(i).getStory());
//                        petSearchListM.add(petSizeM);
//                    }
//                }
//                petAdapter = new PetListViewAdapter(getContext(), petSearchListM);
//                listView.setAdapter(petAdapter);
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        petItem = new ArrayList<>();
//                        PetProfileGeneralFragment petProfile = new PetProfileGeneralFragment();
//                        Bundle bundle = new Bundle();
//                        petItem.add(petSearchListM.get(position));
//                        bundle.putSerializable("petProfile", petItem);
//
//                        petProfile.setArguments(bundle);
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(getId(), petProfile);
//                        ft.commit();
//                    }
//                });
//
//            }
//        });
//
//        btnL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                petSearchListL = new ArrayList<>();
//                for(int i=0; i<petSearchList.size(); i++){
//                    if(petSearchList.get(i).getSize().equals("L")){
//                        PetSearch petSizeL = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
//                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
//                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl(),
//                                petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
//                                petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
//                                petSearchList.get(i).getStory());
//                        petSearchListL.add(petSizeL);
//                    }
//                }
//
//
//                petAdapter = new PetListViewAdapter(getContext(), petSearchListL);
//                listView.setAdapter(petAdapter);
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        petItem = new ArrayList<>();
//                        PetProfileGeneralFragment petProfile = new PetProfileGeneralFragment();
//                        Bundle bundle = new Bundle();
//                        petItem.add(petSearchListL.get(position));
//                        bundle.putSerializable("petProfile", petItem);
//
//                        petProfile.setArguments(bundle);
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(getId(), petProfile);
//                        ft.commit();
//                    }
//                });
//            }
//        });
        return temp;
    }

    @Override
    public void sendFiltering(String input, String color, ArrayList<String> petSearchAge) {
        petSearchFilter = new ArrayList<>();
        if(color != null && !petSearchAge.isEmpty()){
            int count = 0;
            for(int i=0; i<petSearchList.size(); i++){
                for(int j=0; j<petSearchAge.size(); j++){
                    if(petSearchList.get(i).getColour().equals(color) && petSearchList.get(i).getAge().equals(petSearchAge.get(j))){
                        PetSearch petFilter = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(),petSearchList.get(i).getUrl(),
                                petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
                                petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
                                petSearchList.get(i).getStory());
                        petSearchFilter.add(petFilter);
                        count++;

                    }
                }
            }
            resultFound.setText(String.valueOf(count));
            petAdapter = new PetListViewAdapter(getContext(), petSearchFilter);
            listView.setAdapter(petAdapter);
        }
        if(color.equals("ไม่ระบุ") && !petSearchAge.isEmpty()) {
            int count = 0;
            for (int i = 0; i < petSearchList.size(); i++) {
                for (int j = 0; j < petSearchAge.size(); j++) {
                    if (petSearchList.get(i).getAge().equals(petSearchAge.get(j))) {
                        PetSearch petFilter = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                                petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                                petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(), petSearchList.get(i).getUrl(),
                                petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
                                petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
                                petSearchList.get(i).getStory());
                        petSearchFilter.add(petFilter);
                        count++;
                    }
                }
            }
            resultFound.setText(String.valueOf(count));
            petAdapter = new PetListViewAdapter(getContext(), petSearchFilter);
            listView.setAdapter(petAdapter);
        }
        if(color != null && petSearchAge.isEmpty()) {
            int count = 0;
            for (int i = 0; i < petSearchList.size(); i++) {
                if (petSearchList.get(i).getColour().equals(color)) {
                    PetSearch petFilter = new PetSearch(petSearchList.get(i).getID(), petSearchList.get(i).getName(),
                            petSearchList.get(i).getType(), petSearchList.get(i).getColour(), petSearchList.get(i).getSex(),
                            petSearchList.get(i).getAge(), petSearchList.get(i).getBreed(), petSearchList.get(i).getSize(), petSearchList.get(i).getUrl(),
                            petSearchList.get(i).getWeight(), petSearchList.get(i).getCharacter(), petSearchList.get(i).getMarking(),
                            petSearchList.get(i).getHealth(), petSearchList.get(i).getFoundLoc(), petSearchList.get(i).getStatus(),
                            petSearchList.get(i).getStory());
                    petSearchFilter.add(petFilter);
                    count++;
                }
            }
            resultFound.setText(String.valueOf(count));
            petAdapter = new PetListViewAdapter(getContext(), petSearchFilter);
            listView.setAdapter(petAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                petItem = new ArrayList<>();
                PetProfileGeneralFragment petProfile = new PetProfileGeneralFragment();
                Bundle bundle = new Bundle();
                petItem.add(petSearchFilter.get(position));
                bundle.putSerializable("petProfile", petItem);

                petProfile.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), petProfile);
                ft.commit();
            }
        });
    }

}
