package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Book> issuedBooks;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void display() {
        System.out.printf("User ID: %s | Name: %s | Books Issued: %d\n", id, name, issuedBooks.size());
        for (Book b : issuedBooks) b.display();
    }
}
