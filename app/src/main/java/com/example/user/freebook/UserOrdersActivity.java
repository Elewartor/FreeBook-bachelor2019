package com.example.user.freebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import com.example.user.freebook.Adapters.OrdersDataAdapter;
import com.example.user.freebook.Connections.BackgroundTask;
import com.example.user.freebook.Objects.OrderDataObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserOrdersActivity extends AppCompatActivity {

    private ListView listView;
    private OrdersDataAdapter ordersDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

        listView = findViewById(R.id.lv_user_orders_data);

        String userOrdersMethod = "get_user_orders";
        String studentEmail = getSharedPreferences(LoginActivity.USER_DATA, MODE_PRIVATE).getString(LoginActivity.USER_EMAIL, null);
        if (studentEmail!=null){
            try {
                listViewLoader(new BackgroundTask(this).execute(userOrdersMethod, studentEmail).get());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void listViewLoader(String userOrders) throws JSONException {

        ArrayList<OrderDataObject> orderDataObjects = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(userOrders);
        JSONArray jsonArray = jsonObject.getJSONArray("server_response");

        String book_id;
        String book_name;
        String book_type;
        String book_source;
        String book_publish_place;
        String book_publisher;
        String book_year;
        String book_page_amount;
        String order_order_date;
        String order_deadline_date;
        String order_days_left;
        String order_status;
        String book_authors;
        String image_way;

        int counter=0;
        while(counter<jsonArray.length()){
            JSONObject jo = jsonArray.getJSONObject(counter);

            book_id = jo.getString("book_id");
            book_name = jo.getString("book_name");
            book_type = jo.getString("book_type");
            book_source = jo.getString("book_source");
            book_publish_place = jo.getString("book_publish_place");
            book_publisher = jo.getString("book_publisher");
            book_year = jo.getString("book_year");
            book_page_amount = jo.getString("book_page_amount");
            order_order_date = jo.getString("order_order_date");
            order_deadline_date = jo.getString("order_deadline_date");
            order_days_left = jo.getString("order_days_left");
            order_status = jo.getString("order_status");
            book_authors = getBookAuthors(book_id);
            image_way = jo.getString("gallery_server_way");

            OrderDataObject orderDataObject = new OrderDataObject(
                    book_id,
                    book_name,
                    book_type,
                    book_source,
                    book_publish_place,
                    book_publisher,
                    book_year,
                    book_page_amount,
                    order_order_date,
                    order_deadline_date,
                    order_days_left,
                    order_status,
                    book_authors,
                    image_way);

            orderDataObjects.add(orderDataObject);

            counter++;
        }
        if(orderDataObjects.size() !=0) {
            ordersDataAdapter = new OrdersDataAdapter(this, R.layout.adapter_view_order_element, orderDataObjects);
            listView.setAdapter(ordersDataAdapter);
        }else{
            //TODO Exception visual settings if user orders data is empty.
        }

    }
    private String getBookAuthors(String bookId){
        String authors = "";
        String method = "get_book_authors";
        try {
            String authorsResponse = new BackgroundTask(this).execute(method, bookId).get();
            JSONObject jsonObject = new JSONObject(authorsResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");

            int counter = 0;
            while (counter<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(counter);
                authors += jo.getString("author_name");

                counter++;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.orders_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.on_account :
                startActivity(new Intent(this, UserProfileActivity.class));
                return true;
            case R.id.on_scanner:
                startActivity(new Intent(this, ScannerActivity.class));
                return true;
            case R.id.on_library:
                startActivity(new Intent(getApplicationContext(), LibraryActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {}
}
