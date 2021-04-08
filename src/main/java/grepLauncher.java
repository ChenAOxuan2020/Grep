import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;
import org.kohsuke.args4j.Option;
import java.util.List;

public class grepLauncher {
    //get [-v],[-r],[-i]
    @Option(name = "-c", metaVar = "commandline",handler = StringArrayOptionHandler.class, required = true)
    private List<String> commandLine ;

    public static void main(String[] args) {
        new grepLauncher().doMain(args);
    }
    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("formart like [-v][-r][-i] word filePath(use speace to divide command word and path)");
            parser.printUsage(System.err);
            return;
        }
        GrepMain grep = new GrepMain().Grep(commandLine);
    }
}
