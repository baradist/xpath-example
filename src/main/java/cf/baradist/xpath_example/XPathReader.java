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
import java.util.StringJoiner;

import static javax.xml.xpath.XPathConstants.NODE;
import static javax.xml.xpath.XPathConstants.NODESET;

/**
 * Created by Oleg Grigorjev on 26.09.2017.
 */
public class XPathReader {
    private Document document;
    private XPath xPath;

    public XPathReader(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        document = builder.parse(inputStream);
        xPath = XPathFactory.newInstance().newXPath();
    }

    public String getValue(String expression) throws XPathExpressionException {
        return xPath.compile(expression).evaluate(document);
    }

    public Node getNode(String expression) throws XPathExpressionException {
        return (Node) xPath.compile(expression).evaluate(document, NODE);
    }

    public List<String> getList(String expression) throws XPathExpressionException {
        NodeList nodeList = getNodeList(expression);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(nodeList.item(i).getFirstChild().getNodeValue());
        }
        return result;
    }

    public NodeList getNodeList(String expression) throws XPathExpressionException {
        return (NodeList) xPath.compile(expression).evaluate(document, NODESET);
    }

    public static void print(Node node) {
        System.out.println(nodeToString(node));
    }

    public static String nodeToString(Node node) {
        final StringJoiner joiner = new StringJoiner("\n");
        if (null != node) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
                Node nod = nodeList.item(i);
                if (nod.getNodeType() == Node.ELEMENT_NODE) {
                    joiner.add((nodeList.item(i).getNodeName())
                            + " : "
                            + nod.getFirstChild().getNodeValue());
                }
            }
        }
        return joiner.toString();
    }
}
