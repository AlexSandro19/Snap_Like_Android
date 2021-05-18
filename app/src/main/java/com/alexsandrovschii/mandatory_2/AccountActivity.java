package com.alexsandrovschii.mandatory_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alexsandrovschii.mandatory_2.adapter.MyAdapter;
import com.alexsandrovschii.mandatory_2.model.Snap;
import com.alexsandrovschii.mandatory_2.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity implements Updatable{

    List<Snap> snaps = new ArrayList<>();
    MyAdapter myAdapter;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Repository.repo().setupUserSnaps(this, snaps);
        setupListView();
        String user = Repository.repo().getUser();
        textView = findViewById(R.id.textViewAccountUserName);
        textView.setText(user);



    }

    private void setupListView() {
        listView = findViewById(R.id.listViewAccount);
        myAdapter = new MyAdapter(snaps, this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("snapid", snaps.get(position).getId());
            startActivity(intent);
        }));
    }

    @Override
    public void update(Object obj) {
        myAdapter.notifyDataSetChanged();
    }

    public void backBtnPressed(View view) {
        finish();
    }

}