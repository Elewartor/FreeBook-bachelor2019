package com.example.user.freebook.Objects;

public class LibraryObject {

    private String book_id;
    private String book_name;
    private String book_type;
    private String book_source;
    private String book_publish_place;
    private String book_publisher;
    private String book_year;
    private String book_page_amount;
    private String library_book_gamout;
    private String library_book_left;
    private String author_name;

    public LibraryObject(String book_id, String book_name, String book_type, String book_source, String book_publish_place, String book_publisher, String book_year, String book_page_amount, String library_book_gamout, String library_book_left, String author_name) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_type = book_type;
        this.book_source = book_source;
        this.book_publish_place = book_publish_place;
        this.book_publisher = book_publisher;
        this.book_year = book_year;
        this.book_page_amount = book_page_amount;
        this.library_book_gamout = library_book_gamout;
        this.library_book_left = library_book_left;
        this.author_name = author_name;
    }

    public String getBook_id() {
        return book_id;
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

    public String getLibrary_book_gamout() {
        return library_book_gamout;
    }

    public String getLibrary_book_left() {
        return library_book_left;
    }

    public String getAuthor_name() {
        return author_name;
    }
}
