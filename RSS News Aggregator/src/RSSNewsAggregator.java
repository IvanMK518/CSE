import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * This program was modified to take multiple feeds.
 *
 * @author Ivan Martinez-Kay
 * @date October 7th, 2022
 *
 */
public final class RSSNewsAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSNewsAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("<head>");

        /**
         * This part of the code checks for a child of the <channel> that is
         * <title>.
         */

        String title = "";

        //added behavior that checks if title has child
        if ((getChildElement(channel, "title") <= -1)
                && (channel.child(getChildElement(channel, "title"))
                        .numberOfChildren() == 0)) {
            title = ("empty title");
            out.print("<title>");
            out.print(title);
            out.println("</title>");

        } else {
            title = channel.child(getChildElement(channel, "title")).child(0)
                    .label();
            out.print("<title>");
            out.print(title);
            out.println("</title>");
        }

        /**
         * This part of the code checks for a child of the <channel> that is
         * <link>. It will then output a usable link that can be accessed by
         * clicking the title.
         */

        String link = channel.child(getChildElement(channel, "link")).child(0)
                .label();

        out.println("<body>");
        out.print("<h1>");
        out.print("<a href=\"");
        out.print(link);
        out.print("\">");
        out.print(title);
        out.print("</a>");
        out.print("</h1>");

        /**
         * This part of the code checks for a child of the <channel> that is
         * <description>.
         */

        //added behavior that checks if description has child
        if ((getChildElement(channel, "description") <= -1)
                && (channel.child(getChildElement(channel, "description"))
                        .numberOfChildren() == 0)) {
            out.print("<p>");
            out.print("empty description");
            out.println("</p>");
        } else {
            String description = channel
                    .child(getChildElement(channel, "description")).child(0)
                    .label();
            out.print("<p>");
            out.print(description);
            out.println("</p>");
        }

        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>News</th>");
        out.println("<th>Source</th>");
        out.println("<th>Date</th>");
        out.println("</tr>");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        //this method doesn't do anything special :(

        out.println("</table>");
        out.println("</body>");
        out.print("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        /**
         * This method uses a for loop to check which index of the child relates
         * to the child's tag called in other methods.
         */

        int index = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {

            String user = xml.child(i).label();

            //makes sure that the child is a tag and is
            //equal to the parameters passed into the method,
            //otherwise the index returned will be -1

            if (xml.child(i).isTag() && user.equals(tag)) {
                index = i;
            }

        }

        return index;

    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.print("<tr>");

        /**
         * provides link to each separate news story. It will check to make sure
         * that the child exist first by checking that the index provided by
         * getChildElement is > -1
         */

        String link = "";
        if (getChildElement(item, "link") > -1) {
            link = item.child(getChildElement(item, "link")).child(0).label();
        } else {
            link = "no link";
        }

        /**
         * provides link to each separate news story. It will check to make sure
         * that the child exist first by checking that the index provided by
         * getChildElement is > -1
         */

        //added behavior that checks if title has child
        if ((getChildElement(item, "title") > -1)
                && (item.child(getChildElement(item, "title"))
                        .numberOfChildren() != 0)) {
            out.println("<td>");
            out.print("<a href=\"");
            out.print(link);
            out.print("\">");
            out.print(item.child(getChildElement(item, "title")).child(0)
                    .label());
            out.print("</a>");
            out.println("</td>");

        } else if (getChildElement(item, "description") > -1) {
            out.println("<td>");
            out.print("<a href=\"");
            out.print(link);
            out.print("\">");
            out.print(item.child(getChildElement(item, "description")).child(0)
                    .label());
            out.print("</a>");
            out.println("</td>");

        } else {
            out.print("<td>");
            out.print("<a href=\"");
            out.print(link);
            out.print("\">");
            out.print("No title available");
            out.print("</a>");
            out.print("</td>");

        }

        /**
         * Checks for source link. It will check to make sure that the child
         * exist first by checking that the index provided by getChildElement is
         * > -1
         */

        if ((getChildElement(item, "source") > -1)
                && (item.child(getChildElement(item, "source"))
                        .numberOfChildren() != 0)) {
            out.println("<td>");
            out.print("<a href=\"");
            out.print(item.child(getChildElement(item, "source"))
                    .attributeValue("url"));
            out.print("\">");
            out.print(item.child(getChildElement(item, "source")).child(0)
                    .label());
            out.print("</a>");
            out.println("</td>");

            //added behavior for no children but still contains url.
        } else if ((getChildElement(item, "source") > -1)
                && (item.child(getChildElement(item, "source"))
                        .numberOfChildren() == 0)) {
            out.println("<td>");
            out.print("<a href=\"");
            out.print(item.child(getChildElement(item, "source"))
                    .attributeValue("url"));
            out.print("\">");
            out.print("this source has no child");
            out.print("</a>");
            out.println("</td>");

        } else {
            out.print("<td>");
            out.print("No source available");
            out.println("</td>");
        }

        /**
         * Finds the date of publishing. It will check to make sure that the
         * child exist first by checking that the index provided by
         * getChildElement is > -1
         */

        if (getChildElement(item, "pubDate") > -1) {
            out.print("<td>");
            out.print(item.child(getChildElement(item, "pubDate")).child(0)
                    .label());
            out.println("</td>");
        } else {
            out.print("<td>");
            out.print("No published date available");
            out.println("</td>");
        }

        out.println("</tr>");

    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */

    private static void processFeed(String url, String file, SimpleWriter out) {

        /**
         * This condition makes sure that the input url is a valid rss type. It
         * does so by checking the attribute for the rss version which MUST be
         * 2.0 in this case.
         */

        XMLTree xml = new XMLTree1(url);
        //This was used to double check my indexing
        //xml.display();

        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {

            //this simplewriter writes to a .html file.
            SimpleWriter html = new SimpleWriter1L(file);

            /**
             * finds the first instance of the <channel> node which should be
             * the root node. There are exceptions to this, such as:
             * (https://www.huffpost.com/section/front-page/feed?x=1). In this
             * case, the child(0) of this site's xml tree is not <channel>, so I
             * had to be specific about wanting my root in my rssreader to be
             * channel.
             */

            //grabs first instance of channel node
            //(channel should be the root node for most cases but isn't always)
            XMLTree channel = xml.child(getChildElement(xml, "channel"));

            //channel.display();

            //calls header method
            outputHeader(channel, html);

            //this loop will check for every instance of an item tag.
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    XMLTree item = channel.child(i);
                    processItem(item, html);
                }
            }

            //calls footer method
            outputFooter(html);

            html.close();

        }
    }

    /**
     * This method creates the index page that provides links to the other rss
     * news feeds.
     *
     * @param feeds
     * @param out
     * @param in
     */

    private static void sourcePage(XMLTree feeds, SimpleWriter out,
            SimpleReader in) {

        if ((feeds.isTag()) && (feeds.label().equals("feeds"))) {

            out.print("Input a name for a .html file: ");
            String filename = in.nextLine();

            //simplewriter that prints to separate html files.
            SimpleWriter indexPage = new SimpleWriter1L(filename);

            if (feeds.hasAttribute("title")) {

                //note: I tried using out.print at first and then realized
                //that it wouldn't print
                //to the various separate pages.
                indexPage.print("<html>");
                indexPage.print("<head>");
                indexPage.print("<title>");
                indexPage.print(feeds.attributeValue("title"));
                indexPage.println("</title>");
                indexPage.print("</head>");
                indexPage.print("<body>");
                indexPage.print("<h1>");
                indexPage.print(feeds.attributeValue("title"));
                indexPage.print("</h1>");
            }

            //loop that creates the separate html files in the provided by the urls.
            for (int i = 0; i < feeds.numberOfChildren(); i++) {
                if (feeds.child(i).label().equals("feed")) {
                    String file = feeds.child(i).attributeValue("file");
                    String url = feeds.child(i).attributeValue("url");

                    SimpleWriter feed = new SimpleWriter1L(file);

                    //calls method that checks for valid rss type.
                    processFeed(url, file, feed);
                    //prints source links in an unordered list.
                    indexPage.print("<ul>");
                    indexPage.print("<li><a href=\"" + file + "\">"
                            + feeds.child(i).attributeValue("name")
                            + "</a></li>");
                    indexPage.print("</ul>");

                    feed.close();
                }
            }
            indexPage.print("</body>");
            indexPage.print("</html>");
            indexPage.close();
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here only used for calling
     *            methods and firdst input.
     */

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a valid RSS 2.0 feed: ");
        String link = in.nextLine();
        XMLTree feeds = new XMLTree1(link);
        //feeds.display();

        //calls method that creates source pages.
        sourcePage(feeds, out, in);

        in.close();
        out.close();

    }

}