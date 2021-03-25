import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GrepMainTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testCheckReg() {
        assertTrue(new GrepMain().checkReg("abc.*","abcdefg"));
        assertFalse(new  GrepMain().checkReg("ab.*","aBcd"));
        assertFalse(new GrepMain().checkReg("abc.*","a b c d e f g"));
    }

    public void testCheckWord() {
        assertTrue(new GrepMain().checkWord("abc","abcdefg"));
        assertTrue(new GrepMain().checkWord("a b c","a b c d e f g"));
        assertFalse(new GrepMain().checkWord("abc","acbsad"));
    }

    public void testGrep() {
        final PrintStream oldOut = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GrepMain test1 = new GrepMain();
        test1.Grep("","aB","files/text1");
        assertEquals("aBcd",outContent.toString().trim());
        outContent.reset();
        test1.Grep("[-r]","cd.*","files/text1");
        assertEquals("cdAb",outContent.toString().trim());
        outContent.reset();
        test1.Grep("[-r][-v][-i]","ab.*","files/text1");
        assertEquals("cdAb", outContent.toString().trim());
        outContent.reset();
        test1.Grep("[-v],[-i]","cd","files/text1");
        assertEquals("",outContent.toString().trim());
        outContent.reset();
        test1.Grep("[-i]","da","files/text1");
        assertEquals("cdAb",outContent.toString().trim());
        outContent.reset();
        test1.Grep("","Ab","files/text1");
        assertEquals("cdAb",outContent.toString().trim());
        System.setOut(oldOut);
    }
}