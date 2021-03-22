package com.github._1c_syntax.mdclasses.mdoreader.converters;

import com.github._1c_syntax.mdclasses.mdo.MDOSynonym;
import com.github._1c_syntax.mdclasses.mdoreader.Converter;
import lombok.SneakyThrows;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;

public class DesignerSynonym implements Converter<List<MDOSynonym>> {

  private static final String ITEM_NAME = "item";
  private static final String CONTENT_NAME = "content";
  private static final String LANG_NAME = "lang";

  @SneakyThrows
  @Override
  public List<MDOSynonym> unmarshal(XMLEventReader reader, XMLEvent nextEvent) {
    List<MDOSynonym> result = new ArrayList<>();

    while (reader.hasNext()) {
      nextEvent = reader.nextEvent();

      if (nextEvent.isStartElement() && ITEM_NAME.equals(nextEvent.asStartElement().getName().getLocalPart())) {
        var synonym = new MDOSynonym();
        while (reader.hasNext()) {
          nextEvent = reader.nextEvent();
          if (nextEvent.isStartElement()) {
            switch (nextEvent.asStartElement().getName().getLocalPart()) {
              case CONTENT_NAME:
                nextEvent = reader.nextEvent();
                if (nextEvent.isCharacters()) {
                  synonym.setContent(nextEvent.asCharacters().getData());
                  reader.nextEvent();
                }
                break;
              case LANG_NAME:
                nextEvent = reader.nextEvent();
                if (nextEvent.isCharacters()) {
                  synonym.setLanguage(nextEvent.asCharacters().getData());
                  reader.nextEvent();
                }
                break;
              default:
                // noop
            }
          }
        }
        result.add(synonym);
        reader.nextEvent();
      } else {
        break;
      }
    }
    return result;
  }
}
