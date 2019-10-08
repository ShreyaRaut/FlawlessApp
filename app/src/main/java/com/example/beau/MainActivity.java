package com.example.beau;

//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.beau.Helper.GraphicOverlay;
//import com.example.beau.Helper.RectOverlay;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.ml.vision.FirebaseVision;
//import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.face.FirebaseVisionFace;
//import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
//import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
//import com.wonderkiln.camerakit.CameraKitError;
//import com.wonderkiln.camerakit.CameraKitEvent;
//import com.wonderkiln.camerakit.CameraKitEventListener;
//import com.wonderkiln.camerakit.CameraKitImage;
//import com.wonderkiln.camerakit.CameraKitVideo;
//import com.wonderkiln.camerakit.CameraView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    Button signup;
    TextView login;
    private FirebaseAuth mFirebaseAuth;
//    /private FirebaseAuth.AuthStateListener mAuthStateListener;







//    CameraView cameraView;
//    GraphicOverlay graphicOverlay;
//    Button btnDetect;
//
//    AlertDialog waitingDialog;
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        cameraView.start();
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        cameraView.stop();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.in_name);
        email = findViewById(R.id.in_email);

        password = findViewById(R.id.in_pwd);
        signup = findViewById((R.id.btn_signup));
        login = findViewById(R.id.link_login);


//        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseAuth.getCurrentUser()!=null)
        {
//            Toast.makeText(MainActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),InfoActivity.class));
        }


//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
////                else
////                {
//////                    Toast.makeText(LoginActivity.this,"Please login!",Toast.LENGTH_SHORT).show();
////                }
//            }
//        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme );
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();


                    String emailid = email.getText().toString();
                    String pwd = password.getText().toString();

                    if(emailid.isEmpty())
                    {
                        email.setError("Required");
                        email.requestFocus();
                        return;
                    }
                    else if(pwd.isEmpty())
                    {
                        password.setError("Required");
                        password.requestFocus();
                        return;
                    }
                    else if(emailid.isEmpty() && pwd.isEmpty())
                    {
                        Toast.makeText(MainActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(!(emailid.isEmpty() && pwd.isEmpty()))
                    {
                        mFirebaseAuth.createUserWithEmailAndPassword(emailid,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                System.out.print("Inside 110-MA");
                                if(!task.isSuccessful())
                                {
                                    System.out.print("Inside 112-MA");
                                    Toast.makeText(MainActivity.this,"Sign up unsuccessful,please try again.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else{
                                    System.out.print("Inside 116-MA");
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Error occured!",Toast.LENGTH_SHORT).show();
                    }





//                public void onSignupSuccess() {
//                    signup.setEnabled(true);
//                    setResult(RESULT_OK, null);
//                    finish();
//                }

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                signup.setEnabled(true);
                                setResult(RESULT_OK, null);
                                finish();
                                // onSignupFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }


        });

//

//        cameraView=(CameraView)findViewById(R.id.camera_view);
//        graphicOverlay=(GraphicOverlay)findViewById(R.id.graphic_overlay);
//        btnDetect=(Button)findViewById(R.id.btn_detect);
//        waitingDialog=new SpotsDialog.Builder().setContext(this)
//                .setMessage("Please wait")
//                .setCancelable(false)
//                .build();
//
//        btnDetect.setOnClickListener((view) -> {
//           cameraView.start();
//           cameraView.captureImage();
//           graphicOverlay.clear();
//        });
//
//        cameraView.addCameraKitListener(new CameraKitEventListener() {
//            @Override
//            public void onEvent(CameraKitEvent cameraKitEvent) {
//
//            }
//
//            @Override
//            public void onError(CameraKitError cameraKitError) {
//
//            }
//
//            @Override
//            public void onImage(CameraKitImage cameraKitImage) {
//                waitingDialog.show();
//
//                Bitmap bitmap = cameraKitImage.getBitmap();
//                bitmap= Bitmap.createScaledBitmap(bitmap,cameraView.getWidth(),cameraView.getHeight(),false);
//                cameraView.stop();
//
//                runFaceDetector(bitmap);
//
//            }
//
//            @Override
//            public void onVideo(CameraKitVideo cameraKitVideo) {
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

//    private void runFaceDetector(Bitmap bitmap) {
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
//
//        FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder()
//                .build();
//
//        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
//                .getVisionFaceDetector(options);
//
//        detector.detectInImage(image)
//                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
//                    @Override
//                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
//                        processFaceResult(firebaseVisionFaces);
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//    }
//
//    private void processFaceResult(List<FirebaseVisionFace> firebaseVisionFaces) {
//        int count=0;
//
//        for(FirebaseVisionFace face : firebaseVisionFaces)
//        {
//            Rect bounds = face.getBoundingBox();
//            //Draw Rect
//            RectOverlay rect = new RectOverlay(graphicOverlay,bounds);
//            graphicOverlay.add(rect);
//
//            count++;
//        }
//
//        waitingDialog.dismiss();
//        Toast.makeText(this,String.format("Detected %d faces in image",count),Toast.LENGTH_SHORT).show();
//    }


}
