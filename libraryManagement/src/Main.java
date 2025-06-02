import library.Book;
import library.Library;
import library.Member;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("HD&F", "Mark Fisher", "9780545791878");
        Book book2 = new Book("Catching Fire", "Suzanne Collins", "9780545586177");

        library.addBook(book1);
        library.addBook(book2);

        Member member1 = new Member("M001", "Ali");
        Member member2 = new Member("M002", "Tavakol");

        library.addMember(member1);
        library.addMember(member2);

        library.borrowBook("9780545791878", "M001");
        System.out.println(library.getLastTransaction("M001"));

        library.borrowBook("9780545586177", "M001");
        library.borrowBook("9780545791878", "M002");

        library.borrowBook("9780545586177", "M002");
        library.returnBook("9780545586177", "M002");

        System.out.println(library.getTranansactionByIndex(1));
        System.out.println(library.getTranansactionByIndex(2));

        library.returnBook("9780545791878", "M001");

        System.out.println(library.getTranansactionByIndex(3));

        System.out.println(library.getLastTransaction("M002"));
    }
}