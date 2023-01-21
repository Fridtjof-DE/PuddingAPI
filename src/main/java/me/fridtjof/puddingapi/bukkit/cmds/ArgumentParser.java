package me.fridtjof.puddingapi.bukkit.cmds;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

    public static List<String> parseArgs(String[] args, List<String> argList1) {
        return parseArgs(args, argList1, null);
    }

    public static List<String> parseArgs(String[] args, List<String> argList1, List<String> argList2) {
        return parseArgs(args, argList1, argList2, null);
    }

    public static List<String> parseArgs(String[] args, List<String> argList1, List<String> argList2, List<String> argList3) {
        return parseArgs(args, argList1, argList2, argList3, null);
    }

    public static List<String> parseArgs(String[] args, List<String> argList1, List<String> argList2, List<String> argList3, List<String> argList4) {
        return parseArgs(args, argList1, argList2, argList3, argList4, null);
    }

    public static List<String> parseArgs(String[] args, List<String> argList1, List<String> argList2, List<String> argList3, List<String> argList4,  List<String> argList5) {

        List<String> result = new ArrayList<>();

        switch(args.length) {
            case 1 -> {
                if(argList1 != null) {
                    for (String a : argList1) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
            }
            case 2 -> {
                if(argList2 != null) {
                    for (String a : argList2) {
                        if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
            }
            case 3 -> {
                if(argList3 != null) {
                    for (String a : argList3) {
                        if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
            }
            case 4 -> {
                if(argList4 != null) {
                    for (String a : argList4) {
                        if (a.toLowerCase().startsWith(args[3].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
            }
            case 5 -> {
                if(argList5 != null) {
                    for (String a : argList5) {
                        if (a.toLowerCase().startsWith(args[4].toLowerCase())) {
                            result.add(a);
                        }
                    }
                }
            }
        }
        return result;
    }
}
