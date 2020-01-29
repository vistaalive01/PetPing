package com.example.petping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {
    private TextView regTxt;
    private Button regBtn;
    private EditText emailE, passWordE, confirmPassE;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regTxt = findViewById(R.id.regTxt);
        regBtn = findViewById(R.id.regBtn);

        emailE = findViewById(R.id.regEmail);
        passWordE = findViewById(R.id.regPassword);
        confirmPassE = findViewById(R.id.regConfimPass);


        regTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                               String email = emailE.getText().toString();
                String password = passWordE.getText().toString();
                String confirmPass = confirmPassE.getText().toString();

                // Check wrong input from user
                if(email.isEmpty()){
                    showMessage("Please input email");
                }
                else if(password.isEmpty()){
                    showMessage("Please input password");
                }
                else if(!password.equals(confirmPass)){
                    showMessage("Please Make sure that you input correct password");
                }
                // User input every information
                else{
                    CreateUserAccount(email, password, confirmPass);

                }
            }
        });
    }

    private void CreateUserAccount(final String email, final String password, final String confirmPass) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    data.put("confirmPass", confirmPass);
                    changePage();
//                    db.collection("User")
//                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .collection("Information")
//                            .document("BasicInformation")
//                            .set(data)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    changePage();
//                                    showMessage("Can!!");
//                                }
//                            });
                }

            }
        });

    }

    private void changePage() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // make text to show user
    private void showMessage(String show){
        Toast.makeText(getApplicationContext(),show,Toast.LENGTH_LONG).show();

    }


}
