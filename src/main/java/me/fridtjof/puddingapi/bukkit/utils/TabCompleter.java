package me.fridtjof.puddingapi.bukkit.utils;

import me.fridtjof.puddingapi.bukkit.PuddingAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    List<String> arguments1 = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();
    List<String> arguments3 = new ArrayList<String>();

    List<String> result = new ArrayList<>();

    public TabCompleter() {

    }

    public void addArgument(String argument, int argumentNumber) {
        if (argumentNumber == 1) {
            System.out.println("1");
            this.arguments1.add(argument);
        } else if (argumentNumber == 2) {
            System.out.println("2");
            this.arguments2.add(argument);
        } else if (argumentNumber == 3) {
            System.out.println("3");
            this.arguments3.add(argument);
        } else {
            Logger logger = new Logger(PuddingAPI.getInstance());
            logger.warn("Wrong argument number!");
        }
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1 != null) {
            System.out.println("1");
            loop(args, 1, arguments1);
        }
        /*if (arguments2 != null) {
            System.out.println("2");
            loop(args, 1, arguments2);
        }
        if (arguments3 != null) {
            System.out.println("3");
            loop(args, 1, arguments3);
        }*/

        return result;
    }

    void loop(String[] args, int argumentNumber, List<String> arguments) {
        List<String> result = new ArrayList<>();

        if (args.length == argumentNumber) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
        }
    }
}