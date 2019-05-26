package com.example.user.freebook;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    private Context context;
    private String method;

    private final String REGISTRATION_URL="http://188.230.98.137/registration.php";
    private final String LOGIN_URL="http://188.230.98.137/login.php";

    public BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        method = params[0];

        // Registration task
        if (method.equals("registration")){
            String email = params[1];
            String password = params[2];
            String name = params[3];
            String surname = params[4];
            String group = params[5];

            try {
                URL url = new URL(REGISTRATION_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data =
                        URLEncoder.encode("email","UTF-8") +  "=" + URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8") +  "=" + URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8") +  "=" + URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("surname","UTF-8") +  "=" + URLEncoder.encode(surname,"UTF-8")+"&"+
                        URLEncoder.encode("class_group","UTF-8") +  "=" + URLEncoder.encode(group,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                return "Account was created successfully.";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Login task
        if (method.equals("login")){
            String email = params[1];
            String password = params[2];

            try {
                URL url = new URL(LOGIN_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("email","UTF-8") +  "=" + URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8") +  "=" + URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
