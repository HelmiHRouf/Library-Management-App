package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class BookShelf {
    private ArrayList<Book> bookList;

    // EFFECTS: construct a new BookShelf
    public BookShelf() {
        bookList = new ArrayList<>();
    }


    // EFFECTS: return all books based on the given category
    public List<Book> findBookCategory(String pick) {
        List<Book> bookListnew = new ArrayList<>();
        for (Book book : bookList) {
            String category = book.getCategory();
            if (category.equals(pick)) {
                bookListnew.add(book);
            }
        }
        return bookListnew;
    }

    // EFFECTS: return all books based on the given years
    public List<Book> findBookYear(int year) {
        List<Book> bookListNew = new ArrayList<>();
        for (Book book : bookList) {
            int yearPublished = book.getYearPublished();
            if (yearPublished == year) {
                bookListNew.add(book);
            }
        }
        return bookListNew;
    }

    // MODIFIES: this
    // EFFECTS: add book to the BookShelf
    public void addBook(Book book) {
        bookList.add(book);
    }

    // MODIFIES: this
    // EFFECTS: remove book to the BookShelf
    public void remove(int index) {
        bookList.remove(index);
    }

    // EFFECTS: pass back all book
    public List<Book> getBookList() {
        return bookList;
    }

    // EFFECTS: get all borrowed books
    public List<Book> getBorrowedBooks() {
        List<Book> listBorrowedBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getIsBorrowed()) {
                listBorrowedBooks.add(book);
            }
        }
        return listBorrowedBooks;
    }

    // EFFECTS: find book given isbn
    public Book findBookIsbn(int isbn) {
        for (Book book : bookList) {
            if (isbn == book.getNumIsbn()) {
                return book;
            }
        }
        return null;
    }

    // EFFECTS: get all the book category
    public List<String> getBooksCategory() {
        List<String> bookTitle = new ArrayList<>();
        for (Book book : bookList) {
            bookTitle.add(book.getCategory());
        }
        List<String> bookTitleWODuplicates =  new ArrayList<>(new HashSet<>(bookTitle));
        return bookTitleWODuplicates;
    }

    // EFFECT: Decide whether the book can be borrowed
    public boolean isBookBorrowed(int isbn) {
        for (Book book : bookList) {
            if (isbn == book.getNumIsbn()) {
                return book.getIsBorrowed();
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: borrow book from the bookshelf
    public void borrowBook(User user, int isbn) {
        Book borrowedBook = findBookIsbn(isbn);
        user.setBookborrowed(borrowedBook);
        borrowedBook.setBorrowed(true);
    }

    // MODIFIES: this
    // EFFECTS: return book
    public void returnBook(User user) {
        Book borrowedBook = user.getBookborrowed();
        user.setBookborrowed(null);
        borrowedBook.setBorrowed(false);
    }


}
