package ru.otus.hw3.server.services;

import net.sf.saxon.xpath.XPathFactoryImpl;
import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import ru.otus.hw3.db.model.Employee;
import ru.otus.hw3.marshalling.EmployeeListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static ru.otus.hw3.util.ConstantHolder.XML_FORMAT;

public class XmlService {
    private static XmlService instance = null;
    private static final Logger logger = Logger.getLogger(XmlService.class.getName());
    private static final String XPATH_OVER_AVG_PATTERN = "//employees/employee[salary > avg(//employees/employee/salary)]/*[not(*)]";

    public static XmlService getInstance() {
        if (instance == null) {
            instance = new XmlService();
        }
        return instance;
    }

    private XmlService() {
        super();
    }

    public File marshall(List<Employee> employee) {
        try {
            File file = File.createTempFile(UUID.randomUUID().toString(), XML_FORMAT);
            EmployeeListWrapper data = new EmployeeListWrapper();
            data.setEmployeeList(employee);
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeListWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(data, file);

            return file;
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }

    public List<Employee> unmarshal(FileItem fileItem) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeListWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EmployeeListWrapper data = (EmployeeListWrapper) jaxbUnmarshaller.unmarshal(fileItem.getInputStream());
            return data.getEmployeeList();
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }

    public Document readEmployeeXML(FileItem fileItem) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(fileItem.getInputStream());
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }

    public Map<String, String> getTagsMostPayed(Document document) {
        Map<String, String> result = new HashMap<>();
        try {
            XPathFactory factory = new XPathFactoryImpl();
            XPath xPath = factory.newXPath();
            NodeList nodes = (NodeList) xPath.evaluate(
                    XPATH_OVER_AVG_PATTERN,
                    document.getDocumentElement(),
                    XPathConstants.NODESET
            );
            for (int i = 0; i < nodes.getLength(); i++) {
                result.put(nodes.item(i).getNodeName(), nodes.item(i).getFirstChild().getTextContent());
            }
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return result;
    }
}