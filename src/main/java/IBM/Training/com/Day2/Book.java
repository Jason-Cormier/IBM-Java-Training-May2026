package IBM.Training.com.Day2;

public class Book {
	private String title;
	private String author;
	private boolean available;
	
	public Book (String t, String a) {
		title = t;
		author = a;
		available = true;
	}
	
	public void borrowBook() {
		if (available) {
			available = false;
			System.out.println(title + " borrowed successfully.");
		} else {
			System.out.println("Book is already borrowed.");
		}
	}
	
	public void returnBook() {
		available = true;
		System.out.println(title + " returned successfully.");
	}
	
	public void getInfo() {
		System.out.println("Title: " + this.title);
		System.out.println("Author: " + this.author);
		String avail = available ? "Yes" : "No";
		System.out.println("Available? " + avail);
		System.out.println();
	}
	
	public String getTitle() {
		return title;
	}
}
