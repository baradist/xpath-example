package cf.baradist.xpath_example;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Oleg Grigorjev on 27.09.2017.
 */
public class EmployeeNodeMapper {
    public Employee nodToEmployee(Node node) {
        Employee employee = new Employee();
        if (node != null) {
            String emplid = ((DeferredElementImpl) node).getAttribute("emplid");
            employee.setId(Long.valueOf(emplid));
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
                Node nod = nodeList.item(i);
                if (nod.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = nodeList.item(i).getNodeName();
                    String value = nod.getFirstChild().getNodeValue();
                    switch (nodeName) {
                        case "firstname":
                            employee.setFirstname(value);
                            break;
                        case "lastname":
                            employee.setLastname(value);
                            break;
                        case "age":
                            employee.setAge(Integer.valueOf(value));
                            break;
                        case "email":
                            employee.setEmail(value);
                            break;
                    }
                }
            }
        }

        return employee;
    }
}
