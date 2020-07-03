package tk.fridtjof.puddingapi.general.utils;

import java.io.IOException;
import java.net.URL;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MojangAPI {

    public static String getUuidFromUsername(String username) {
        String urlString = "https://api.mojang.com/users/profiles/minecraft/" + username;

        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(urlString));
            if(UUIDJson.isEmpty()) {
                return null;
            }
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException e) {
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}