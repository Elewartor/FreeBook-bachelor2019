package com.example.user.freebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.user.freebook.Adapters.LibraryDataAdapter;
import com.example.user.freebook.Connections.BackgroundTask;
import com.example.user.freebook.Objects.LibraryObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LibraryActivity extends AppCompatActivity {

    private ListView lv_library_data;
    private LibraryDataAdapter libraryDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        try {
            getLibraryData();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getLibraryData() throws ExecutionException, InterruptedException, JSONException {
        lv_library_data = findViewById(R.id.lv_library_data);
        ArrayList<LibraryObject> libraryObjects = new ArrayList<>();

        String method = "get_library_data";
        String libraryResponse = new BackgroundTask(this).execute(method).get();

        JSONObject jsonObject = new JSONObject(libraryResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("server_response");

        String book_id;
        String book_name;
        String book_type;
        String book_source;
        String book_publish_place;
        String book_publisher;
        String book_year;
        String book_page_amount;
        String library_book_gamout;
        String library_book_left;
        String author_name;

        int counter=0;
        while (counter<jsonArray.length()){
            JSONObject jo = jsonArray.getJSONObject(counter);

            book_id = jo.getString("book_id");
            book_name = jo.getString("book_name");
            book_type = jo.getString("book_type");
            book_source = jo.getString("book_source");
            book_publish_place = jo.getString("book_publish_place");
            book_publisher = jo.getString("book_publisher");
            book_year = jo.getString("book_year");
            book_page_amount = jo.getString("book_page_amount");
            library_book_gamout = jo.getString("library_book_gamout");
            library_book_left = jo.getString("library_book_left");
            author_name = getBookAuthors(book_id);

            LibraryObject libraryObject = new LibraryObject(
                    book_id,
                    book_name,
                    book_type,
                    book_source,
                    book_publish_place,
                    book_publisher,
                    book_year,
                    book_page_amount,
                    library_book_gamout,
                    library_book_left,
                    author_name
            );

            libraryObjects.add(libraryObject);
            if(libraryObjects.size() != 0){
                libraryDataAdapter = new LibraryDataAdapter(this, R.layout.adapter_library_view, libraryObjects);
                lv_library_data.setAdapter(libraryDataAdapter);
            }else {
                //TODO Exception visual settings if user orders data is empty.
            }

            counter++;
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
}
