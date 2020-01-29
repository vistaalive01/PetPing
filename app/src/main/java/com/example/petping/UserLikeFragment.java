package com.example.petping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserLikeFragment extends Fragment {

    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayList<PetSearch> petFavList = new ArrayList<>();
    private UserLikeAdapter likeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_like, container, false);
        listView = view.findViewById(R.id.listView_like);

        db.collection("User")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Like")
                .get()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                PetSearch petSearch = new PetSearch(document.get("petID").toString(), document.get("petName").toString(), document.get("petType").toString(),
                                        document.get("petColor").toString(), document.get("petSex").toString(), document.get("petAge").toString(),
                                        document.get("petBreed").toString(), document.get("petSize").toString(), document.get("petURL").toString(),
                                        document.get("petWeight").toString(), document.get("petCharacter").toString(), document.get("petMarking").toString(),
                                        document.get("petHealth").toString(), document.get("petFoundLoc").toString(), document.get("petStatus").toString(),
                                        document.get("petStory").toString());
                                petFavList.add(petSearch);
//                                Log.d("StatusList", document.get("petName").toString());
                            }
                            likeAdapter = new UserLikeAdapter(getContext(), petFavList);
                            listView.setAdapter(likeAdapter);
                        }
                        else { Log.d("Error", "Error getting documents: ", task.getException()); }
                    }
                });

        return view;
    }
}