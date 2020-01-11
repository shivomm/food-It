package com.shivom.foodit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivom.foodit.Model.User;

public class SignUp extends AppCompatActivity {
 EditText editPhoneUp,editNameUp,editPasswordUp;
 Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editNameUp=(EditText)findViewById(R.id.editNameUp);
         editPasswordUp=(EditText)findViewById(R.id.editPasswordUp);
         editPhoneUp=(EditText)findViewById(R.id.editPhoneUp);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog =new ProgressDialog(SignUp.this);
                mDialog.setMessage("please  waiting");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //check if User data already exit or not
                    if(dataSnapshot.child(editPhoneUp.getText().toString()).exists()){
                        mDialog.dismiss();
                        Toast.makeText(SignUp.this, "Phone Number already Registered", Toast.LENGTH_SHORT).show();
                        }else
                    {
                        mDialog.dismiss();
                        User user =new User(editNameUp.getText().toString(),editPasswordUp.getText().toString());

                                table_user.child(editPhoneUp.getText().toString()).setValue(user);
                        Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
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
