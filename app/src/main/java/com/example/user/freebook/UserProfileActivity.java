package com.example.user.freebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.freebook.Connections.BackgroundTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private TextView tvUserPassword;
    private TextView tvUserName;
    private TextView tvUserSurname;
    private TextView tvUserGroup;
    private ImageView ivUserQRCode;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvUserEmail = findViewById(R.id.tv_user_email);
        tvUserPassword = findViewById(R.id.tv_user_password);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserSurname = findViewById(R.id.tv_user_surname);
        tvUserGroup = findViewById(R.id.tv_user_group);
        ivUserQRCode = findViewById(R.id.iv_user_qrcode);

        sharedPreferences = getSharedPreferences(LoginActivity.USER_DATA, MODE_PRIVATE);

        tvUserEmail.setText(sharedPreferences.getString(LoginActivity.USER_EMAIL, ""));
        tvUserPassword.setText(sharedPreferences.getString(LoginActivity.USER_PASSWORD, ""));
        tvUserName.setText(sharedPreferences.getString(LoginActivity.USER_NAME, ""));
        tvUserSurname.setText(sharedPreferences.getString(LoginActivity.USER_SURNAME,""));
        tvUserGroup.setText(sharedPreferences.getString(LoginActivity.USER_GROUP,""));

        String method = "get_user_QRCode";
        String email = sharedPreferences.getString(LoginActivity.USER_EMAIL,null);
        if(email != null) {
            try {
                Picasso.get().load(new BackgroundTask(this).execute(method, email).get()).into(ivUserQRCode);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onLogout(View view){
        editor = sharedPreferences.edit();
        editor.putBoolean(LoginActivity.USER_STATUS, false).apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

}
