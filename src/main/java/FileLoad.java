import org.apache.commons.cli.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileLoad {

    private enum FileType {TXT, CSV, JSON, INLINE};

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
                    List<String> tmp = new ArrayList<>();
                    for(String w : words)
                        tmp.add(w);
                    list.add(tmp);
                }
                break;
            case JSON:
                lines = string.split("\\n");
                for (String line : lines) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(line);
                    list.add(tmp);
                }
                break;
            case TXT:
            default:

                lines = string.split("\\n");
                for (String line : lines) {
                    String[] words = line.split(" ");
                    List<String> tmp = new ArrayList<>();
                    for(String w : words)
                        tmp.add(w);
                    list.add(tmp);
                }
                break;
        }
        System.out.println(list);
    }

    private static void process(String s, FileType type) {
        List<List<String>> list = null;
        switch (type) {
            case INLINE:
                list = null;
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
                list = null;
                break;
        }
        System.out.println("done.");

    }

    private static List<List<String>> loadFromJson(String s) {
        return null;
    }

    private static List<List<String>> loadFromTxt(String s) {
        return null;
    }

    private static List<List<String>> loadFromCsv(String s) {
        return null;
    }


}
