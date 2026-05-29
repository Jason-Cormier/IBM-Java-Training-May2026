package IBM.Training.com.Day2;
import java.util.ArrayList;

public class Library {
	private ArrayList<Book> books = new ArrayList<>();
	
	public void addBook(Book b) {
		books.add(b);
	}
	
	public void showAllBooks() {
		for (Book b : books) {
			b.getInfo();
		}
	}
	
	public void borrowBook(String title) {
		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				b.borrowBook();
				return;
			}
			System.out.println("Book not found.");
		}
	}
	
	public void returnBook(String title) {
		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				b.returnBook();
				return;
			}
			System.out.println("Book not found.");
		}
	}
}
