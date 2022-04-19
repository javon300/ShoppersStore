package com.mm.shoppersstore;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private String email;
    private String password;
    private Button homeScreenBtn;
    private Button registerScreenBtn;
    private TextView emailTv;
    private TextView passwordTv;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);



        emailTv = (TextView) findViewById(R.id.emailTf2);
        passwordTv = (TextView) findViewById(R.id.emailTf2);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
/****************************  Buttons and Button Listenners ************************/
        //btn and intent creation for navigation
        homeScreenBtn = (Button) findViewById(R.id.loginBtn);
        homeScreenBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent agn = new Intent(Login.this, Home.class);
                startActivity(agn);

            }
        });

        //btn and intent creation for navigation
        registerScreenBtn = (Button) findViewById(R.id.registerBtn);
        registerScreenBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                loginUser();
                Intent agn = new Intent(Login.this, Registration.class);
                startActivity(agn);

            }
        });
/****************************  Buttons and Button Listenners End ************************/

    }

    public void loginUser()
    {
        //getEmailPassword();
        email = "pam@email.com";
        password = "Password123";
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(Object o) {
    }

    public void getEmailPassword()
    {
        email = emailTv.getText().toString();
        password = passwordTv.getText().toString();
        Log.d(TAG, "getEmailPassword: " + email + password);
    }
    public void onClick(View v) {
// TODO Auto-generated method stub

    }
}