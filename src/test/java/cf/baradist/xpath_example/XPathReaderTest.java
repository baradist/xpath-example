package cf.baradist.xpath_example;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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

    @Before
    public void setUp() throws Exception {
        reader = new XPathReader(new ByteArrayInputStream(TEST_STRING.getBytes()));
    }

    @Test
    public void getEmailByEmplid() throws Exception {
        System.out.println("Employee with emplid=3333");
        String value = reader.getValue("/Employees/Employee[@emplid='3333']/email");
        System.out.println(value);
        assertThat(value, is("jim@sh.com"));
    }

    @Test
    public void getAllFirstnames() throws Exception {
        System.out.println("3.1 Read firstname of all employees");
        List<String> list = reader.getList("/Employees/Employee/firstname");
        System.out.println(list);
        assertThat(list, containsInAnyOrder(new String[]{"John", "Sherlock", "Jim", "Mycroft"}));
    }

    @Test
    public void getEmployeeByEmplid() throws Exception {
        System.out.println("3.2 Read a specific employee using employee id");
        Node node = reader.getNode("/Employees/Employee[@emplid='3333']");
        String string = XPathReader.nodeToString(node);
        System.out.println(string);
        assertThat(string,
                is("firstname : Jim\n" +
                        "lastname : Moriarty\n" +
                        "age : 52\n" +
                        "email : jim@sh.com"));
    }

    @Test
    public void getFirstnamesByType() throws Exception {
        System.out.println("3.3 Read firstname of all employees who are admin");
        List<String> list = reader.getList("/Employees/Employee[@type='admin']/firstname");
        System.out.println(list);
        assertThat(list, containsInAnyOrder(new String[]{"John", "Sherlock"}));
    }

    @Test
    public void getFirstnamesByAge() throws Exception {
        System.out.println("3.4 Read firstname of all employees who are older than 40 year");
        List<String> list = reader.getList("/Employees/Employee[age>40]/firstname");
        System.out.println(list);
        assertThat(list, containsInAnyOrder(new String[]{"Jim", "Mycroft"}));
    }

    @Test
    public void getTwoFirstnames() throws Exception {
        System.out.println("3.5 Read firstname of first two employees (defined in xml file)");
        List<String> list = reader.getList("/Employees/Employee[position() <= 2]/firstname");
        System.out.println(list);
        assertThat(list, containsInAnyOrder(new String[]{"John", "Sherlock"}));
    }
}
