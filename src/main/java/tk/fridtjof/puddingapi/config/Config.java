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
	
	private static String prefix = "[CONFIG]: ";
	private static String str_ind = "\"";
	private static String char_ind = "'";
	
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
				
			    //System.out.println(prefix + "File is created!");
			} else {
			    //System.out.println(prefix + "File already exists.");
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
	    catch(FileNotFoundException e) {
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
	
	public void valueSetter(String index, String value, String indicator) {
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
	        		if(line1.matches("^>-" + index + "=.*;$")) {
	        			line1 = line1.replaceAll("^>-" + index + "=.*;$", ">-" +  index + "=" + indicator + value + indicator + ";");
	        		} else {
	        		}
	        	writer.write(line1 + "\n");
				}
	        	
	        } else {        	
				for (String line1 : lines) {
					writer.write(line1 + "\n");
				}
				writer.write(">-" + index + "=" + indicator + value + indicator + ";");
				}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	//String ----------------------------------
	
	public String getString(String index) {
		return getValue(index, str_ind);
	}
	
	public void setValue(String index, String value) {
		valueSetter(index, value, str_ind);
	}
	
	public void setDefault(String index, String value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
	
	//Char ----------------------------------
	
	public char getChar(String index) {
		char out = 0;
		try {
			out = getValue(index, char_ind).charAt(0);
		} catch(NullPointerException e) {
			System.out.println(prefix + index + " is not a char!");
		}
		return out;
	}
	
	public void setValue(String index, char value) {
		valueSetter(index, Character.toString(value), char_ind);
	}
	
	public void setDefault(String index, char value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
	
	//Int ----------------------------------
	
	public int getInt(String index) {
		int out = 0;
		try {
			out = Integer.parseInt(getValue(index, ""));
		} catch(NumberFormatException e) {
			System.out.println(prefix + index + " is not an integner!");
		}
		return out;
	}
	
	//Long ----------------------------------
	
	public long getLong(String index) {
		long out = 0;
		String in = getValue(index, "");
		if(in != null) {
			try {
				out = Long.parseLong(in);
			} catch(NumberFormatException e) {
				System.out.println(prefix + index + " is not a long!");
			}
		}
		return out;
	}
	
	public void setValue(String index, long value) {
		valueSetter(index, "" + value, "");
	}
	
	public void setDefault(String index, int value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
	
	//Double ----------------------------------
	
	public double getDouble(String index) {
		double out = 0;
		String in = getValue(index, "");
		if(in != null) {
			try {
				out = Double.parseDouble(in);
			} catch(NumberFormatException e) {
				System.out.println(prefix + index + " is not a double!");
			}
		}
		return out;
	}
	
	public void setValue(String index, double value) {
		valueSetter(index, "" + value, "");
	}
	
	public void setDefault(String index, double value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
	
	//Float ----------------------------------
	
	public float getFloat(String index) {
		float out = 0;
		String in = getValue(index, "");
		if(in != null) {
			try {
				out = Float.parseFloat(getValue(index, ""));
			} catch(NumberFormatException e) {
				System.out.println(prefix + index + " is not a float!");
			}
		}
		return out;
	}
	
	//Boolean ----------------------------------
	
	public boolean getBoolean(String index) {
		boolean bool = false;
		String in = getValue(index, "");
		if(in != null) {
			if(in.equalsIgnoreCase("true")) {
				bool = true;
			} else if(in.equalsIgnoreCase("false")) {
				bool = false;
			} else {
				System.out.println(prefix + index + " is not a boolean!");
			}
		}
		return bool;
	}
	
	public void setValue(String index, boolean value) {
		valueSetter(index, value + "", "");
	}
	
	public void setDefault(String index, boolean value) {
		if(!checkIndex(index)) {
			setValue(index, value);
		}
	}
}
