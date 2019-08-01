package com.hermosaprogramacion.premium.ecomerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(3000);
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish();
    }
}
