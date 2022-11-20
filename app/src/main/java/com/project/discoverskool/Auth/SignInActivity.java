package com.project.discoverskool.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.discoverskool.MainActivity;
import com.project.discoverskool.R;

public class SignInActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText EditTextEmail,EditTextPassword;
    boolean passwordVisible;
    TextView signUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        EditTextEmail = findViewById(R.id.editText2);
        EditTextPassword = findViewById(R.id.editText3);
        progressBar = findViewById(R.id.progressBar);

        EditTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=EditTextPassword.getRight()-EditTextPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=EditTextPassword.getSelectionEnd();
                        if (passwordVisible){
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off_24,0);
                            //hide password
                            EditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else {
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_24,0);
                            //show password
                            EditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        EditTextPassword.setSelection(selection);
                        return  true;

                    }
                }
                return false;
            }
        });
    }

    public void login(View view) {
        String email = EditTextEmail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            EditTextEmail.setError(" email is required!!");
            EditTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EditTextEmail.setError("Please provide a valid email address!");
            EditTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            EditTextPassword.setError(" password is required!!");
            EditTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    Toast.makeText(SignInActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Failed to log in check your credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void sign_Up(View view) {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        finish();
    }

    public void password(View view) {
    }
}