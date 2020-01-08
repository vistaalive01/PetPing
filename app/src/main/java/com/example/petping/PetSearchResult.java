package com.example.petping;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PetSearchResult extends Fragment {
    private ListView listView;
   // private PetSearchResultAdapter petAdapter;
    private SearchFragment searchFragment;
    private PetSearch pet ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View temp = inflater.inflate(R.layout.fragment_pet_search_result, null);

        searchFragment.searchPetResult();
//        Log.d("Color: ", pet.getSex());

//        listView = temp.findViewById(R.id.listview_pet);
//        petAdapter = new PetSearchResultAdapter(getContext(), petList);
//        listView.setAdapter(petAdapter);
        return temp;
    }

}
