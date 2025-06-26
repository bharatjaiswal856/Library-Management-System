public class Book {
    private String title;
    private String author;
    private String isbn; // Unique identifier for the book title
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String author, String isbn, int initialCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        if (initialCopies < 0) {
            throw new IllegalArgumentException("Initial copies cannot be negative.");
        }
        this.totalCopies = initialCopies;
        this.availableCopies = initialCopies;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    // Methods to manage borrowing and returning a single copy
    public boolean borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false; // No copies available
    }

    public boolean returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            return true;
        }
        // This case means an attempt to return more copies than originally existed,
        System.err.println("Warning: Attempted to return a copy for '" + title + "' but all original copies are already available.");
        return false;
    }

    // Method to add more copies of this book
    public void addCopies(int numberOfCopiesToAdd) {
        if (numberOfCopiesToAdd > 0) {
            this.totalCopies += numberOfCopiesToAdd;
            this.availableCopies += numberOfCopiesToAdd;
        } else {
            System.out.println("Cannot add zero or negative copies.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
                " (Available: " + availableCopies + "/" + totalCopies + ")";
    }
}