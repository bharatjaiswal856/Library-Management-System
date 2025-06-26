# Library Management System

---

A simple, console-based **Library Management System** developed in Java. This project demonstrates fundamental Object-Oriented Programming (OOP) concepts, including classes, objects, encapsulation, and data structures like `HashMap` and `ArrayList`. It allows for managing books, users, and borrowing/returning operations within a library setting.

## Features

* **Book Management:**
    * Add new books with title, author, ISBN, and initial copy count.
    * Add more copies to existing books.
    * Display all books in the library.
    * Display only available books.
    * Remove entire book titles (only if no copies are currently borrowed).
    * Search for a book by ISBN and view its details, including who has borrowed it.
* **User Management:**
    * Add new users with a unique user ID and name.
    * Display all registered users and their borrowed books.
    * Delete users (automatically returns all borrowed books before deletion).
    * Search for a user by ID and view their details and current borrowed books.
* **Borrowing & Returning:**
    * Issue books to registered users.
    * Return borrowed books.

    ---

##  Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need to have **Java Development Kit (JDK) 8 or higher** installed on your system.

* [Download JDK](https://www.oracle.com/java/technologies/downloads/)

### Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/bharatjaiswal856/Library-Management-System.git
    ```

    

2.  **Navigate to the project directory:**

    ```bash
    cd Library-Management-System
    ```

3.  **Compile the Java files:**

    ```bash
    javac *.java
        OR
    javac Book.java
    javac User.java
    javac Library.java
    javac LibraryManagement.java
    ```


4.  **Run the application:**

    ```bash
    java LibraryManagement
    ```

    The application will start, and you'll see the main menu in your console.

    ---

## Example Usage

Once you run the application, you'll be presented with a menu:

New book 'The Lord of the Rings' added with 3 copies.
New book 'Pride and Prejudice' added with 2 copies.
New book '1984' added with 5 copies.
New book 'To Kill a Mockingbird' added with 1 copies.
2 copies of 'The Lord of the Rings' added. Total: 5, Available: 5
New book 'Dear Life' added with 1 copies.
User added: Bharat Jaiswal
User added: Shiva Jaiswal
User added: Suraj Jaiswal
One copy of 'The Lord of the Rings' issued to user 'Bharat Jaiswal'.
Remaining available copies of 'The Lord of the Rings': 4
One copy of 'The Lord of the Rings' issued to user 'Shiva Jaiswal'.
Remaining available copies of 'The Lord of the Rings': 3
One copy of 'Pride and Prejudice' issued to user 'Bharat Jaiswal'.
Remaining available copies of 'Pride and Prejudice': 1
One copy of '1984' issued to user 'Suraj Jaiswal'.
Remaining available copies of '1984': 4

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 1

--- Available Books ---
Title: The Lord of the Rings, Author: J.R.R. Tolkien, ISBN: 978-0618260274 (Available: 3/5)
Title: Pride and Prejudice, Author: Jane Austen, ISBN: 978-0141439518 (Available: 1/2)
Title: 1984, Author: George Orwell, ISBN: 978-0451524935 (Available: 4/5)
Title: Dear Life, Author: Alice Munro, ISBN: 978-0804168915 (Available: 1/1)
Title: To Kill a Mockingbird, Author: Harper Lee, ISBN: 978-0061120084 (Available: 1/1)

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 2

--- Registered Users ---
User ID: U003, Name: Suraj Jaiswal, Books Borrowed: 1
  Borrowed Book Titles (one entry per borrowed copy):
    - 1984 (ISBN: 978-0451524935)
User ID: U002, Name: Shiva Jaiswal, Books Borrowed: 1
  Borrowed Book Titles (one entry per borrowed copy):
    - The Lord of the Rings (ISBN: 978-0618260274)
User ID: U001, Name: Bharat Jaiswal, Books Borrowed: 2
  Borrowed Book Titles (one entry per borrowed copy):
    - The Lord of the Rings (ISBN: 978-0618260274)
    - Pride and Prejudice (ISBN: 978-0141439518)

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 11
Enter User ID to find: U003

--- User Details ---
User ID: U003
Name: Suraj Jaiswal
Items currently borrowed by Suraj Jaiswal: 1
  - 1984 (ISBN: 978-0451524935) - 1 copy

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 4
Enter ISBN of book to return: 978-0451524935
Enter User ID returning the book: U003
One copy of '1984' returned by user 'Suraj Jaiswal'.
Now available copies of '1984': 5

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 11
Enter User ID to find: U003

--- User Details ---
User ID: U003
Name: Suraj Jaiswal
Suraj Jaiswal has no books currently borrowed.

--- Library Menu ---
1. Display Available Books
2. Display All Users
3. Issue Book
4. Return Book
5. Add New Book or Add Copies
6. Add New User
7. Remove Book (entire title)
8. Delete User
9. Display All Book Titles
10. Find Book by ISBN (and who has it)
11. Find User by ID (and their borrowed books)
0. Exit
Enter your choice: 0
Exiting Library System. Goodbye!

---

## Project Structure

The project is organized into several Java classes, each responsible for a specific part of the library system:

* `Book.java`: Represents a book with properties like title, author, ISBN, total copies, and available copies. It includes methods for borrowing and returning single copies, and adding more copies.
* `User.java`: Represents a library user with a unique ID, name, and a list of books they have borrowed.
* `Library.java`: The core class that manages collections of `Book` and `User` objects. It handles adding/removing books and users, issuing/returning books, and providing search functionalities.
* `LibraryManagement.java`: Contains the `main` method, which serves as the entry point of the application. It provides the command-line interface for users to interact with the library system.

---

## Contributing

This project is a basic implementation of a library management system. Feel free to fork the repository and contribute by:

* Adding more features (e.g., persistent storage, search by title/author).
* Improving error handling and user input validation.
* Refactoring the code for better modularity.
* Adding unit tests.

---

### Author

* [Bharat Jaiswal](https://github.com/bharatjaiswal856/Library-Management-System)

---

