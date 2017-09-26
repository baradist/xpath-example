package cf.baradist.xpath_example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

/**
 * Created by Oleg Grigorjev on 27.09.2017.
 */
public class XPathReaderTest {
    private XPathReader reader;
    private static String TEST_STRING = "<?xml version=\"1.0\"?>\n" +
            "<Employees>\n" +
            "    <Employee emplid=\"1111\" type=\"admin\">\n" +
            "        <firstname>John</firstname>\n" +
            "        <lastname>Watson</lastname>\n" +
            "        <age>30</age>\n" +
            "        <email>johnwatson@sh.com</email>\n" +
            "    </Employee>\n" +
            "    <Employee emplid=\"2222\" type=\"admin\">\n" +
            "        <firstname>Sherlock</firstname>\n" +
            "        <lastname>Homes</lastname>\n" +
            "        <age>32</age>\n" +
            "        <email>sherlock@sh.com</email>\n" +
            "    </Employee>\n" +
            "    <Employee emplid=\"3333\" type=\"user\">\n" +
            "        <firstname>Jim</firstname>\n" +
            "        <lastname>Moriarty</lastname>\n" +
            "        <age>52</age>\n" +
            "        <email>jim@sh.com</email>\n" +
            "    </Employee>\n" +
            "    <Employee emplid=\"4444\" type=\"user\">\n" +
            "        <firstname>Mycroft</firstname>\n" +
            "        <lastname>Holmes</lastname>\n" +
            "        <age>41</age>\n" +
            "        <email>mycroft@sh.com</email>\n" +
            "    </Employee>\n" +
            "</Employees>";

    @BeforeMethod
    public void setUp() throws Exception {
        reader = new XPathReader(new ByteArrayInputStream(TEST_STRING.getBytes()));
    }

    @Test
    public void testGetValue() throws Exception {
        // TODO:
    }

    @Test
    public void testGetNode() throws Exception {

    }

    @Test
    public void testGetList() throws Exception {

    }

}