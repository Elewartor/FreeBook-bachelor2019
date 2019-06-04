package com.example.user.freebook.Connections;

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
    private final String GET_USER_ORDERS_URL="http://188.230.98.137/get_orders_data.php";
    private final String GET_BOOK_BY_QR_DATA_URL="http://188.230.98.137/get_book_by_qr.php";
    private final String CREATE_ORDER_URL="http://188.230.98.137/create_order.php";
    private final String GET_USER_QRCODE_URL="http://188.230.98.137/get_user_qrcode.php";
    private final String GET_LIBRARY_DATA_URL="http://188.230.98.137/get_library.php";
    private final String GET_BOOK_AUTHORS_BY_BOOK_ID_URL ="http://188.230.98.137/get_book_authors.php";

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

        //Getting user personal QR Code by user id.
        if(method.equals("get_user_QRCode")){
            String studentEmail = params[1];

            try {
                URL url = new URL(GET_USER_QRCODE_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("student_email","UTF-8") +  "=" + URLEncoder.encode(studentEmail,"UTF-8");
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

        //User orders task
        if(method.equals("get_user_orders")){
            String studentEmail = params[1];

            try {
                URL url = new URL(GET_USER_ORDERS_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("student_email","UTF-8") +  "=" + URLEncoder.encode(studentEmail,"UTF-8");
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

        //Get book info by QR data
        if(method.equals("get_book_by_qr_data")){
            String book_id = params[1];

            try {
                URL url = new URL(GET_BOOK_BY_QR_DATA_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") +  "=" + URLEncoder.encode(book_id,"UTF-8");
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

        //Create order task
        if(method.equals("create_order")){
            String user_email = params[1];
            String book_id = params[2];

            try {
                URL url = new URL(CREATE_ORDER_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("bookId","UTF-8") +  "=" + URLEncoder.encode(book_id,"UTF-8")+"&"+
                        URLEncoder.encode("userEmail","UTF-8") +  "=" + URLEncoder.encode(user_email,"UTF-8");
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

        //Getting all library data
        if(method.equals("get_library_data")){
            try {
                URL url = new URL(GET_LIBRARY_DATA_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

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

        //Getting book authors data by book id
        if(method.equals("get_book_authors")){
            String book_id = params[1];

            try {
                URL url = new URL(GET_BOOK_AUTHORS_BY_BOOK_ID_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("book_id","UTF-8") +  "=" + URLEncoder.encode(book_id,"UTF-8");
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
