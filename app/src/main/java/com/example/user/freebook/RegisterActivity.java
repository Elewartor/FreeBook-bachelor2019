package com.example.user.freebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.freebook.Connections.BackgroundTask;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private EditText et_accept_password;
    private EditText et_name;
    private EditText et_surname;
    private EditText et_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_accept_password = findViewById(R.id.et_acceptpassword);
        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        et_group = findViewById(R.id.et_group);

    }

    public void onCreateAccountPush(View view){
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String acceptPassword = et_accept_password.getText().toString();
        String name = et_name.getText().toString();
        String surname = et_surname.getText().toString();
        String group = et_group.getText().toString();

        if(password.equals(acceptPassword)){
            String method = "registration";
            String result = "";
            try {
                result = new BackgroundTask(this).execute(method, email, password, name, surname, group).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            Toast.makeText(this, "Passwords do no match", Toast.LENGTH_SHORT).show();
        }
    }


}
