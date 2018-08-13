package ru.job4j.sql;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class ConvertXSQT {

    public void convert(File source, File dest, File schem) throws FileNotFoundException, TransformerException {
        InputStream inputSchem = new FileInputStream(schem);
        InputStream inputSource = new FileInputStream(source);
        OutputStream write = new FileOutputStream(dest);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(inputSchem));
        transformer.transform(new StreamSource(inputSource), new StreamResult(write));
    }

    static class SAXPars extends DefaultHandler {
        private int sum = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            this.sum += Integer.valueOf(attributes.getValue("field"));
        }

        public int sumOfAttr(File source)  {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser parser = factory.newSAXParser();
                parser.parse(source, new SAXPars());
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (SAXException se) {
                se.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return this.sum;
        }
    }
}
