package ru.nsu.fit.dis.valkova.parser;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class PartialUnmarshaller<T> implements Iterable<T>, Iterator<T> {

    private final Class<T> clazz;
    private final Unmarshaller unmarshaller;
    private final XMLStreamReader reader;
    boolean endOfNodes = false;

    public PartialUnmarshaller(InputStream stream, Class<T> clazz) throws XMLStreamException, JAXBException {
        this.clazz = clazz;
        this.unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
        this.reader = XMLInputFactory.newInstance().createXMLStreamReader(stream);
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @SneakyThrows
    @Override
    public boolean hasNext() {
        endOfNodes = skipElements();
        return !endOfNodes && reader.hasNext();
    }

    boolean skipElements() throws XMLStreamException {
        int eventType = reader.getEventType();
        boolean noNodes = false;
        while (eventType != END_DOCUMENT
                && eventType != START_ELEMENT
                || (eventType == START_ELEMENT && !"node".equals(reader.getLocalName()))
        ) {
            if (eventType == START_ELEMENT) {
                String s = reader.getLocalName();
                noNodes = "way".equals(s) || "relation".equals(s);
                if (noNodes) {
                    break;
                }
            }
            eventType = reader.next();
        }
        return noNodes;
    }

    @SneakyThrows
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return unmarshaller.unmarshal(reader, clazz).getValue();
    }
}
