package com.example.durianspeciesclassificationapplication;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Result extends AppCompatActivity {
    //declare database variable
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");
        String accuracy = bundle.getString("accuracy");

        //storing the strings from mainActivity
        TextView durianName = (TextView) findViewById(R.id.durianName);
        TextView durianDesc = (TextView) findViewById(R.id.durianDesc);
        ImageView durianPic = findViewById(R.id.durianPic);

        //set ImageView based on the result accordingly
        switch(result){
            case "D24":
                durianPic.setImageDrawable(getResources().getDrawable(R.drawable.d24));
                break;
            case "D101":
                durianPic.setImageDrawable(getResources().getDrawable(R.drawable.d101));
                break;
            case "Musang King":
                durianPic.setImageDrawable(getResources().getDrawable(R.drawable.mk));
                break;
            case "D13":
                durianPic.setImageDrawable(getResources().getDrawable(R.drawable.d13));
                break;
        }

        //reference to database child
        database = FirebaseDatabase.getInstance("https://durianapp-fyp2-default-rtdb.firebaseio.com/").getReference().child("durians").child(result);
        database.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String desc = dataSnapshot.child("desc").getValue().toString();
                        String rarity = dataSnapshot.child("rarity").getValue().toString();
                        String source = dataSnapshot.child("source").getValue().toString();
                        String taste = dataSnapshot.child("taste").getValue().toString();
                        String feature = dataSnapshot.child("features").getValue().toString();
                        String dDesc = desc + "<br><br><b>Features: </b>" + feature + "<br><br><b>Taste: </b>" + taste +
                                "<br><br><b>Rarity: </b>" + rarity + "<br><br><b>Source: </b>" + source;

                        durianDesc.setText(Html.fromHtml(dDesc));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                }
        );

        durianName.setText(result + " with " + accuracy + "% accuracy");
    }
}