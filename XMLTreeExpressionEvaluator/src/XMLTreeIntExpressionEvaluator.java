import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Ivan Martinez-Kay
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int result = 0;

        //makes sure that the xml tree has children
        if (exp.numberOfChildren() > 0) {

            //will add children attribute value of tag with label plus
            if (exp.label().equals("plus")) {

                //recursive call will find children of children and
                //operate based on values generated.
                result = evaluate(exp.child(0)) + evaluate(exp.child(1));

                //will subtract children attribute value of tag with label minus
            } else if (exp.label().equals("minus")) {

                result = evaluate(exp.child(0)) - evaluate(exp.child(1));

                //will multiply children attribute value of tag with label times
            } else if (exp.label().equals("times")) {

                result = evaluate(exp.child(0)) * evaluate(exp.child(1));

                //will divide children attribute value of tag with label divide
            } else if (exp.label().equals("divide")) {

                result = evaluate(exp.child(0)) / evaluate(exp.child(1));
            }

        } else {

            //return first attribute value if there are no children.
            result = Integer.parseInt(exp.attributeValue("value"));
        }

        //return mathematical operation result
        return result;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
