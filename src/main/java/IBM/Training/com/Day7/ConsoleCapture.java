package IBM.Training.com.Day7;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleCapture {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public void start() {
        System.setOut(new PrintStream(outputStream));
    }

    public String stop() {
        System.setOut(originalOut);
        return outputStream.toString();
    }
}