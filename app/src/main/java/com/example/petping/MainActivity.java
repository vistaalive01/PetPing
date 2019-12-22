package com.example.petping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Sex;
    private EditText Breed;
    private EditText Weight;
    private EditText Character;
    private EditText Story;
    private EditText Health;
    private EditText DOB;
    private Button Btn;
    private  FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.petName);
        Breed = findViewById(R.id.petBreed);
        Sex = findViewById(R.id.petSex);
        Weight = findViewById(R.id.petWeight);
        Character = findViewById(R.id.petChar);
        Story = findViewById(R.id.petStory);
        Health = findViewById(R.id.petHealth);
        DOB = findViewById(R.id.petDOB);

        Btn = findViewById(R.id.btnAddPet);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String breed = Breed.getText().toString();
                String sex = Sex.getText().toString();
                String weight = Weight.getText().toString();
                String character = Character.getText().toString();
                String story = Story.getText().toString();
                String health = Health.getText().toString();
                String dob = DOB.getText().toString();
                addPet(name, breed, sex, weight, character, story, health, dob);
            }
        });
    }

    private void addPet(String name, String breed, String sex, String weight, String character, String story, String health, String DOB) {
        db = FirebaseFirestore.getInstance();
        Pet pet = new Pet(name, breed, sex, weight, character, story, health, DOB);
        db.collection("Pet").add(pet)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        showMessage("Add pet success!");
                    }
                });
    }

    private void showMessage(String show){
        //show message on application
        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_LONG).show();
    }

}
