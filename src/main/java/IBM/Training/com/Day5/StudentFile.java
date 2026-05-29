package IBM.Training.com.Day5;
import java.io.*;

public class StudentFile {
	public static void main(String[] args) {
		try (
			BufferedReader br = new BufferedReader(new FileReader("student.csv"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("student.json"))
		) {
			String line;
			br.readLine();
			bw.write("[");
			bw.newLine();
			boolean first = true;
			
			while ((line = br.readLine()) != null) {
				String[] row = line.replace("\"","").split(",");
				
				String id = row[0];
				String name = row[1];
				String course = row[2];
				 
				if (!first) {
					bw.write(",");
					bw.newLine();
				}
				 
				bw.write("  {");
				bw.newLine();
				bw.write("    \"id\": \"" + id + "\",");
				bw.newLine();
				bw.write("    \"name\": \"" + name + "\",");
				bw.newLine();
				bw.write("    \"course\": \"" + course + "\"");
				bw.newLine();
				bw.write("  }");
				 
				first = false;
			}
			
			bw.newLine();
			bw.write("]");
			
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}