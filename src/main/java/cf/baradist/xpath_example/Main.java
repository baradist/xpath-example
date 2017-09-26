package cf.baradist.xpath_example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Oleg Grigorjev on 27.09.2017.
 */
public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        FileInputStream inputStream = new FileInputStream("src/main/resources/employees.xml");
        XPathReader reader = new XPathReader(inputStream);

        System.out.println("Employee with emplid=3333");
        System.out.println(reader.getValue("/Employees/Employee[@emplid='3333']/email"));
        System.out.println("3. Examples: Query XML document using XPath");
        System.out.println("3.1 Read firstname of all employees");
        System.out.println(reader.getList("/Employees/Employee/firstname"));
        System.out.println("3.2 Read a specific employee using employee id");
        System.out.println(reader.getNode("/Employees/Employee[@emplid='3333']"));
        System.out.println("3.3 Read firstname of all employees who are admin");
        System.out.println(reader.getList("/Employees/Employee[@type='admin']/firstname"));
        System.out.println("3.4 Read firstname of all employees who are older than 40 year");
        System.out.println(reader.getList("/Employees/Employee[age>40]/firstname"));
        System.out.println("3.5 Read firstname of first two employees (defined in xml file)");
        System.out.println(reader.getList("/Employees/Employee[position() <= 2]/firstname"));
    }
}
