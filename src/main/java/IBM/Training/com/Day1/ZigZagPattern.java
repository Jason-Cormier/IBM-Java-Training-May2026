package IBM.Training.com.Day1;
import java.util.Scanner;

public class ZigZagPattern {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num = input.nextInt();
		zigzagPattern(num);
	}
	
	public static void zigzagPattern(int num) {
		for (int i = 0; i < num; i++) {
			int firstNum = i * num + 1;
			if (i % 2 == 0) {
				for (int j = 0; j < num; j++) {
					System.out.print(firstNum + j + " ");
				}
			} else {
				for (int j = 0; j < num; j++) {
					System.out.print(firstNum + num - 1 -  j + " ");
				}
			}
			System.out.println();
		}
	}
}
