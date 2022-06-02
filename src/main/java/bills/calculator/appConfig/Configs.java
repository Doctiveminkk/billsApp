package bills.calculator.appConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Read/Write/Create configuration file that defines divisor, ignored character and directory
public class Configs {

	private static Configs configs;
	private final String FILENAME = ".billsConfig";
	private int divisor;
	private String ignoreChar;
	private String directory;

	// Constructor
	private Configs() {
		readFile();
		System.out.println(toString());
	}

	public static Configs getInstance() {
		if (configs == null) {
			configs = new Configs();
		}
		return configs;
	}

	// Create and write information into the file
	public void writeFile() {
		String fileContent = divisor + "\n" + ignoreChar + "\n" + directory;
		try {
			FileWriter fileWriter = new FileWriter(FILENAME);
			fileWriter.write(fileContent);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read information from the file in case it already exists
	private void readFile() {
		try {
			BufferedReader input = new BufferedReader(new FileReader(FILENAME));
			try {
				divisor = Integer.parseInt(input.readLine());
				ignoreChar = input.readLine();
				directory = input.readLine();
				input.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			setDefault();
			writeFile();
		}
	}

	// Set default values
	private void setDefault() {
		divisor = 3;
		ignoreChar = ",";
		directory = System.getProperty("user.dir");
	}

	// Getters and Setters
	public int getDivisor() {
		return divisor;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public String getIgnoreChar() {
		return ignoreChar;
	}

	public void setIgnoreChar(String ignoreChar) {
		this.ignoreChar = ignoreChar;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String toString() {
		return "**Divisor -> " + divisor + "\n**Ignored Character -> \'" + ignoreChar + "\'\n**Directory -> " + directory
				+ "\n[type 0 to access configurations]\n";
	}
}
