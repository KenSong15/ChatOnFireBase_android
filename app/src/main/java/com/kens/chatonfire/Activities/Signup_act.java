package com.kens.chatonfire.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kens.chatonfire.R;

public class Signup_act extends AppCompatActivity {

    private EditText usernameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText firmpwdET;
    private Button regBtn;

    private AlertDialog.Builder alertBuilder;
    private AlertDialog alertDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_act);

        usernameET = (EditText) findViewById(R.id.usernameETreg);
        emailET = (EditText) findViewById(R.id.emailETreg);
        passwordET = (EditText) findViewById(R.id.passwordETreg);
        firmpwdET = (EditText) findViewById(R.id.password2ETreg);
        regBtn = (Button) findViewById(R.id.regBtnSU);

        mAuth = FirebaseAuth.getInstance();

        alertBuilder = new AlertDialog.Builder(Signup_act.this);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(usernameET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put username");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();

                }else if(emailET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put email");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();


                }else if(passwordET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put password");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();


                }else if(firmpwdET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put confirm password");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();


                }else if(!passwordET.getText().toString().equals(firmpwdET.getText().toString())) {
                    alertBuilder.setTitle("Password done not match");
                    alertBuilder.setMessage("please confirm password is match");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();

                }else{
                    //sign up this user
                    //Toast.makeText(Signup_act.this, "good to reg.", Toast.LENGTH_SHORT).show();

                    mAuth.createUserWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("SignUp", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(Signup_act.this, Chat_act.class));
                                    } else {
                                        Toast.makeText(Signup_act.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });

    }
}
