import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class grepLauncher {

    @Option(name = "-c", metaVar = "commands", usage = "input commands")
    private String commands;

    @Option(name = "-w", metaVar = "words", required = true, usage = "input word for search")
    private String word;

    @Option(name = "-f", metaVar = "inPutFile", required = true, usage = "name of inputfile")
    private String file;

    public static void main(String[] args) {
        new grepLauncher().doMain(args);
    }
    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar GrepMain.jar -c command -w words -f inPutFile\n" +
                    " like: [-v][-i] word file/text1");
            parser.printUsage(System.err);
            return;
        }
        GrepMain grep = new GrepMain().Grep(commands,word,file);
    }
}
