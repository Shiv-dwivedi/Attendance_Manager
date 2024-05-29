package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private Button SignupButton;
    private TextView LoginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        SignupButton = findViewById(R.id.signup_button);
        LoginRedirectText = findViewById(R.id.loginRedirectText);

        SignupButton.setOnClickListener(view -> {
            String user = signupEmail.getText().toString().trim();
            String pass = signupPassword.getText().toString().trim();

            if (user.isEmpty()) {
                signupEmail.setError("Email can't be empty");
                return;
            }
            if (pass.isEmpty()) {
                signupPassword.setError("Password can't be empty");
                return;
            }

            auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    finish(); // Finish this activity to prevent going back to it after signing up
                } else {
                    Toast.makeText(SignupActivity.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        LoginRedirectText.setOnClickListener(view -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}
