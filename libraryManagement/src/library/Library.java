package library;

import management.BookManager;
import management.MemberManager;
import management.TransactionManager;

public class Library {
    private BookManager bookManager;
    private MemberManager memberManager;
    private TransactionManager transactionManager;

    public Library() {
        this.memberManager = new MemberManager();
        this.bookManager = new BookManager(memberManager);
        this.transactionManager = new TransactionManager();
    }

    public void addBook(Book book) {
        bookManager.addBook(book);
    }

    public void addMember(Member member) {
        memberManager.addMember(member);
    }

    public void borrowBook(String isbn, String memberId) {


        if (bookManager.isBookAvailable(isbn)) {

            if (memberManager.getLastTransaction(memberId)!= null &&
                    memberManager.getLastTransaction(memberId).getType().equals("BORROW")){

                System.out.println("First you have to return the borrowed book: " +
                        memberManager.getLastTransaction(memberId).getBookTitle());

            } else {
                bookManager.setBookAvailability(isbn, false);
                Transaction transaction = transactionManager.addTransaction(bookManager.getBookByIsbn(isbn).getTitle(),
                        memberManager.getMember(memberId).getName(),
                        "BORROW");
                memberManager.recordTransaction(memberId, transaction);
            }
        } else {
            System.out.println("The requested book with code " + isbn + " is not available in the library.");
            bookManager.addToWaitlist(isbn, memberManager.getMember(memberId));
        }
    }

    public void returnBook(String isbn, String memberId) {

        bookManager.setBookAvailability(isbn, true);
        Transaction transaction = transactionManager.addTransaction(bookManager.getBookByIsbn(isbn).getTitle(),
                memberManager.getMember(memberId).getName(),
                "RETURN");
        memberManager.recordTransaction(memberId, transaction);

        if (bookManager.hasWaitlist(isbn)) {
            Member nextMember = bookManager.getNextFromWaitlist(isbn);
            if (nextMember != null) {
                borrowBook(isbn, nextMember.getMemberId());
            }
        }
    }

    public Transaction getLastTransaction(String memberId) {
        return memberManager.getLastTransaction(memberId);
    }

    public Transaction getTranansactionByIndex(int index) {
        return transactionManager.getTransactionByIndex(index);
    }
}