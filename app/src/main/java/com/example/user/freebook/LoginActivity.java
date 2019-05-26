package com.example.user.freebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_DATA = "user_data";

    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String USER_GROUP = "user_group";
    public static final String USER_STATUS = "user_status";


    SharedPreferences userSharedPreferences;
    SharedPreferences.Editor editor;

    private EditText email;
    private EditText password;

    private String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        userSharedPreferences = getSharedPreferences(USER_DATA, MODE_PRIVATE);

        if(userSharedPreferences.getBoolean(USER_STATUS, false)){
            startActivity(new Intent(this, UserOrdersActivity.class));
        }
    }

    public void onRegisterActivity(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onLoginPush(View v){
        String method = "login";
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        try {
            userData = new BackgroundTask(this).execute(method,email,password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (userData.equals("Wrong email or password")){
            Toast.makeText(this, userData, Toast.LENGTH_SHORT).show();
        }else {
            saveUserData(userData);
        }

    }

    private void saveUserData(String jsonUserData){
        String email, password, name, surname, group;

        try {
            JSONObject jsonObject = new JSONObject(jsonUserData);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject object = jsonArray.getJSONObject(0);

            email = object.getString("email");
            password = object.getString("password");
            name = object.getString("name");
            surname = object.getString("surname");
            group = object.getString("group");

            userSharedPreferences = getSharedPreferences(USER_DATA, MODE_PRIVATE);
            editor = userSharedPreferences.edit();

            editor.putString(USER_EMAIL, email).apply();
            editor.putString(USER_PASSWORD, password).apply();
            editor.putString(USER_NAME, name).apply();
            editor.putString(USER_SURNAME, surname).apply();
            editor.putString(USER_GROUP, group).apply();
            editor.putBoolean(USER_STATUS, true).apply();

            startActivity(new Intent(this, UserOrdersActivity.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {}
}
