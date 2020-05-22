package tk.fridtjof.puddingapi.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	
	public static File createConfig(String file_path, String file_name) {
		String full_path = file_name + ".pddg";	
		File config_file = new File(full_path);
		try {
			if (config_file.createNewFile()) {
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config_file;
	}

	public static void setDefault(File file, String value_path, String value) {
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(value_path + "=\"" + value + "\"");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDefault(File file, String value_path, int value) {
		
	}
	
	public static String getString() {
		return null;
		
	}
	
	public static int getInt() {
		return 0;
	}
}
