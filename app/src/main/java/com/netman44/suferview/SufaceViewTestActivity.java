package com.netman44.suferview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.netman44.camera.R;

public class SufaceViewTestActivity extends AppCompatActivity {
    MyGameSurfaceView1 mMyGameSurfaceView1;
    MyGameSurfaceView2 mMyGameSurfaceView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suface_view_test);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        mMyGameSurfaceView1 = findViewById(R.id.MyGameSurfaceView1);
        mMyGameSurfaceView2 = findViewById(R.id.MyGameSurfaceView2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyGameSurfaceView1.MyGameSurfaceView_OnResume();
        mMyGameSurfaceView2.MyGameSurfaceView_OnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMyGameSurfaceView1.MyGameSurfaceView_OnPause();
        mMyGameSurfaceView2.MyGameSurfaceView_OnPause();
    }
}
