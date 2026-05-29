package IBM.Training.com.Day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class MathActTest {

	@Test
	void should_ReturnCorrectAddition_When_AddedNumbers() {
		//given
		float a = 10;
		float b = 5;
		float expected = a + b;
		
		//when
		float actual = MathAct.add(a, b);
		
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnCorrectSubtraction_When_SubtractNumbers() {
		//given
		float a = 10;
		float b = 5;
		float expected = a - b;
		
		//when
		float actual = MathAct.subtract(a, b);
		
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnCorrectMultiplication_When_MultiplyNumbers() {
		//given
		float a = 10;
		float b = 5;
		float expected = a * b;
		
		//when
		float actual = MathAct.multiply(a, b);
		
		//then
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ReturnCorrectDivision_When_DivideNumbers() {
		//given
		float a = 10;
		float b = 5;
		float expected = a / b;
		
		//when
		float actual = MathAct.divide(a, b);
		
		//then
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ThrowException_When_DivideNumbersByZero() {
		//given
		float a = 10;
		float b = 0;
		
		//when
		Executable executable = () -> MathAct.divide(a, b);
		
		//then
		assertThrows(ArithmeticException.class, executable);		
	}
}
