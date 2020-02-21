package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddPetShelterFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RadioGroup typeRdGroup, sexRdGroup, statusRdGroup;
    private RadioButton typeRd, dogRd, catRd, sexRd, maleRd, femaleRd, statusRd, homeRd, waitRd;
    private EditText name, breed, color, marking, age, weight, foundLoc;
    private EditText character, size, story, health;
    private Button btn;
    private String sex, type, status, size1;
    private Map<String, Object> data = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_pet_shelter, null);
        btn = view.findViewById(R.id.button);
        name = view.findViewById(R.id.name);
        breed = view.findViewById(R.id.breed);
        color = view.findViewById(R.id.color);
        marking = view.findViewById(R.id.marking);
        age = view.findViewById(R.id.age);
        weight = view.findViewById(R.id.weight);
        foundLoc = view.findViewById(R.id.found_loc);
        character =  view.findViewById(R.id.character);
        size = view.findViewById(R.id.size);
        story = view.findViewById(R.id.story);
        health = view.findViewById(R.id.health);

        sexRdGroup = view.findViewById(R.id.rd_sex);
        maleRd = view.findViewById(R.id.rd_male);
        femaleRd = view.findViewById(R.id.rd_female);

        typeRdGroup = view.findViewById(R.id.rd_type);
        dogRd = view.findViewById(R.id.rd_dog);
        catRd = view.findViewById(R.id.rd_cat);

        statusRdGroup = view.findViewById(R.id.rd_status);
        homeRd = view.findViewById(R.id.rd_find_home);
        waitRd = view.findViewById(R.id.rd_waiting);

        if(weight.getText().toString().equals("1") || weight.getText().toString().equals("2") ||
                weight.getText().toString().equals("3") || weight.getText().toString().equals("4") ||
                weight.getText().toString().equals("5")){
            size.setText("S");
            size1 = "S";
        }
        else if(weight.getText().toString().equals("6") || weight.getText().toString().equals("7") ||
                weight.getText().toString().equals("8") || weight.getText().toString().equals("9") ||
                weight.getText().toString().equals("10")){
            size.setText("M");
            size1 = "M";
        }
        else if(weight.getText().toString().compareTo("11") > 0){
            size.setText("L");
            size1 = "L";
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioSex = sexRdGroup.getCheckedRadioButtonId();
                sexRd = view.findViewById(radioSex);
                if(sexRd == view.findViewById(R.id.rd_male)){
                    sex = maleRd.getText().toString();
                }
                else if(sexRd == view.findViewById(R.id.rd_female)){
                    sex = femaleRd.getText().toString();
                }

                int radioType = typeRdGroup.getCheckedRadioButtonId();
                typeRd = view.findViewById(radioType);
                if(typeRd == view.findViewById(R.id.rd_dog)){
                    type = dogRd.getText().toString();
                }
                else if(typeRd == view.findViewById(R.id.rd_cat)){
                    type = catRd.getText().toString();
                }

                int radioStatus = statusRdGroup.getCheckedRadioButtonId();
                statusRd = view.findViewById(radioStatus);
                if(statusRd == view.findViewById(R.id.rd_find_home)){
                    status = homeRd.getText().toString();
                }
                else if(statusRd == view.findViewById(R.id.rd_waiting)){
                    status = waitRd.getText().toString();
                }

                data.put("Age", age.getText().toString());
                data.put("Breed", breed.getText().toString());
                data.put("Character", character.getText().toString());
                data.put("Color", color.getText().toString());
                data.put("Health", health.getText().toString());
                data.put("Marking", marking.getText().toString());
                data.put("Name", name.getText().toString());
                data.put("OriginalLocation", foundLoc.getText().toString());
                data.put("Sex", sex);
                data.put("Size", size1);
                data.put("Status", status);
                data.put("Story", story.getText().toString());
                data.put("Type", type);
                data.put("Weight", weight.getText().toString());
                data.put("Image", "https://firebasestorage.googleapis.com/v0/b/petping-c03ea.appspot.com/o/Leo.jpg?alt=media&token=139eb991-a383-4383-ac51-bf789788f1a8");

                db.collection("Pet").document().set(data);
            }
        });
        return view;
    }
}
