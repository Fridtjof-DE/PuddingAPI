package tk.fridtjof.puddingapi.utils;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MojangAPI {

	public static String getUuidFromUsername(String username) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + username;
        try {
            @SuppressWarnings("deprecation")
			String UUIDJson = IOUtils.toString(new URL(url));           
            if(UUIDJson.isEmpty()) {
            	return null;                       
            }
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException e) {
        	
            return null;
        } catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
       
        return "null";
    }
}
