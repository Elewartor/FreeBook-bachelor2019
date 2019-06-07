package com.example.user.freebook.Objects;

public class OrderDataObject {

    private String book_id;
    private String book_name;
    private String book_type;
    private String book_source;
    private String book_publish_place;
    private String book_publisher;
    private String book_year;
    private String book_page_amount;
    private String order_order_date;
    private String order_deadline_date;
    private String order_days_left;
    private String order_status;
    private String book_authors;
    private String image_way;

    public OrderDataObject(
            String book_id, String book_name,
            String book_type,
            String book_source,
            String book_publish_place,
            String book_publisher,
            String book_year,
            String book_page_amount,
            String order_order_date,
            String order_deadline_date,
            String order_days_left,
            String order_status, String book_authors, String image_way) {
        this.book_id = book_id;

        this.book_name = book_name;
        this.book_type = book_type;
        this.book_source = book_source;
        this.book_publish_place = book_publish_place;
        this.book_publisher = book_publisher;
        this.book_year = book_year;
        this.book_page_amount = book_page_amount;
        this.order_order_date = order_order_date;
        this.order_deadline_date = order_deadline_date;
        this.order_days_left = order_days_left;
        this.order_status = order_status;
        this.book_authors = book_authors;

        this.image_way = image_way;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_type() {
        return book_type;
    }

    public String getBook_source() {
        return book_source;
    }

    public String getBook_publish_place() {
        return book_publish_place;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public String getBook_year() {
        return book_year;
    }

    public String getBook_page_amount() {
        return book_page_amount;
    }

    public String getOrder_order_date() {
        return order_order_date;
    }

    public String getOrder_deadline_date() {
        return order_deadline_date;
    }

    public String getOrder_days_left() {
        return order_days_left;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getImage_way() {
        return image_way;
    }

    public String getBook_authors() {
        return book_authors;
    }

    public String getBook_id() {
        return book_id;
    }
}
