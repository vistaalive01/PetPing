package com.example.petping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Pet pet = new Pet("Pccky", "Male", "20",
                "Thai", "Nice for kids", "เจ้าของเอามาปล่อยในตลาด" , "Good", "1/5/2019");
        db.collection("Pet").document("test").set(pet);
       /* Map<String,Object> user = new HashMap<>();
        user.put("one", "beem");
        user.put("two", "cat");
        user.put("three", "data");

        db.collection("users").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("myTag", "This is my message");
                    }
                });*/
        setContentView(R.layout.activity_main);
    }
}
