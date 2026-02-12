package com.example.firebase3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class delete extends AppCompatActivity {

    private  Button delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        delete = findViewById(R.id.delete) ;

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  first style            (delete for document name )

//                FirebaseFirestore.getInstance().collection("vendor") .document("B6g1MIVJrPiity28VxdO").delete() .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(delete.this, "Successful", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class ) );
//                    }
//                });

                FirebaseFirestore.getInstance().collection("vendor") .whereEqualTo("pass", "20") .get() .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        WriteBatch b = FirebaseFirestore.getInstance().batch();
                        List <DocumentSnapshot> s = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot  snapshot  : s) {
                            b.delete(snapshot.getReference());
                        }
                        b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(delete.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class ) );
                            }
                        });

                    }
                });


                }
        }) ;
    }
}