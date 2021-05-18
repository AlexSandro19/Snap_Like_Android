package com.alexsandrovschii.mandatory_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexsandrovschii.mandatory_2.model.Snap;
import com.alexsandrovschii.mandatory_2.repository.Repository;

public class DetailActivity extends AppCompatActivity implements TaskListener{

    private ImageView imageView;
    private Bitmap currentBitmap;
    private Snap currentSnap;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String snapId = getIntent().getStringExtra("snapid");
        currentSnap = Repository.repo().getSnapWithId(snapId);
        textViewSetup();
        imageViewSetup(snapId);

    }

    private void imageViewSetup(String snapId) {
        imageView = findViewById(R.id.imageViewDetail);
        currentBitmap = currentSnap.getImage();
        imageView.setImageBitmap(currentBitmap);
    }

    private void textViewSetup() {
        textView = findViewById(R.id.textViewDetailUser);
        textView.setText(currentSnap.getUser() + " Snap");
    }

    public void backBtnPressed(View view) {
        finish();
    }


    @Override
    public void receive(byte[] bytes) {
        currentBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView.setImageBitmap(currentBitmap);
    }
}