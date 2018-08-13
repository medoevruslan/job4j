package ru.job4j.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StoreXML {
    private File file;

    public StoreXML(File target) {
        this.file = target;
    }

    public void save(List<Entry> list) {
        try (OutputStream os = new FileOutputStream(this.file)) {
            for (Entry entry : list) {
                JAXBContext context = JAXBContext.newInstance(Entry.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(entry, os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    private static class Entry {
        @XmlElement
        private Field[] field;

        public Entry(Field[] field) {
            this.field = field;
        }

        public Entry() { }

        public Field[] getField() {
            return this.field;
        }
    }

    @XmlRootElement
    private static class Field {
        @XmlElement
        private int value;

        public Field(int value) {
            this.value = value;
        }

        public Field() { }

        public int getValue() {
            return this.value;
        }
    }
}
