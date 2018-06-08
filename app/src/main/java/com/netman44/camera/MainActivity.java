package com.netman44.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.netman44.suferview.SufaceViewTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToSufaceViewDemo(View view) {
        Intent intent = new Intent(this, SufaceViewTestActivity.class);
        startActivity(intent);
    }

    public void tackPicture(View view) {
        Intent intent = new Intent(this, TackPictureActivity.class);
        startActivity(intent);
    }
}
