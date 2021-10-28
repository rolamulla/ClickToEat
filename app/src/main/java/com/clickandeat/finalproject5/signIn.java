package com.clickandeat.finalproject5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class signIn extends AppCompatActivity {

    private TextInputLayout usernameTxt, passwordTxt;
    private Button signInBtn, registerBtn, forgetPassBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        usernameTxt = findViewById(R.id.usernameTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        signInBtn = findViewById(R.id.signInBtn);
        registerBtn = findViewById(R.id.registerBtn);
        forgetPassBtn = findViewById(R.id.forgetPassBtn);

        usernameTxt.getEditText().setText("foda@gmail.com"); //TODO: Remove me
        passwordTxt.getEditText().setText("12345678"); //TODO: Remove me

        mAuth = FirebaseAuth.getInstance();

        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter Your Email To Receive the Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        if (mail != null)
                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(signIn.this, "Reset Link Send To Your Email !!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signIn.this, "Incorrect mail !!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                });
                passwordResetDialog.create().show();
            }
        });

        //sign up
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signIn.this, Register.class));
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateGmail() || !validatePass()) {
                    return;
                } else {
                    isUser();
                }
            }
        });
    }


    private void isUser() {

        String username = usernameTxt.getEditText().getText().toString();
        String password = passwordTxt.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               if (!task.isSuccessful()) {
                                                   try {
                                                       throw task.getException();
                                                   }
                                                   // if user enters wrong email.
                                                   catch (FirebaseAuthInvalidUserException invalidEmail) {
                                                       usernameTxt.setError("Incorrect username");
                                                   }
                                                   // if user enters wrong password.
                                                   catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                                                       passwordTxt.setError("Incorrect password");
                                                   } catch (Exception e) {
                                                       Toast.makeText(signIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                   }
                                               } else {
                                                   Intent intent = new Intent(getApplicationContext(), Location.class);
                                                   startActivity(intent);
                                               }
                                           }
                                       }
                );
    }

    //Validation info
    private Boolean validateGmail() {
        String val = usernameTxt.getEditText().getText().toString();

        if (val.isEmpty()) {
            usernameTxt.setError("Field cannot be empty");
            return false;
        } else {
            usernameTxt.setError(null);
            return true;
        }
    }

    private Boolean validatePass() {
        String val = passwordTxt.getEditText().getText().toString();

        if (val.isEmpty()) {
            passwordTxt.setError("Field cannot be empty");
            return false;
        } else {
            passwordTxt.setError(null);
            return true;
        }
    }
}