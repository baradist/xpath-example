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
        String command = args[0];
        FileInputStream inputStream = new FileInputStream(args[1]);
        String xPath = args[2];

        XPathReader reader = new XPathReader(inputStream);
        switch (command) {
            case "value":
                System.out.println(reader.getValue(xPath));
                break;
            case "list":
                System.out.println(reader.getList(xPath));
                break;
            case "node":
                System.out.println(XPathReader.nodeToString(reader.getNode(xPath)));
                break;
            default:
                System.out.println("Put following arguments: (value | list | node) path_to_xml xPath_expression");
        }
    }
}
