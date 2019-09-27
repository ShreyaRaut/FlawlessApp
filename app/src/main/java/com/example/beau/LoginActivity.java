package com.example.beau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    TextView signup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.in_email);
        password = findViewById(R.id.in_pwd);
        login = findViewById((R.id.btn_login));
        signup = findViewById(R.id.link_signup);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(LoginActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,CameraActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Please login!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailid = email.getText().toString();
                String pwd = password.getText().toString();

                if(emailid.isEmpty())
                {
                    email.setError("Required");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Required");
                    password.requestFocus();
                }
                else if(emailid.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(emailid.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            System.out.print("Inside 84-LA");
                            if(!task.isSuccessful())
                            {
                                System.out.print("Inside 86-LA");
                                Toast.makeText(LoginActivity.this,"Login unsuccessful,please try again.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                System.out.print("Inside 91-LA");
                                startActivity(new Intent(LoginActivity.this,CameraActivity.class));

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Error occured!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
