package IBM.Training.com.Day5;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class LogFileAnalyzer {
	static class MalformedLogEntryException extends Exception {
		public MalformedLogEntryException(String msg) {
			super(msg);
		}
	}
	
	public static void main(String[] args) {
		Map<String, Integer> countLog = new HashMap<>();
		countLog.put("INFO", 0);
		countLog.put("WARN", 0);
		countLog.put("ERROR", 0);
		
		List<String> errMsg = new ArrayList<>();
		
		int totalEntry = 0;
		
		LocalDateTime earliest = null;
		LocalDateTime latest = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try (
			BufferedReader br = new BufferedReader(new FileReader("server.log"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("summary.txt"))
		) {
			String line;
			
			while ((line = br.readLine()) != null) {
				if ((!line.startsWith("[") || !line.contains("]")) || (!(line.contains("INFO:") || line.contains("WARN:") || line.contains("ERROR:")))) {
					throw new MalformedLogEntryException("Invalid log format");
				}
				
				totalEntry++;
				
				String timeTxt = line.substring(1, line.indexOf("]"));
				LocalDateTime time = LocalDateTime.parse(timeTxt, formatter);
				
				if (earliest == null || time.isBefore(earliest)) {
					earliest = time;
				}
				
				if (latest == null || time.isAfter(latest)) {
					latest = time;
				}
				
				String[] logPart = line.substring(line.indexOf("]") + 2).split(": ", 2);
				
				if (logPart.length < 2) {
				    throw new MalformedLogEntryException("Invalid message format");
				}
				
				String level = logPart[0];
				String message = logPart[1];
				
				countLog.put(level, countLog.get(level) + 1);
				
				if (level.equals("ERROR")) {
					errMsg.add(message);
				}
			}
			
			bw.write("Log Summary Report");
			bw.newLine();
			bw.write("------------------");
			bw.newLine();
			bw.write("Total Entries: " + totalEntry);
			bw.newLine();
			bw.write("INFO: " + countLog.get("INFO"));
			bw.newLine();
			bw.write("WARN: " + countLog.get("WARN"));
			bw.newLine();
			bw.write("ERROR: " + countLog.get("ERROR"));
			bw.newLine();
			bw.newLine();
			bw.write("Error Messages:");
			bw.newLine();
			
			for (String msg : errMsg) {
				bw.write("- " + msg);
				bw.newLine();
			}
			
			bw.newLine();
			bw.write("Earliest Timestamp: " + earliest.format(formatter));
			bw.newLine();
			bw.write("Latest Timestamp: " + latest.format(formatter));
			bw.newLine();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("File Error: " + e.getMessage());
		} catch (MalformedLogEntryException e) {
			System.out.println("Malformed log entry: " + e.getMessage());
		}
	}
}
