package com.example.durianspeciesclassificationapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDurian extends AppCompatActivity {

    private DatabaseReference ref;
    ArrayList<Durians> list;
    RecyclerView recyclerView;
    SearchView searchView;

    DatabaseReference connref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_durian);

        ref = FirebaseDatabase.getInstance("https://durianapp-fyp2-default-rtdb.firebaseio.com/").getReference().child("durians");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);

        connref = FirebaseDatabase.getInstance("https://durianapp-fyp2-default-rtdb.firebaseio.com/").getReference(".info/connected");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            list.add(ds.getValue(Durians.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SearchDurian.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null){
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String str) {
                        connref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                boolean connected = snapshot.getValue(Boolean.class);
                                if (connected) {
                                    search(str);
                                }else{
                                    Toast.makeText(SearchDurian.this, "Internet is not connected", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.err.println("Listener was cancelled");
                            }
                        });

                        return true;
                    }
                });
        }
    }

    private void search(String str){
        ArrayList<Durians> myList = new ArrayList<>();
        for (Durians object : list ){
            if (object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        //display the durians from myList one by one using adapter
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }
}