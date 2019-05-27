package com.example.user.freebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
    }

    public void onAccountActivity (View view){
        startActivity(new Intent(this, UserProfileActivity.class));
    }
    public void onScanner(View view){
        startActivity(new Intent(this, ScannerActivity.class));
    }

    @Override
    public void onBackPressed() {}
}
