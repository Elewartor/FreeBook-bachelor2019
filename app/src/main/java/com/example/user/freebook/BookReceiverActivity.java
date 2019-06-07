package com.example.user.freebook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.freebook.Connections.BackgroundTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class BookReceiverActivity extends AppCompatActivity {

    private TextView
            tv_book_name,
            tv_book_type,
            tv_book_source,
            tv_book_publish_place,
            tv_book_publisher,
            tv_book_year,
            tv_book_page_amount,
            tv_library_book_gamout,
            tv_library_book_left,
            tv_author_name;

    private String book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_receiver);

        tv_book_name = findViewById(R.id.book_name);
        tv_book_type = findViewById(R.id.book_type);
        tv_book_source = findViewById(R.id.book_source);
        tv_book_publish_place = findViewById(R.id.book_publish_place);
        tv_book_publisher = findViewById(R.id.book_publisher);
        tv_book_year = findViewById(R.id.book_year);
        tv_book_page_amount = findViewById(R.id.book_page_amount);
        tv_library_book_gamout = findViewById(R.id.library_book_gamout);
        tv_library_book_left = findViewById(R.id.library_book_left);
        tv_author_name = findViewById(R.id.author_name);

        book_id = getIntent().getStringExtra(ScannerActivity.ENCODED_DATA);
        try {
            getBookByQR(book_id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Current book does not exist.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), UserOrdersActivity.class));
            finish();
        }

    }

    private void getBookByQR(String id) throws ExecutionException, InterruptedException, JSONException {
        String method = "get_book_by_qr_data";
        String bookInfo = new BackgroundTask(this).execute(method, id).get();

        JSONObject jsonObject = new JSONObject(bookInfo);
        JSONArray jsonArray = jsonObject.getJSONArray("book_info");

        String authors = "";
        int counter = 0;
        while (counter < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(counter);

            String author = jo.getString("author.author_name");
            authors += author;

            counter++;
        }

        JSONObject jo = jsonArray.getJSONObject(0);

        String book_name = jo.getString("book.book_name");
        String book_type = jo.getString("book.book_type");
        String book_source = jo.getString("book.book_source");
        String book_publish_place = jo.getString("book.book_publish_place");
        String book_publisher = jo.getString("book.book_publisher");
        String book_year = jo.getString("book.book_year");
        String book_page_amount = jo.getString("book.book_page_amount");
        String library_book_gamout = jo.getString("library.library_book_gamout");
        String library_book_left = jo.getString("library.library_book_left");
        String author_name = authors;
        String image_way = jo.getString("gallery_server_way");

        if (!library_book_left.equals("0")) {
            tv_library_book_left.setTextColor(Color.GREEN);
        } else {
            tv_library_book_left.setTextColor(Color.RED);
            Button bt_submit = findViewById(R.id.bt_submit_order);
            bt_submit.setActivated(false);
        }

        tv_book_name.setText(book_name);
        tv_book_type.setText(book_type);
        if (book_source.equals("null")) {
            tv_book_source.setVisibility(View.INVISIBLE);
        } else {
            tv_book_source.setText(book_source);
        }
        tv_book_publish_place.setText(book_publish_place);
        tv_book_publisher.setText(book_publisher);
        tv_book_year.setText(book_year);
        tv_book_page_amount.setText(book_page_amount);
        tv_library_book_gamout.setText(library_book_gamout);
        tv_library_book_left.setText(library_book_left);
        tv_author_name.setText(author_name);

        ImageView iv_book_image = findViewById(R.id.iv_book_image);
        Picasso.get().load(image_way).into(iv_book_image);

    }

    public void onSubmit(View view){
        String method = "create_order";
        String user_email = getSharedPreferences(LoginActivity.USER_DATA, MODE_PRIVATE).getString(LoginActivity.USER_EMAIL, null);
        if(user_email != null){
            try {
                String result = new BackgroundTask(this).execute(method, user_email, book_id).get();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserOrdersActivity.class));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onRefuse(View view){
        startActivity(new Intent(getApplicationContext(), UserOrdersActivity.class));
        finish();
    }
}
