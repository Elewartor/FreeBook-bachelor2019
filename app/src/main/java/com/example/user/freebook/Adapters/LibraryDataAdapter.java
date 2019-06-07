package com.example.user.freebook.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.freebook.Objects.LibraryObject;
import com.example.user.freebook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryDataAdapter extends ArrayAdapter<LibraryObject> {

    private  static  final String TAG = "LibraryDataAdapter";

    private Context context;
    private int resource;

    public LibraryDataAdapter(Context context, int resource, ArrayList<LibraryObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String book_id = getItem(position).getBook_id();
        String book_name = getItem(position).getBook_name();
        String book_type = getItem(position).getBook_type();
        String book_source = getItem(position).getBook_source();
        String book_publish_place = getItem(position).getBook_publish_place();
        String book_publisher = getItem(position).getBook_publisher();
        String book_year = getItem(position).getBook_year();
        String book_page_amount = getItem(position).getBook_page_amount();
        String library_book_gamout = getItem(position).getLibrary_book_gamout();
        String library_book_left = getItem(position).getLibrary_book_left();
        String author_name = getItem(position).getAuthor_name();
        String image_way = getItem(position).getImage_way();

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
                author_name,
                image_way);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tv_library_book_name = convertView.findViewById(R.id.tv_library_book_name);
        TextView tv_library_book_type = convertView.findViewById(R.id.tv_library_book_type);
        TextView tv_library_book_source = convertView.findViewById(R.id.tv_library_book_source);
        TextView tv_library_book_publish_place = convertView.findViewById(R.id.tv_library_book_publish_place);
        TextView tv_library_book_publisher = convertView.findViewById(R.id.tv_library_book_publisher);
        TextView tv_library_book_year = convertView.findViewById(R.id.tv_library_book_year);
        TextView tv_library_book_page_amount = convertView.findViewById(R.id.tv_library_book_page_amount);
        TextView tv_library_library_book_gamout = convertView.findViewById(R.id.tv_library_library_book_gamout);
        TextView tv_library_library_book_left = convertView.findViewById(R.id.tv_library_library_book_left);
        TextView tv_library_author_name = convertView.findViewById(R.id.tv_library_author_name);

        tv_library_book_name.setText(book_name);
        tv_library_book_type.setText(book_type);
        if(book_source == "null"){
            tv_library_book_source.setVisibility(View.INVISIBLE);
        }else {
            tv_library_book_source.setText(book_source);
        }
        tv_library_book_publish_place.setText(book_publish_place);
        tv_library_book_publisher.setText(book_publisher);
        tv_library_book_year.setText(book_year);
        tv_library_book_page_amount.setText(book_page_amount);
        tv_library_library_book_gamout.setText(library_book_gamout);
        tv_library_library_book_left.setText(library_book_left);
        tv_library_author_name.setText(author_name);

        ImageView iv_book_image = convertView.findViewById(R.id.iv_book_image);
        Picasso.get().load(image_way).into(iv_book_image);

        return convertView;
    }
}
