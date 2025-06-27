package manager;

import model.Book;
import model.User;

import java.util.*;

public class LibraryManager {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            showMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addUser();
                case 3 -> viewBooks();
                case 4 -> viewUsers();
                case 5 -> issueBookToUser();
                case 6 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        } while (choice != 6);
    }

    private void showMenu() {
        System.out.println("""
        === Book & User Management System ===
        1. Add Book
        2. Add User
        3. View Books
        4. View Users
        5. Issue Book to User
        6. Exit
        Enter your choice:""");
    }

    private void addBook() {
        System.out.print("Book ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private void addUser() {
        System.out.print("User ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        users.add(new User(id, name));
        System.out.println("User added successfully.");
    }

    private void viewBooks() {
        System.out.println("--- Book List ---");
        if (books.isEmpty()) System.out.println("No books found.");
        else books.forEach(Book::display);
    }

    private void viewUsers() {
        System.out.println("--- User List ---");
        if (users.isEmpty()) System.out.println("No users found.");
        else users.forEach(User::display);
    }

    private void issueBookToUser() {
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine();
        Book book = books.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued.");
            return;
        }

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        User user = users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        book.issue();
        user.issueBook(book);
        System.out.println("Book issued to user.");
    }
}
