import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class GrepMainTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }
    @Test
    public void testCheckReg() {
        assertTrue(new GrepMain().checkReg("abc.*","abcdefg"));
        assertFalse(new  GrepMain().checkReg("ab.*","aBcd"));
        assertFalse(new GrepMain().checkReg("abc.*","a b c d e f g"));
    }
    @Test
    public void testCheckWord() {
        assertTrue(new GrepMain().checkWord("abc","abcdefg"));
        assertTrue(new GrepMain().checkWord("a b c","a b c d e f g"));
        assertFalse(new GrepMain().checkWord("abc","acbsad"));
    }
    @Test
    public void testGrep() {
        final PrintStream oldOut = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GrepMain test1 = new GrepMain();
        List<String> testList = List.of("aB","files/text1");
        test1.Grep(testList);
        assertEquals("aBcd",outContent.toString().trim());
        outContent.reset();
        testList = List.of("[-r]","cd.*","files/text1");
        test1.Grep(testList);
        assertEquals("cdAb",outContent.toString().trim());
        outContent.reset();
        testList = List.of("[-r][-v][-i]","ab.*","files/text1");
        test1.Grep(testList);
        assertEquals("cdAb", outContent.toString().trim());
        outContent.reset();
        testList = List.of("[-v],[-i]","cd","files/text1");
        test1.Grep(testList);
        assertEquals("",outContent.toString().trim());
        outContent.reset();
        testList = List.of("[-i]","da","files/text1");
        test1.Grep(testList);
        assertEquals("cdAb",outContent.toString().trim());
        outContent.reset();
        testList = List.of("Ab","files/text1");
        test1.Grep(testList);
        assertEquals("cdAb",outContent.toString().trim());
        System.setOut(oldOut);
    }
}