package util;

public class Member {
    private String id;
    private String name;
    private String bookLoaning; // ISBN of the book currently loaned, null if none

    public Member() {
        this.id = "";
        this.name = "";
        this.bookLoaning = null;
    }

    public Member(String id, String name, String bookLoaning) {
        this.id = id;
        this.name = name;
        this.bookLoaning = bookLoaning;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBookLoaning() {
        return bookLoaning;
    }
    public void setBookLoaning(String bookLoaning) {
        this.bookLoaning = bookLoaning;
    }

    @Override
    public String toString() {
        return "Member [id: " + id + ", name: " + name + ", bookLoaning: " + bookLoaning + "]";
    }
}
