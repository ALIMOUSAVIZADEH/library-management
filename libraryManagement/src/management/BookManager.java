package management;

import datastructures.maps.CustomHashMap;
import library.Book;
import library.Member;

public class BookManager {
    private CustomHashMap<String, Book> listOfBooks;

    private MemberManager memberManager;

    public BookManager(MemberManager memberManager) {
        listOfBooks = new CustomHashMap<>();
        this.memberManager = memberManager;
    }

    public void addBook(Book book) {
        listOfBooks.put(book.getIsbn(), book);
    }

    public Book getBookByIsbn(String isbn) {
        return listOfBooks.get(isbn);
    }

    public boolean isBookAvailable(String isbn) {
        return listOfBooks.get(isbn).isAvailable();
    }

    public void setBookAvailability(String isbn, boolean available) {
        listOfBooks.get(isbn).setAvailable(available);
    }

    public void addToWaitlist(String isbn, Member member) {
        listOfBooks.get(isbn).addToWaitlist(member);
    }

    public Member getNextFromWaitlist(String isbn) {
        return listOfBooks.get(isbn).getNextInWaitlist();
    }

    public boolean hasWaitlist(String isbn) {
        return listOfBooks.get(isbn).hasWaitlist();
    }
}