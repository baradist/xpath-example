package cf.baradist.xpath_example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.xpath.XPathConstants.NODE;
import static javax.xml.xpath.XPathConstants.NODESET;

/**
 * Created by Oleg Grigorjev on 26.09.2017.
 */
public class XPathReader {
    private Document document;
    private XPath xPath;
    private EmployeeNodeMapper employeeNodeMapper;

    public XPathReader(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        document = builder.parse(inputStream);
        xPath = XPathFactory.newInstance().newXPath();
        employeeNodeMapper = new EmployeeNodeMapper();
    }

    public String getValue(String expression) throws XPathExpressionException {
        return xPath.compile(expression).evaluate(document);
    }

    public Employee getNode(String expression) throws XPathExpressionException {
        Node node = (Node) xPath.compile(expression).evaluate(document, NODE);

        return employeeNodeMapper.nodToEmployee(node);
    }

    public List<String> getList(String expression) throws XPathExpressionException {
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, NODESET);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(nodeList.item(i).getFirstChild().getNodeValue());
        }
        return result;
    }
}
