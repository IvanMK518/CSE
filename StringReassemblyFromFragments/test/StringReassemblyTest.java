import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    //this was hard to test considering it needs an output stream
    @Test
    public void testPrintWithLineSeparators() {
        SimpleWriter out = new SimpleWriter1L("data/lineSeparators.txt");
        SimpleReader in = new SimpleReader1L("data/lineSeparators.txt");
        String text = "Hello~There~It is~Me~Ivan";
        String expect = "Hello\n" + "There\n" + "It is\n" + "Me\n" + "Ivan";
        StringReassembly.printWithLineSeparators(text, out);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        String line4 = in.nextLine();
        String line5 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, line1 + "\n" + line2 + "\n" + line3 + "\n" + line4
                + "\n" + line5);
    }

    //this was hard to test considering it needs an output stream
    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("data/lineSeparators2.txt");
        SimpleReader in = new SimpleReader1L("data/lineSeparators2.txt");
        String text = "Hi there~why are you here?~Silly goose!~this will replace each~with a line break";
        String expect = "Hi there\n" + "why are you here?\n" + "Silly goose!\n"
                + "this will replace each\n" + "with a line break";
        StringReassembly.printWithLineSeparators(text, out);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        String line4 = in.nextLine();
        String line5 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, line1 + "\n" + line2 + "\n" + line3 + "\n" + line4
                + "\n" + line5);
    }

    @Test
    public void testaddToSetAvoidingSubstringsIsSubstring() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExp = strSet.newInstance();
        String str = "Hel";
        strSet.add("Hello");
        strSetExp.add("Hello");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExp);
    }

    @Test
    public void testaddToSetAvoidingSubstringsIsSubstringgoodbye() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExp = strSet.newInstance();
        String str = "Hel";
        strSet.add("Today");
        strSet.add("lGoodbye");
        strSet.add("Hello");
        strSetExp.add("Hello");
        strSetExp.add("Today");
        strSetExp.add("lGoodbye");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExp);
    }

    @Test
    public void testaddToSetAvoidingSubstringsIsNotSubstring() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExp = strSet.newInstance();
        String str = "Goodbye";
        strSet.add("Hello");
        strSet.add("Today");
        strSetExp.add("Hello");
        strSetExp.add("Today");
        strSetExp.add("Goodbye");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExp);
    }

    @Test
    public void testAddToSetAvoidingSubstringshamburger() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExp = new Set1L<>();
        String str = "hotdog";
        strSet.add("hot");
        strSet.add("hamburger");
        strSetExp.add("hotdog");
        strSetExp.add("hamburger");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExp, strSet);
    }

    @Test
    public void testaddToSetAvoidingSubstringsIsSuperstring() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExp = strSet.newInstance();
        String str = "HelloDoug";
        strSet.add("Hello");
        strSetExp.add("HelloDoug");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExp);
    }

    @Test
    public void testCombinationIvan() {
        String str1 = "Iva";
        String str2 = "an";
        int overlap = 1;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Ivan", combine);
    }

    @Test
    public void testCombinationCumulonimbus() {
        String str1 = "Cumulonim";
        String str2 = "nimbus";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Cumulonimbus", combine);
    }

    /*
     * Tests for lines from input
     */
    @Test
    public void testLinesFromInput() {
        String fileName = "data/cheer.txt";
        SimpleReader fileRead = new SimpleReader1L(fileName);
        Set<String> setExp = new Set1L<>();
        setExp.add("Bucks -- Beat");
        setExp.add("Go Bucks");
        setExp.add("Beat Mich");
        setExp.add("Michigan~");
        setExp.add("o Bucks -- B");
        Set<String> setActual = StringReassembly.linesFromInput(fileRead);
        assertEquals(setActual, setExp);
    }

    @Test
    public void testLinesFromInput_All_Not_SS() {
        String fileName = "data/HelloIvan.txt";
        SimpleReader fileRead = new SimpleReader1L(fileName);
        Set<String> setExp = new Set1L<>();
        setExp.add("Hello");
        setExp.add("There");
        setExp.add("It is");
        setExp.add("Me");
        setExp.add("Ivan");
        Set<String> setActual = StringReassembly.linesFromInput(fileRead);
        assertEquals(setActual, setExp);
    }

}
