package IBM.Training.com.Day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogAnalyzerTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private LogAnalyzer la = new LogAnalyzer();

    @BeforeEach
    void setup() throws Exception {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Files.deleteIfExists(Path.of("src/main/resources/summary.txt"));
    }

    @AfterEach
    void cleanup() throws Exception {
        System.setOut(originalOut);

        Files.deleteIfExists(Path.of("src/main/resources/summary.txt"));
    }
    
    //should_ReturnEqual_IfSummaryOutputIsEquivalent
    @Test
    void exec001() throws Exception {
        Path testFolder = Path.of("src/test/resources/exec001");

        String expectedFile = Files.readString(testFolder.resolve("summary_001.txt"));

        String file = testFolder.resolve("server_001.log").toString();

        LogAnalyzer.main(new String[] { file });

        String actualFile = Files.readString(Path.of("src/main/resources/summary.txt"));

        assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());

        String consoleOutput = outputStream.toString();

        assertTrue(consoleOutput.contains("Analysis complete. Summary written to summary.txt"));
    }
    
    //should_SkipMalformedLines_IfInvalidEntriesExist
    @Test
    void exec002() throws Exception {
        Path testFolder = Path.of("src/test/resources/exec002");

        String expectedFile = Files.readString(testFolder.resolve("summary_002.txt"));

        String file = testFolder.resolve("server_002.log").toString();

        LogAnalyzer.main(new String[] { file });

        String actualFile = Files.readString(Path.of("src/main/resources/summary.txt"));

        assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());

        String consoleOutput = outputStream.toString();

        assertTrue(consoleOutput.contains("Skipping malformed line"));

        assertTrue(consoleOutput.contains("Analysis complete. Summary written to summary.txt"));
    }
    
    //should_NotCreateSummaryFile_IfLogFileDoesNotExist
    @Test
    void exec003() throws Exception {
        Path testFolder = Path.of("src/test/resources/exec003");

        String file = testFolder.resolve("server_003.log").toString();

        LogAnalyzer.main(new String[] { file });

        File summary = new File("src/main/resources/summary.txt");

        assertFalse(summary.exists());

        String consoleOutput = outputStream.toString();

        assertTrue(consoleOutput.contains("Log file not found."));
    }
    
    //should_DisplayErrorReadingFile_IfFileIsLocked
    @Test
    void exec004() throws Exception {
        Path testFolder = Path.of("src/test/resources/exec004");

        Path logFile = testFolder.resolve("server_004.log");

        FileChannel channel = FileChannel.open(logFile, StandardOpenOption.WRITE);

        FileLock lock = channel.lock();

        try {
            LogAnalyzer.main(new String[] { logFile.toString() });

            String consoleOutput = outputStream.toString();

            assertTrue(consoleOutput.contains("Error reading file."));
        } finally {
            lock.release();
            channel.close();
        }
    }
    
    //should_SkipLine_IfMessageIsMissing
    @Test
    void exec005() throws Exception {

        Path testFolder = Path.of("src/test/resources/exec005");

        String expectedFile = Files.readString(testFolder.resolve("summary_005.txt"));

        String file = testFolder.resolve("server_005.log").toString();

        LogAnalyzer.main(new String[] { file });

        String actualFile = Files.readString(Path.of("src/main/resources/summary.txt"));

        assertEquals(expectedFile.replace("\r\n", "\n").trim(), actualFile.replace("\r\n", "\n").trim());

        String consoleOutput = outputStream.toString();

        assertTrue(consoleOutput.contains("Skipping malformed line"));
    }

    //should_DisplayErrorWritingSummaryFile_IfSummaryCannotBeWritten
    @Test
    void exec006() throws Exception {
        Path testFolder = Path.of("src/test/resources/exec006");

        String file = testFolder.resolve("server_006.log").toString();
        
        Files.deleteIfExists(Path.of("src/main/resources/summary.txt"));

        new File("src/main/resources/summary.txt").mkdir();

        try {
            LogAnalyzer.main(new String[] { file });

            String consoleOutput = outputStream.toString();

            assertTrue(consoleOutput.contains("Error writing summary file."));
        } finally {
        	new File("src/main/resources/summary.txt").delete();
        }
    }
}