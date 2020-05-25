package util;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;

public class Parser {
    @Option(name = "-d", usage = "Directory search")
    private String path = null;

    @Option(name = "-r", usage = "Need search child")
    private boolean needChild = false;

    @Argument
    private ArrayList<String> arguments = new ArrayList<>();
    private String fileName;

    public void parse(String[] args) {
        CmdLineParser lineParser = new CmdLineParser(this);
        try {
            lineParser.parseArgument(args);
            if (arguments.isEmpty()) {
                System.out.println("[-r] [-d directory] filename.txt");
                lineParser.printUsage(System.out);
                throw new IllegalArgumentException("File name is required!");
            }
        } catch (CmdLineException e) {
            System.out.println("[-r] [-d directory] filename.txt");
            lineParser.printUsage(System.out);
            throw new IllegalArgumentException("File name is required!");
        }

        fileName = arguments.get(0);
    }

    public String getPath() {
        return path;
    }

    public boolean isNeedChild() {
        return needChild;
    }

    public String getFileName() {
        return fileName;
    }
}
