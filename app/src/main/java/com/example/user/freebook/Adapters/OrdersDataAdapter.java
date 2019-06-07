package com.example.user.freebook.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.freebook.Objects.OrderDataObject;
import com.example.user.freebook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrdersDataAdapter extends ArrayAdapter<OrderDataObject> {

    private  static  final String TAG = "OrdersDataAdapter";

    private Context context;
    private int resourse;

    public OrdersDataAdapter(Context context, int resource, ArrayList<OrderDataObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourse = resource;
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
        String order_order_date = getItem(position).getOrder_order_date();
        String order_deadline_date = getItem(position).getOrder_deadline_date();
        String order_days_left = getItem(position).getOrder_days_left();
        String order_status = getItem(position).getOrder_status();
        String book_authors = getItem(position).getBook_authors();
        String image_way = getItem(position).getImage_way();

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

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resourse, parent, false);

        TextView tv_book_name = convertView.findViewById(R.id.tv_book_name);
        TextView tv_book_type = convertView.findViewById(R.id.tv_book_type);
        TextView tv_book_source = convertView.findViewById(R.id.tv_book_source);
        TextView tv_book_publish_place = convertView.findViewById(R.id.tv_book_publish_place);
        TextView tv_book_publisher = convertView.findViewById(R.id.tv_book_publisher);
        TextView tv_book_year = convertView.findViewById(R.id.tv_book_year);
        TextView tv_book_page_amount = convertView.findViewById(R.id.tv_book_page_amount);
        TextView tv_order_order_date = convertView.findViewById(R.id.tv_order_order_date);
        TextView tv_order_deadline_date = convertView.findViewById(R.id.tv_order_deadline_date);
//        TextView tv_order_days_left = convertView.findViewById(R.id.tv_order_days_left);
        TextView tv_order_status = convertView.findViewById(R.id.tv_order_status);
        TextView tv_book_authors = convertView.findViewById(R.id.tv_authors);

        switch (order_status){
            case "0":
                order_status = "completed";
                tv_order_status.setTextColor(Color.GREEN);
                break;
            case "1":
                order_status = "using";
                tv_order_status.setTextColor(Color.BLUE);
                break;
            case "2":
                order_status = "outstanding";
                tv_order_status.setTextColor(Color.RED);
                break;
        }

        tv_book_name.setText(book_name);
        tv_book_type.setText(book_type);
        if(book_source == "null"){
            tv_book_source.setVisibility(View.INVISIBLE);
        }else {
            tv_book_source.setText(book_source);
        }
        tv_book_publish_place.setText(book_publish_place);
        tv_book_publisher.setText(book_publisher);
        tv_book_year.setText(book_year);
        tv_book_page_amount.setText(book_page_amount);
        tv_order_order_date.setText(order_order_date);
        tv_order_deadline_date.setText(order_deadline_date);
//        tv_order_days_left.setText(order_days_left);
        tv_order_status.setText(order_status);
        tv_book_authors.setText(book_authors);

        ImageView iv_book_image = convertView.findViewById(R.id.iv_book_image);
        Picasso.get().load(image_way).into(iv_book_image);

        return convertView;
    }
}
