import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    //these were the only testable methods I could implement in jUnit
    //other than this, I created some testable text files for further proof
    //of functionality.
    //It took a long time to come up with these tests, because I didn't
    //realize that I needed to essentially run snippets of each method instead
    //of the entire one.

    @Test
    public void termsGeneratedMoreGlossary() {
        SimpleReader file = new SimpleReader1L("data/test2.txt");
        Sequence<String> terms = new Sequence1L<>();

        while (!file.atEOS()) {
            String line = file.nextLine();
            if (!line.contains(" ")) {
                terms.add(terms.length(), line);
            }
            //I used this so that the definitions are not read in
            //as separate empty terms
            String definition = Glossary.definitions(file);

        }

        Glossary.sortIndex(terms);

        Sequence<String> sortedTerms = new Sequence1L<>();
        sortedTerms.add(0, "adjective");
        sortedTerms.add(1, "antonym");
        sortedTerms.add(2, "book");
        sortedTerms.add(3, "definition");
        sortedTerms.add(4, "glossary");
        sortedTerms.add(5, "language");
        sortedTerms.add(6, "meaning");
        sortedTerms.add(7, "noun");
        sortedTerms.add(8, "synonym");
        sortedTerms.add(9, "term");
        sortedTerms.add(10, "word");

        assertEquals(sortedTerms, terms);

        file.close();
    }

    @Test
    public void termsGeneratedIvan() {
        SimpleReader file = new SimpleReader1L("data/test3.txt");
        Sequence<String> terms = new Sequence1L<>();

        while (!file.atEOS()) {
            String line = file.nextLine();
            if (!line.contains(" ")) {
                terms.add(terms.length(), line);
            }
            //I used this so that the definitions are not read in
            //as separate empty terms
            String definition = Glossary.definitions(file);

        }

        Glossary.sortIndex(terms);

        Sequence<String> sortedTerms = new Sequence1L<>();

        sortedTerms.add(0, "Alejandro");
        sortedTerms.add(1, "Architecture");
        sortedTerms.add(2, "CSE");
        sortedTerms.add(3, "Elma");
        sortedTerms.add(4, "Ivan");
        sortedTerms.add(5, "Kay");
        sortedTerms.add(6, "Martinez");
        sortedTerms.add(7, "Martinez-Kay");
        sortedTerms.add(8, "name");

        assertEquals(sortedTerms, terms);

        file.close();
    }

    @Test
    public void definitionsSpaceGenerated() {
        SimpleReader file = new SimpleReader1L("data/test3.txt");
        Sequence<String> terms = new Sequence1L<>();
        Sequence<String> definitions = new Sequence1L<>();

        while (!file.atEOS()) {
            String line = file.nextLine();
            if (!line.contains(" ")) {
                //kept this in to prevent terms from being added to
                //definition sequence
                terms.add(terms.length(), line);
            }

            String definition = Glossary.definitions(file);
            definitions.add(definitions.length(), definition);

        }

        Sequence<String> defTest = new Sequence1L<>();

        defTest.add(0, "A given alias to a thing or person");
        defTest.add(1, "My name");
        defTest.add(2, "My father's last name");
        defTest.add(3, "My mother's last name");
        defTest.add(4, "My last name");
        defTest.add(5, "My mother's first name");
        defTest.add(6, "My father's first name");
        defTest.add(7, "My current major");
        defTest.add(8, "My other prospective double major");

        assertEquals(defTest, definitions);

        file.close();
    }

}
