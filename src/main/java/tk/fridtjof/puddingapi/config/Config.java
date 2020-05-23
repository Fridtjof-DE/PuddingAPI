package tk.fridtjof.puddingapi.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config {
	
	private static String file_path = new String();
	private static String file_name = new String();
	private static String str_ind = "\"";
	
	public Config(String path, String name) {
		file_path = path;
		file_name = name;
	}
	
	private static File createConfig() {
		String full_path = file_path + file_name + ".pddg";	
		File config_file = new File(full_path);
		try {
			if (config_file.createNewFile()) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				
				FileWriter writer = new FileWriter(createConfig());
				writer.write("Date of creation: " + dtf.format(now));
				writer.close();
				
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config_file;
	}
	
	private boolean checkIndex(String index) {
		String line = null;
		boolean exist = false;
		try {
	        Scanner scanner = new Scanner(createConfig());
	        while (scanner.hasNextLine()) {
	        	line = scanner.nextLine();
	        	
	        	if(line.matches("^>-" + index + "=.*;$")) {
	        		
	        		exist = true;
	        		break;
	        	} else {
	        		exist = false;
	        	}
	        }
	    scanner.close();  
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		return exist;
	}
	
	
	
	//String-----------------------------------------------------------
	
	public void setDefault(String index, String value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
	
	private String getValue(String index, String indicator) {
		String line = null;
		boolean exists = false;
		try {
	        Scanner scanner = new Scanner(createConfig());
	        while (scanner.hasNextLine()) {
	        	line = scanner.nextLine();
	        
	        	if(line.matches("^>-" + index + "=" + indicator + ".*" + indicator + ";$")) {
	        		exists = true;
	        		break;
	        	} else {
	        		exists = false;
	        	}
	        }
	    scanner.close();  
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		if(exists) {
			line = line.replaceAll("^>-.*=" + indicator, "");
			line = line.replaceAll(indicator + ";$", "");
			return line;
		} else {
			return null;
		}
	}
	
	public void setValue(String index, String value) {
		String line = null;
		
		List<String> lines = new ArrayList<String>();
		
		boolean check = !checkIndex(index);
		
		try {
			Scanner scanner = new Scanner(createConfig());
			while (scanner.hasNextLine()) {
	        	line = scanner.nextLine();
	        	lines.add(line);
	        }
		scanner.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
		try {
			FileWriter writer = new FileWriter(createConfig());
			
			if(!check) {
	        	for (String line1 : lines) {
	        		if(line1.matches("^>-" + index + "=" + str_ind + ".*" + str_ind + ";$")) {
	        			line1 = line1.replaceAll("^>-" + index + "=" + str_ind + ".*" + str_ind + ";$", ">-" +  index + "=" + str_ind + value + str_ind + ";");
	        		} else {
	        		}
	        	writer.write(line1 + "\n");
				}
	        	
	        } else {        	
				for (String line1 : lines) {
					writer.write(line1 + "\n");
				}
				writer.write(">-" + index + "=\"" + value + "\";");
				}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	
	public String getString(String index) {
		return getValue(index, str_ind);	
	}
}
