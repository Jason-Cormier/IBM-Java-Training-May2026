package IBM.Training.com.Day2;

public class Main {
	
	public static void main(String[] args) {
		Library library = new Library();
		
		library.addBook(new Book("Brave New World", "Aldous Huxley"));
		library.addBook(new Book("Moby-Dick", "Herman Melville"));
		library.addBook(new Book("The Odyssey", "Homer"));
		
		library.borrowBook("The Odyssey");
		
		library.showAllBooks();
		
		library.returnBook("The Odyssey");
		
		library.showAllBooks();
	}
	
}
