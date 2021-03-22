package com.github._1c_syntax.mdclasses.mdoreader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;

public interface Converter<T> {
  T unmarshal(XMLEventReader reader, XMLEvent nextEvent);
}
