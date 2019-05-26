package com.example.user.freebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private TextView tvUserPassword;
    private TextView tvUserName;
    private TextView tvUserSurname;
    private TextView tvUserGroup;

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

        sharedPreferences = getSharedPreferences(LoginActivity.USER_DATA, MODE_PRIVATE);

        tvUserEmail.setText(sharedPreferences.getString(LoginActivity.USER_EMAIL, ""));
        tvUserPassword.setText(sharedPreferences.getString(LoginActivity.USER_PASSWORD, ""));
        tvUserName.setText(sharedPreferences.getString(LoginActivity.USER_NAME, ""));
        tvUserSurname.setText(sharedPreferences.getString(LoginActivity.USER_SURNAME,""));
        tvUserGroup.setText(sharedPreferences.getString(LoginActivity.USER_GROUP,""));

    }

    public void onLogout(View view){
        editor = sharedPreferences.edit();
        editor.putBoolean(LoginActivity.USER_STATUS, false).apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

}
