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

public class Login_act extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;
    private Button regBtn;
    private Button loginBtn;

    private AlertDialog.Builder alertBuilder;
    private AlertDialog alertDialog;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_act);

        emailET = (EditText) findViewById(R.id.userNameETlogin);
        passwordET = (EditText) findViewById(R.id.passWordETlogin);
        regBtn = (Button) findViewById(R.id.signupBtnlogin);
        loginBtn = (Button) findViewById(R.id.loginBtnlogin);

        alertBuilder = new AlertDialog.Builder(Login_act.this);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_act.this, Signup_act.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put login email");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();

                }else if(passwordET.getText().toString().equals("")) {
                    alertBuilder.setTitle("Input invalid");
                    alertBuilder.setMessage("please put password");
                    alertDialog = alertBuilder.create();
                    alertDialog.show();
                } else {
                    //log this user in
                    mAuth.signInWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Login", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login_act.this, user.getEmail(),
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Login", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(Login_act.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }

            }
        });
    }
}
