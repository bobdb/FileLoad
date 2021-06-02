import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLoad {

    private enum FileType {TXT, CSV, JSON, INLINE}

    public static void main(String[] args) {

        Options options = new Options();

        // name option
        Option file = Option.builder("file")
                .argName( "filename" )
                .hasArg()
                .desc(  "file to read" )
                .build();
        options.addOption(file);

        // inline option
        Option inline = Option.builder("inline")
                .argName( "string" )
                .hasArg()
                .desc(  "read inline string" )
                .build();
        options.addOption(inline);

        // csv flag
        Option csv = Option.builder("csv")
                .desc(  "csv format" )
                .build();
        options.addOption(csv);

        // json flag
        Option json = Option.builder("json")
                .desc(  "json format" )
                .build();
        options.addOption(json);

        // help option
        Option help = Option.builder("help")
                .desc(  "show this message" )
                .build();
        options.addOption(help);

        // parse and generate CommandLine object
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse( options, args);
            HelpFormatter formatter = new HelpFormatter();
            if (cmd.hasOption("help")) {
                formatter.printHelp( "FileLoad", options );
            } else if (cmd.hasOption("inline")) {
                String string = cmd.getOptionValue("inline");
                if (cmd.hasOption("csv"))
                    processString(string,FileType.CSV);
                else if (cmd.hasOption("json"))
                    processString(string,FileType.JSON);
                else {
                    string = string.substring(1,string.length()-1);
                    processString(string, FileType.TXT);
                }
            } else if (cmd.hasOption("file")) {
                String filename = cmd.getOptionValue("file");
                if (cmd.hasOption("csv"))
                    process(filename,FileType.CSV);
                else if (cmd.hasOption("json"))
                    process(filename,FileType.JSON);
                else
                    process(filename,FileType.TXT);
            } else {
                formatter.printHelp( "FileLoad", options );
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void processString(String string, FileType type) {
        List<List<String>> list = new ArrayList<>();
        String[] lines;
        switch (type) {
            case CSV:
                lines = string.split("\\n");
                for (String line : lines) {
                    String[] words = line.split(",");
                    List<String> tmp = new ArrayList<>(Arrays.asList(words));
                    list.add(tmp);
                }
                break;
            case JSON:
                List<List<String>> list1 = new ArrayList<>();
                List<List<String>> list2 = new ArrayList<>();
                lines = string.split("\\n");
                Pattern pattern = Pattern.compile("'([^:,]+)'\\s*:\\s*'([^:,]+)'");
                for (String line : lines) {
                    List<String> keys = new ArrayList<>();
                    List<String> values = new ArrayList<>();
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        keys.add(matcher.group(1));
                        values.add(matcher.group(2));
                    }
                    list1.add(keys);
                    list2.add(values);

                }
                System.out.println(list1);
                System.out.println(list2);
                break;
            case INLINE:
                break;
            case TXT:
            default:
                lines = string.split("\\n");
                for (String line : lines) {
                    String[] words = line.split(" ");
                    List<String> tmp = new ArrayList<>(Arrays.asList(words));
                    list.add(tmp);
                }
                break;
        }
        System.out.println(list);

    }

    private static void process(String s, FileType type) {
        List<List<String>> list = new ArrayList<>();
        switch (type) {
            case INLINE:
                break;
            case CSV:
                list = loadFromCsv(s);
                break;
            case TXT:
                list = loadFromTxt(s);
                break;
            case JSON:
                list = loadFromJson(s);
                break;
            default:
                break;
        }
        System.out.println(list);
        System.out.println("done");

    }

    private static List<List<String>> loadFromJson(String s) {
        return null;
    }

    private static List<List<String>> loadFromTxt(String s) {
        List<List<String>> result = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(s));
            for (String line : allLines) {
                if (!line.isEmpty()) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(line);
                    result.add(tmp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static List<List<String>> loadFromCsv(String s) {
        return null;
    }


}
