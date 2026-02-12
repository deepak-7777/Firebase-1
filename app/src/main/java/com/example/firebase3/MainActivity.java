package com.example.firebase3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private Button fire;

    Button next, delete2, images;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.ed_name ) ;
        pass = findViewById(R.id.ed_age ) ;
        fire = findViewById(R.id.btn_add );
        next = findViewById(R.id.btn_next ) ;
        delete2 = findViewById(R.id.btn_delete );
        images = findViewById(R.id.btn_images );

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String , String > v= new HashMap<>();
                v.put("name", name.getText().toString() );
                v.put("pass", pass.getText().toString() );
                FirebaseFirestore.getInstance().collection("vendor").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {

                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        pass.setText("");
                    }
                });

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), show.class ) );
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), delete.class ) );
            }
        });

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), upload_images.class ) );
            }
        });

    }
}