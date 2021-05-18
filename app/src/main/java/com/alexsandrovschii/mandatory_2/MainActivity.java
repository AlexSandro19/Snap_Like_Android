package com.alexsandrovschii.mandatory_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alexsandrovschii.mandatory_2.adapter.MyAdapter;
import com.alexsandrovschii.mandatory_2.model.Snap;
import com.alexsandrovschii.mandatory_2.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Updatable{

    List<Snap> snaps = new ArrayList<>();

    ListView listView;
    MyAdapter myAdapter;
    Button addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListView();
        Repository.repo().setup(this, snaps);
        Repository.repo().startListener();
    }

    private void setupListView() {
        listView = findViewById(R.id.listViewMain);
        myAdapter = new MyAdapter(snaps, this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("snapid", snaps.get(position).getId());
            startActivity(intent);
        }));
    }

    public void accountBtnPressed(View view){
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void snapBtnPressed(View view){
        Intent intent = new Intent(MainActivity.this, SnapActivity.class);
        startActivity(intent);
    }

    @Override
    public void update(Object obj) {
        myAdapter.notifyDataSetChanged();
    }

}