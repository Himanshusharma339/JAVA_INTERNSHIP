package model;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean issued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public String getId() {
        return id;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issue() {
        this.issued = true;
    }

    public void returnBook() {
        this.issued = false;
    }

    public void display() {
        System.out.printf("Book ID: %s | Title: %s | Author: %s | %s\n",
                id, title, author, issued ? "Issued" : "Available");
    }
}
