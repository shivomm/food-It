package com.shivom.foodit;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivom.foodit.Model.User;
import com.shivom.foodit.common.Comman;

public class SignIn1 extends AppCompatActivity {
  EditText editPhone,editPassword;
  Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in1);
       editPassword=(EditText)findViewById(R.id.editPassword);
       editPhone=(EditText)findViewById(R.id.editPhone);
       btnSignIn=(Button)findViewById(R.id.btnSignIn);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(SignIn1.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Checking User avail
                        if(dataSnapshot.child(editPhone.getText().toString()).exists())
                        {
                            //Get User data
                            progressDialog.dismiss();
                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                           // assert user != null;
                            if (user.getPassword().equals(editPassword.getText().toString()))
                            {
                                progressDialog.dismiss();

                                Toast.makeText(SignIn1.this, ""+user.getName(), Toast.LENGTH_SHORT).show();
                                user.setPhone(editPhone.getText().toString());
                                Intent intent = new Intent(SignIn1.this, Home.class);
                                Comman.currentUser=user;
                                startActivity(intent);
                                finish();

                            } else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignIn1.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignIn1.this, "User not exists!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
