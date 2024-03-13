package com.rf9.tips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rf9.tips.MainActivity;
import com.rf9.tips.R;
import com.rf9.tips.adapter.TeamAdapter;
import com.rf9.tips.model.DataModel;


import java.util.ArrayList;

public class ViewDetailsActivity extends AppCompatActivity {
    public View view;
    private RecyclerView recyclerView;
    private TextView message, today, old, heading;
    private String Page = "", Subject = "";
    private DatabaseReference ref;
    private ArrayList<DataModel> list;
    private ProgressBar progressBar;
    private int count = 0;
    //  private final String TAG = MainActivity.class.getSimpleName(); //not nessacery
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        message = findViewById(R.id.message_txt);
        today = findViewById(R.id.today_tips);
        old = findViewById(R.id.old_tips);
        ref = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        progressBar = findViewById(R.id.progress_bar);
        back = findViewById(R.id.btn_back);
        heading = findViewById(R.id.heading);
        Intent i = getIntent();
        Page = i.getStringExtra("page");
        Subject = i.getStringExtra("subject");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getValues(Subject, Page, "Today");
                heading.setText(Page);
                count++;
            }
        }, 700);

        today.setOnClickListener(view -> {
            if (count == 1) {
                getValues(Subject, Page, "Today");
            }
        });

        old.setOnClickListener(view -> {
            if (count == 1) {
                getValues(Subject, Page, "Old");
            }
        });

    }


    private void getValues(String sub, String page, String day) {
        ref.child(sub).child(page).child(day).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    list.add(ds.getValue(DataModel.class));
                }

                TeamAdapter adapter = new TeamAdapter(list, ViewDetailsActivity.this);

                if (adapter.getItemCount() != 0) {
                    progressBar.setVisibility(View.GONE);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.child(sub).child(page).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String mes = snapshot.child("message").getValue(String.class);
                message.setText(mes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}