package com.vnrvjiet.qr_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    public void teacherLogin(View view) {
        checkLogin("Teacher");
    }

    public void adminLogin(View view) {
        checkLogin("Admin");
    }

    private void checkLogin(String teacher) {
        View addView = LayoutInflater.from(this).inflate(R.layout.add_credentials, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(addView)
                .setMessage(teacher + " Login")
                .setCancelable(true)
                .setPositiveButton("Login", null).setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText = addView.findViewById(R.id.add_email), passText = addView.findViewById(R.id.add_password);
                String e = emailText.getText().toString().trim();
                String p = passText.getText().toString().trim();
                if(e.isEmpty()){
                    emailText.setError("Enter email");
                    return;
                }
                if(p.isEmpty()){
                    passText.setError("Enter password");
                    return;
                }
//                reference.child(teacher).orderByChild("email").equalTo(e).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange( DataSnapshot snapshot) {
//                        if(snapshot.exists())
//                            Toast.makeText(MainActivity.this, "e", Toast.LENGTH_SHORT).show();
//                        else
//                            Toast.makeText(MainActivity.this, "n", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled( DatabaseError error) {
//
//                    }
//                });
                //dialog.cancel();
            }
        });
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    public void studentLogin(View view) {
    }
}