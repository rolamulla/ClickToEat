package com.clickandeat.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.clickandeat.finalproject5.Model.userModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    Button signupBtn;
    EditText userphone ,username,useremail, pass;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    Spinner address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userphone=findViewById(R.id.userphone);

        username=findViewById(R.id.username);
        useremail=findViewById(R.id.useremail);
        pass=findViewById(R.id.password);
        signupBtn=findViewById(R.id.signupBtn);
        address= findViewById(R.id.spinner);
        mAuth=FirebaseAuth.getInstance();

        Spinner mySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.location));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        database = FirebaseDatabase.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int phone = Integer.parseInt(userphone.getText().toString().trim());
                String name = username.getText().toString().trim();
                String email = useremail.getText().toString().trim();
                String password= pass.getText().toString().trim();
                String Address= String.valueOf(address.getResources().getStringArray(R.array.location).toString().trim());


                if(name.isEmpty())
                {
                    username.setError("Enter the name");
                    username.requestFocus();
                    return;
                }

                if(email.isEmpty())
                {
                    useremail.setError("Email is empty");
                    useremail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    useremail.setError("Enter the valid email address");
                    useremail.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass.setError("Enter the password");
                    pass.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    pass.setError("Length of the password should be more than 6");
                    pass.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            userModel userModel = new userModel(name,email,password,Address,phone);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);

                            Toast.makeText(Register.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Location.class));
                        }
                        else
                        {
                            Toast.makeText(Register.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}