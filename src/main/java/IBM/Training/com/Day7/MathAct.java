package IBM.Training.com.Day7;

public class MathAct {
	public static float add (float a, float b) {
		return a + b;
	}
	
	public static float subtract (float a, float b) {
		return a - b;
	}
	
	public static float multiply (float a, float b) {
		return a * b;
	}
	
	public static float divide (float a, float b) {
		if (b == 0) {
			throw new ArithmeticException("Should not divide by zero.");
		}
		return a / b;
	}
}
