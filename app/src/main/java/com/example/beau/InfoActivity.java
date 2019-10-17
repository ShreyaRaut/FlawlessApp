package com.example.beau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button button2;
    FirebaseAuth firebaseAuth;
    TextView textView2;
    TextView linkskintype;

    EditText fullName,phoneNo;
    Button buttonSave;
    RadioButton radioMale,radioFemale,radioDry,radioNormal,radioOily,radioCombination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

//        textView2=(TextView) findViewById(R.id.textView2);
//
//        textView2.setText("Welcome "+user.getEmail());
        linkskintype=findViewById(R.id.linkskintype);



//        info form casting

        fullName=(EditText)findViewById(R.id.fullName);
        phoneNo=(EditText)findViewById(R.id.phoneNo);
        radioMale=(RadioButton) findViewById(R.id.radioMale);
        radioFemale=(RadioButton) findViewById(R.id.radioFemale);
        radioDry=(RadioButton) findViewById(R.id.radioDry);
        radioNormal=(RadioButton) findViewById(R.id.radioNormal);
        radioOily=(RadioButton) findViewById(R.id.radioOily);
        radioCombination=(RadioButton) findViewById(R.id.radioCombination);

        buttonSave=(Button) findViewById(R.id.buttonSave);

        databaseReference= FirebaseDatabase.getInstance().getReference("Customer");
        firebaseAuth=FirebaseAuth.getInstance();






        linkskintype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(InfoActivity.this,SkinTypeActivity.class);
                startActivity(i);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender="";
                String skinType="";
                String fName=fullName.getText().toString().trim();
                String pNo=phoneNo.getText().toString().trim();

                if(radioMale.isChecked()){
                    gender="Male";
                }
                else if(radioFemale.isChecked())
                {
                    gender="Female";
                }

                if(radioDry.isChecked())
                {
                    skinType="Dry";
                }
                else if(radioNormal.isChecked())
                {
                    skinType="Normal";
                }
                else if(radioOily.isChecked())
                {
                    skinType="Oily";

                }
                else if(radioCombination.isChecked())
                {
                    skinType="Combination";
                }
                else
                {

                }



                Customer customer=new Customer(fName,pNo,gender,skinType);
                FirebaseUser user=firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(customer);
                Toast.makeText(InfoActivity.this,"Information Saved!",Toast.LENGTH_SHORT).show();

//                Toast.makeText(LoginActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//
//                Intent i= new Intent(InfoActivity.this,HomeActivity.class);
//                startActivity(i);




            }
        });

    }

}