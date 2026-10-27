package me.fridtjof.puddingapi.bukkit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorTranslationUtils {

    char colorChar = '&';
    String hexPatternStart = "&'";
    String hexPatternEnd = "";

    public ColorTranslationUtils() {

    }

    public ColorTranslationUtils(char colorChar) {
        this.colorChar = colorChar;
    }

    public ColorTranslationUtils(char colorChar, String hexPatternStart, String hexPatternEnd) {
        this.colorChar = colorChar;
        this.hexPatternStart = hexPatternStart;
        this.hexPatternEnd = hexPatternEnd;
    }


    public String translateHexCodes(String message) {
        if (message == null) return "";

        String regex = Pattern.quote(hexPatternStart) + "([A-Fa-f0-9]{6})" + Pattern.quote(hexPatternEnd);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            StringBuilder replacement = new StringBuilder("§x");
            for (char c : hex.toCharArray()) {
                replacement.append('§').append(c);
            }
            matcher.appendReplacement(buffer, replacement.toString());
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    public String translateLegacyColorCodes(String message) {
        if (message == null) {
            return "";
        }

        String regex = Pattern.quote(String.valueOf(colorChar)) + "([0-9a-fA-F])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, "§" + matcher.group(1));
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    public String translateLegacyFormattingCodes(String message) {
        if (message == null) {
            return "";
        }

        String regex = Pattern.quote(String.valueOf(colorChar)) + "([kKlLmMnOoRr])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, "§" + matcher.group(1));
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    public boolean isMessageEmptyOrWhitespaceOnly(String message) {
        if (message == null) return true;

        String cleaned = message.replaceAll("&#[A-Fa-f0-9]{6}", "");
        cleaned = cleaned.replaceAll("[&§][0-9a-fk-orA-FK-OR]", "");
        cleaned = cleaned.replace(" ", "").trim();

        return cleaned.isEmpty();
    }
}
