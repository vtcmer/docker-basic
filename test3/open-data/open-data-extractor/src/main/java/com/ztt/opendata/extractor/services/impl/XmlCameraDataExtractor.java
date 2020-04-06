package com.ztt.opendata.extractor.services.impl;

import com.ztt.opendata.extractor.event.IPublisher;
import com.ztt.opendata.extractor.event.impl.PhotoEvent;
import com.ztt.opendata.extractor.model.Photo;
import com.ztt.opendata.extractor.services.DataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class XmlCameraDataExtractor implements DataExtractor {

    // https://attacomsian.com/blog/parsing-xml-response-spring-boot

    @Value(value = "${extractor.cameras.url}")
    String url;

    @Autowired
    IPublisher iPublisher;

    @Override
    public void execute() {
        this.extractData();
    }

    private void extractData(){
        List<Photo> photoList = new ArrayList<Photo>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Camara");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Photo photo = this.processNode(node);
                if (photo != null){
                    photoList.add(photo);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 }

    private Photo processNode(Node node){
        Photo photo = null;
        if (node.getNodeType() == Node.ELEMENT_NODE){
            photo = new Photo();
            Element elem = (Element) node;
            photo.setName(elem.getElementsByTagName("Nombre").item(0).getTextContent());
            photo.setUrl(elem.getElementsByTagName("URL").item(0).getTextContent());
            photo.setLatitude(new BigDecimal(elem.getElementsByTagName("Latitud").item(0).getTextContent()));
            photo.setLongitude(new BigDecimal(elem.getElementsByTagName("Longitud").item(0).getTextContent()));
            photo.setDate(new Date());

            PhotoEvent photoEvent = new PhotoEvent(photo);
            this.iPublisher.sendData(photoEvent);
        }
        return photo;
    }
}
