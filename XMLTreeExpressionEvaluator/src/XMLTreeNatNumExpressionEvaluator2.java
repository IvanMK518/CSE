import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Ivan Martinez-Kay
 *
 */
public final class XMLTreeNatNumExpressionEvaluator2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNatNumExpressionEvaluator2() {
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
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber result = new NaturalNumber2(0);
        //Side Note: I got deadstore checkstyle warning when
        //I initialized to a new constructor
        //so I just created a new instance.
        //stores value of attribute of child(0)
        NaturalNumber child0 = result.newInstance();
        //stores value of attribute of child(1)
        NaturalNumber child1 = result.newInstance();

        //makes sure exp has children
        if (exp.numberOfChildren() > 0) {

            //checks for child with attribute value and assign child0 to that value.
            if (exp.child(0).hasAttribute("value")) {

                child0 = new NaturalNumber2(
                        exp.child(0).attributeValue("value"));

            } else {
                //recursively checks for child
                //with attribute value and assign child0 to that value.
                child0 = evaluate(exp.child(0));

            }

            if (exp.child(1).hasAttribute("value")) {

                child1 = new NaturalNumber2(
                        exp.child(1).attributeValue("value"));

            } else {

                child1 = evaluate(exp.child(1));

            }

            //will add children attribute value of tag with label plus
            if (exp.label().equals("plus")) {

                child0.add(child1);

                //will subtract children attribute value of tag with label minu
            } else if (exp.label().equals("minus")) {

                //read string prompt
                if (child1.compareTo(child0) > 0) {

                    Reporter.fatalErrorToConsole(
                            ".subtract requires this >= n.");
                } else {

                    child0.subtract(child1);

                }

                //will multiply children attribute value of tag with label times
            } else if (exp.label().equals("times")) {

                child0.multiply(child1);

                //will divide children attribute value of tag with label divide
            } else if (exp.label().equals("divide")) {

                //read string prompt
                if (child1.isZero()) {

                    Reporter.fatalErrorToConsole(
                            "You cannot divide by zero. .divide requires n > 0");

                } else {

                    child0.divide(child1);

                }
            }

            //since we are changing the object value of child0 specifically,
            //we should return child0
            result.copyFrom(child0);

        } else {
            //otherwise it will return the attribute value of the root node
            result = new NaturalNumber2(exp.attributeValue("value"));

        }

        //returns result of mathematical operations
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
