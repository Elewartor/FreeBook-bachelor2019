package com.example.user.freebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.freebook.Connections.BackgroundTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvUserMsg;
    private ImageView ivUserQRCode;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvUserMsg = findViewById(R.id.tv_user_msg);
        ivUserQRCode = findViewById(R.id.iv_user_qrcode);

        sharedPreferences = getSharedPreferences(LoginActivity.USER_DATA, MODE_PRIVATE);
        // msg settings
        String mainMsg = "Hello, " + sharedPreferences.getString(LoginActivity.USER_NAME, "") + " " + sharedPreferences.getString(LoginActivity.USER_SURNAME, "") + " from "
                + sharedPreferences.getString(LoginActivity.USER_GROUP, "") + "!:)";
        String adviceMsg = "\n\nUse qr-code below to enter in library.";

        tvUserMsg.setText(mainMsg+adviceMsg);


        String method = "get_user_QRCode";
        String email = sharedPreferences.getString(LoginActivity.USER_EMAIL,null);
        if(email != null) {
            try {
                Picasso.get().load(new BackgroundTask(this).execute(method, email).get()).into(ivUserQRCode);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                editor = sharedPreferences.edit();
                editor.putBoolean(LoginActivity.USER_STATUS, false).apply();
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }

    public void onLogout(View view){
        editor = sharedPreferences.edit();
        editor.putBoolean(LoginActivity.USER_STATUS, false).apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.on_library :
                startActivity(new Intent(this, LibraryActivity.class));
                return true;
            case R.id.on_scanner:
                startActivity(new Intent(this, ScannerActivity.class));
                return true;
            case R.id.on_orders:
                startActivity(new Intent(this, UserOrdersActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
