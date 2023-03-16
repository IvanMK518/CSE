import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program will take a text file input with valid format. It will then
 * create a multi page glossary.
 *
 * @author Ivan Martinez-Kay
 *
 */
public final class Glossary {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        //This code is recycled from lab, it will look for separators and words
        //on a given line.

        char textChar = text.charAt(position);

        int len = text.length();

        String result = "";
        boolean flag = false;
        int i = position;
        if (!separators.contains(textChar)) {
            while (i < len && !flag) {
                textChar = text.charAt(i);

                if (!separators.contains(textChar)) {

                    String combine = "" + textChar;
                    result = result.concat(combine);
                    i++;
                } else {
                    flag = true;
                }
            }
        } else {
            if (separators.contains(textChar)) {
                while (i < len && !flag) {
                    textChar = text.charAt(i);

                    if (separators.contains(textChar)) {

                        String combine = "" + textChar;
                        result = result.concat(combine);
                        i++;
                    } else {
                        flag = true;
                    }
                }

            }

        }
        return result;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        //also recycled from lab.

        for (int i = 0; i < str.length(); i++) {
            if (!charSet.contains(str.charAt(i))) {
                charSet.add(str.charAt(i));
            }
        }

    }

    /**
     * @param file
     *            reads terms.txt(or whatever file u want to read in) into the
     *            StringBuilder.
     *
     * @return the StringBuilder that compiles the definition
     *
     *         This method should create each individual definition.
     */

    public static String definitions(SimpleReader file) {

        //Dr. Boggus said it is ok to use StringBuilder
        //since Rob said we could use it.
        StringBuilder definition = new StringBuilder(file.nextLine());
        boolean flag = true;

        //will loop through file and look for lines with spaces
        //spaces indicate definitions
        while (flag && !file.atEOS()) {
            String definitionFragment = file.nextLine();
            if (definitionFragment.contains(" ")) {
                //will append an extra space to the stringbuilder
                //when multiline definitions are found
                definition.append(" " + definitionFragment);
            } else {
                //breaks loop
                flag = false;
            }
        }

        return definition.toString();

    }

    /**
     * @param file
     *            parses through each line
     * @param terms
     *            sequence of terms to be generated
     * @param definitions
     *            sequence of definitions to be generated
     * @return created map of terms and definitions.
     *
     *         This method should create a map from the two sequences (not in
     *         alphabetical order).
     */

    public static Map<String, String> createMap(SimpleReader file,
            Sequence<String> terms, Sequence<String> definitions) {

        while (!file.atEOS()) {
            String line = file.nextLine();
            if (!line.contains(" ")) {
                //puts terms (any line without spacing) in sequence
                terms.add(terms.length(), line);
            }
            //calls definition finder in loop to generate all definitions
            String definition = definitions(file);
            //puts definitions found in sequence
            definitions.add(definitions.length(), definition);
        }

        //puts each sequences' elements into the map.
        //terms being the key and definitions being the value of the key.
        Map<String, String> termsAndDef = new Map1L<>();
        for (int i = 0; i < definitions.length(); i++) {
            termsAndDef.add(terms.entry(i), definitions.entry(i));
        }

        return termsAndDef;

    }

    /**
     * @param sortedTerms
     *
     * @updates sortedTerms
     *
     *          This method should sort the term sequence in alphabetical order
     *          so that we can call it when we display the home-page.
     */
    public static void sortIndex(Sequence<String> sortedTerms) {

        //Comparator to sort queue in lexographic order.
        Comparator<String> order = new StringLT();

        //I used a queue because it has sort() function.
        Queue<String> temp = new Queue1L<>();

        //this loop will remove each element of the sequence from back to front
        //then enqueue  each element into the temp queue.
        int i = sortedTerms.length() - 1;
        while (sortedTerms.length() > 0) {
            String term = sortedTerms.remove(i);
            temp.enqueue(term);
            i--;
        }

        //this loop calls the comparator to sort the queue.
        //each element in order will then be removed from the queue back into
        //the sequence, thus giving us alphabetical order.
        int j = 0;
        while (temp.length() > 0) {
            temp.sort(order);
            String remove = temp.dequeue();
            sortedTerms.add(j, remove);
            j++;
        }

    }

    /**
     *
     * @param indexPage
     *
     * @param sortedTerms
     *
     *            This method should create a home-page of terms in the glossary
     *            in alphabetical order.
     */

    private static void indexPage(SimpleWriter indexPage,
            Map<String, String> sortedTerms) {

        Sequence<String> termsCopy = new Sequence1L<>();

        for (Map.Pair<String, String> terms : sortedTerms) {
            termsCopy.add(termsCopy.length(), terms.key());
        }

        //only need to sort the term sequence here because the index page is the
        //only page where the terms need to be in order, therefore
        //I didn't sort the definition sequence
        sortIndex(termsCopy);

        //turns each term into a hyperlink with its own page.
        for (int i = 0; i < sortedTerms.size(); i++) {
            String term = termsCopy.entry(i);
            indexPage.print("<li><a href =\"" + term + ".html\">");
            indexPage.println(term + "</a></li>");
        }

    }

    /**
     *
     * @param folderName
     *
     * @param termsAndDef
     *
     *            This method will create HTML pages for each term and it's
     *            definition. It should create links from each term that appears
     *            in a definition so the user can go straight to another term
     *            page if the want to.
     */

    public static void termHTMLPages(String folderName,
            Map<String, String> termsAndDef) {

        /*
         * Define separator characters for test. Copied code from previous lab
         * to deal with separators.
         */
        final String separatorStr = " \t,;!.: ";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        Sequence<String> termsCopy = new Sequence1L<>();

        for (Map.Pair<String, String> terms : termsAndDef) {
            termsCopy.add(termsCopy.length(), terms.key());
        }

        //generates separate page for each term.
        for (int i = 0; i < termsAndDef.size(); i++) {
            String term = termsCopy.entry(i);
            SimpleWriter termPage = new SimpleWriter1L(
                    folderName + "/" + term + ".html");

            termPage.print("<html>");
            termPage.println("<head>");
            termPage.println("<title>" + term + "</title");
            termPage.println("</head>");
            termPage.println("<body>");
            termPage.println("<h2><b><i><font color=\"red\">" + term
                    + "</font></i></b></h2>");

            termPage.print("<p>");

            //this loop will use nextWordOrSeparator to check every single word
            //in a definition.
            //if a definition contains a term from the index page,
            //it will create a reference link straight
            //to the other terms page.
            String definition = termsAndDef.value(term);
            int pos = 0;
            while (pos < definition.length()) {

                String containsTerm = nextWordOrSeparator(definition, pos,
                        separatorSet);
                if (termsAndDef.hasKey(containsTerm)) {
                    termPage.print("<a href=\"" + containsTerm + ".html\">"
                            + containsTerm + "</a>");
                } else {
                    termPage.print(containsTerm);
                }

                pos += containsTerm.length();
            }

            termPage.print("</p>");

            termPage.print("<hr>");
            termPage.println(
                    //return to home page.
                    "<p>Return to <a href=\"index.html\">index</a>.</p>");
            termPage.print("</hr>");
            termPage.println("</body>");
            termPage.println("</html>");

            termPage.close();
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments. Basic header and footer of
     *            indexPage. Asks user for file input.
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a text file: ");
        String fileName = in.nextLine();

        out.print("Enter a folder in which the text file resides: ");
        String folderName = in.nextLine();

        //reads user input for file location
        SimpleReader index = new SimpleReader1L(
                folderName + "/" + fileName + ".txt");

        //these sequences are passed in as parameters for term and definition generation.
        Sequence<String> terms = new Sequence1L<>();
        Sequence<String> definitions = new Sequence1L<>();

        //writes user file to user input location.
        SimpleWriter indexPage = new SimpleWriter1L(folderName + "/index.html");
        Map<String, String> termsAndDef = createMap(index, terms, definitions);

        //checking if sortIndex sorts the sequence of terms properly.
        //sortIndex(terms);
        //out.print(terms);

        String title = "Glossary";

        indexPage.println("<html>");
        indexPage.println("<head>");
        indexPage.println("<title>" + title + "</title>");
        indexPage.println("</head>");
        indexPage.println("<body>");
        indexPage.println("<h1>" + title + "</h1>");
        indexPage.println("<hr><h2>Index</h2></hr>");
        indexPage.println("<ul>");

        indexPage(indexPage, termsAndDef);
        termHTMLPages(folderName, termsAndDef);

        indexPage.println("</ul>");
        indexPage.println("</body>");
        indexPage.println("</html>");

        in.close();
        out.close();
        indexPage.close();
        index.close();
    }

}
