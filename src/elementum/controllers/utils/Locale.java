package elementum.controllers.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author Alexander Elbracht
 */
public class Locale {
    static final String locale = "de_DE";

    private Document document;

    public Locale() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream inputStream = getClass().getResourceAsStream(String.format("/elementum/l18n/%s.xml", locale));
        document = builder.parse(inputStream);
    }

    public String getString(String tag, String name) {
        Node parent = document.getElementsByTagName(tag).item(0);
        NodeList nodes = parent.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if(node.getNodeType() == node.ELEMENT_NODE) {
                if (node.getNodeName() == name) {
                    return node.getTextContent();
                }
            }
        }

        return null;
    }
}
