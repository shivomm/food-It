package com.shivom.foodit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivom.foodit.Model.User;

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
        final FirebaseDatabase  database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference(" User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog =new ProgressDialog(SignIn1.this);
                mDialog.setMessage("please  waiting");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(editPhone.getText().toString()).exists()) {
                              mDialog.dismiss();
                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editPassword.getText().toString())) {
                                Toast.makeText(SignIn1.this, "SignIn successfulll", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn1.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {   mDialog.dismiss();
                            Toast.makeText(SignIn1.this, "User Not Exist", Toast.LENGTH_SHORT).show();
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
