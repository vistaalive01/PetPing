package com.example.petping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ManagePetInfoShelterFragment extends Fragment {
    private ArrayList<PetSearch> petList = new ArrayList<>();
    private ManagePetInfoShelterAdapter adapter;
    private ListView listView;
    private PetSearch petSearch;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btnAddPet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_pet_info_shelter, null);
        listView = view.findViewById(R.id.listView_pet_info);
        btnAddPet = view.findViewById(R.id.btn_add_pet);
        db.collection("Pet")
                .orderBy("Type")
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

        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopUpShelterAddPet.class);
                startActivity(intent);
            }
        });

        return view;
    }

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
        adapter = new ManagePetInfoShelterAdapter(getContext(), petList);
        listView.setAdapter(adapter);
    }
}
