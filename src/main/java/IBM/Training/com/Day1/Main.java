package IBM.Training.com.Day1;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Blackjack
		System.out.println("Blackjack");
		System.out.println(blackjack(1, 2));
		System.out.println(blackjack(21, 22));
		System.out.println(blackjack(22, 22));
		System.out.println(blackjack(2, 10));
		System.out.println();
		
		// Days of the Week
		System.out.println("Days of the Week");
		System.out.print("Enter a number: ");
		int num = input.nextInt();
		System.out.println(getDay(num));
		System.out.println(getDayPatternMatch(num));
		System.out.println();
		
		// Number Pyramid
		System.out.println("Number Pyramid");
		numPyramid();
	}
	
	public static int blackjack(int a, int b) {
		a = a > 21 ? 0 : a;
		b = b > 21 ? 0 : b;
		return a > b ? a : b;
	}
	
	public static String getDay(int day) {
		switch (day) {
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
			case 7:
				return "Sunday";
			default:
				return "Invalid day number.";
		}
	}
	
	public static String getDayPatternMatch(int day) {
		return switch (day) {
			case 1 -> "Monday";
			case 2 -> "Tuesday";
			case 3 -> "Wednesday";
			case 4 -> "Thursday";
			case 5 -> "Friday";
			case 6 -> "Saturday";
			case 7 -> "Sunday";
			default -> "Invalid day number.";
		};
	}	
	
	public static void numPyramid() {
		Scanner input = new Scanner(System.in);
		int num = 0;
		while (num < 1 || num > 20) {
			System.out.print("Enter a number (1-20): ");
			num = input.nextInt();
		}
		for (int i = 0; i < num + 1; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
