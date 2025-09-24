package util;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import util.Book;
import util.Member;

//i havent covered the scenario of a member loaning a book then deleting the member. the book still appears to be loaned out
//similarly, if a book is loaned out then deleted, the member still appears to be holding the book 
//otherwise its decently functional for a simple library system


public class Library {
    Scanner scan = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    //import/export data to these files
    private final String BOOK_FILE = "books.txt";
    private final String MEMBER_FILE = "members.txt";

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        loadBooks();
        loadMembers();

        // If library is empty, add some sample books
        if (books.isEmpty()) {
            books.add(new Book("ISBN1", "Book 1", "John Smith", "Programming", 2018));
            books.add(new Book("ISBN2", "Book 2", "Tom Parker", "Business", 2005));
            books.add(new Book("ISBN3", "Book 3", "Brian Johnson", "Communication", 2006));
        }

        // If members list is empty, add some sample members
        if (members.isEmpty()) {
            members.add(new Member("M001", "Alice Johnson", null));
            members.add(new Member("M002", "Bob Smith", null));
        }
    }


    // ---------- Book Methods (add, remove, find, display all) ----------
    public void addBook() {
        System.out.print("Enter Book INFO: \nISBN: ");
        String isbn = scan.nextLine();
        if (findBook(isbn) != null) {
            System.out.println("ERROR- A book with this ISBN already exists.");
            return;
        }

        System.out.print("Title: ");

        String title = scan.nextLine();

        System.out.print("Author: ");
        String author = scan.nextLine();

        System.out.print("Genre: ");
        String genre = scan.nextLine();
        
        System.out.print("Year: ");
        int year = Integer.parseInt(scan.nextLine());

        books.add(new Book(isbn, title, author, genre, year));
        System.out.println("Book added: " + title);
    }

    public void removeBook() {
        System.out.print("Enter Book ISBN to remove: ");
        String isbn = scan.nextLine();

        //we have a find function already, resue it here
        Book book = findBook(isbn);

        if (book != null) {
            books.remove(book);
            System.out.println("Book removed: " + book.getTitle());
        } else {
            System.out.println("Book not found.");
        }
    }

    //---
    public Book findBook() {
        System.out.print("Enter Book ISBN to find: ");
        String isbn = scan.nextLine();
        Book book = findBook(isbn);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found.");
        }
        return book;
    }

    // Overloaded method to find a book by ISBN
    public Book findBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                return b;
            }
        }
        return null;
    }
    //---

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }


    // ---------- Member Methods (add, remove, find, display all) ----------
    public void addMember() {
    System.out.print("Member ID: ");
    String id = scan.nextLine();
    if (findMember(id) != null) {
        System.out.println("ERROR- A member with this ID already exists.");
        return;
    }

    System.out.print("Name: ");
    String name = scan.nextLine();

    // New members are not loaning any book yet
    members.add(new Member(id, name, null));
    System.out.println("Member added: " + name);
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (int i = 0; i < members.size(); i++) {
                System.out.println(members.get(i));
            }
        }
    }

    //---
    public Member findMember() {
        System.out.print("Enter Member ID to find: ");
        String id = scan.nextLine();
        Member member = findMember(id);
        if (member != null) {
            System.out.println("Member found: " + member);
        } else {
            System.out.println("Member not found.");
        }
        return member;
    }

    // Overloaded method to find a member by ID
    public Member findMember(String id) {
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            if (m.getId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }
    //---

    public void removeMember() {
        System.out.print("Enter Member ID to remove: ");
        String id = scan.nextLine();
        Member member = findMember(id);
        if (member != null) {
            members.remove(member);
            System.out.println("Member removed: " + member.getName());
        } else {
            System.out.println("Member not found.");
        }
    }

    // ---------- Transactions (Loan, Return, Availability) ----------
    public void loanBook() {
        System.out.print("Enter Book ISBN to loan: ");
        String isbn = scan.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scan.nextLine();

        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book already on loan.");
            return;
        }
        if (member.getBookLoaning() != null) {
            System.out.println("Member already has a book on loan.");
            return;
        }

        book.setAvailable(false);
        member.setBookLoaning(isbn);
        System.out.println("Book loaned: " + book.getTitle() + " to " + member.getName());
    }

    public void returnBook() {
        System.out.print("Enter Book ISBN to return: ");
        String isbn = scan.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scan.nextLine();
    
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid return request.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book was not on loan.");
        } else if (!isbn.equals(member.getBookLoaning())) {
            System.out.println("This member did not loan this book.");
        } else {
            book.setAvailable(true);
            member.setBookLoaning(null);
            System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
        }
    }





    // ---------- File Handling ----------

    // [LOADING / IMPORTING] DATA FROM FILES
    public void loadBooks() {
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String isbn = fields[0];
                    String title = fields[1];
                    String author = fields[2];
                    String genre = fields[3];
                    int year = Integer.parseInt(fields[4]); //parseInt converts text to an integer
                
                    Book book = new Book(isbn, title, author, genre, year);
                    book.setAvailable(fields.length > 5 ? Boolean.parseBoolean(fields[5]) : true);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("No books found. Starting fresh.");
        }
    }

    public void loadMembers() {
        members.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String id = fields[0];
                    String name = fields[1];
                    String bookLoaning = (fields.length > 2 && !"null".equals(fields[2])) ? fields[2] : null;
                    members.add(new Member(id, name, bookLoaning));
                }
            }
        } catch (IOException e) {
            System.out.println("No members found. Starting fresh.");
        }
    }


    // [SAVING / EXPORTING] DATA TO FILES
    public void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book b : books) {
                pw.println(String.join(",", b.getIsbn(), b.getTitle(), b.getAuthor(), b.getGenre(),
                        String.valueOf(b.getYear()), String.valueOf(b.isAvailable())));
            }
        } catch (IOException e) {
            System.out.println("Failed to save books.");
        }
    }

    public void saveMembers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                pw.println(String.join(",", m.getId(), m.getName(),
                        m.getBookLoaning() == null ? "null" : m.getBookLoaning()));
            }
        } catch (IOException e) {
            System.out.println("Failed to save members.");
        }
    }

    public void saveAll() {
        saveBooks();
        saveMembers();

        System.out.println("Data exported successfully to books.txt and members.txt");
    }
    
    public void loadAll() {
        loadBooks();
        loadMembers();

        System.out.println("Data imported successfully from books.txt and members.txt");
        System.out.println("Current books:");
        displayAllBooks();
        System.out.println("Current members:");
        displayAllMembers();
    }
}