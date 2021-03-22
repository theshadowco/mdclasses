package com.github._1c_syntax.mdclasses.mdoreader;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MDOReader {

  /**
   * Фабрика для чтения
   */
  private XMLInputFactory xmlInputFactory;

  /**
   * Соответствие имен тегов классам
   */
  private static Map<String, Class<?>> pojoClasses = new HashMap<>();
  private static Map<Class<?>, Map<String, Field>> pojoFields = new HashMap<>();
  private static Map<String, Converter> converters = new HashMap<>();

  MDOReader() {
    xmlInputFactory = XMLInputFactory.newInstance();
  }

  public void registerClass(@NonNull String fullName, @NonNull Class<?> clazz) {
    pojoClasses.put(fullName, clazz);
  }

  public void registerClass(@NonNull Class<?> clazz) {
    var annotation = clazz.getAnnotation(MDClass.class);
    if (annotation == null) {
      return;
    }
    pojoClasses.put(String.format("{%s}%s", annotation.namespaceEDT(), annotation.nameEDT()), clazz);
    pojoClasses.put(String.format("{%s}%s", annotation.namespaceDesigner(), annotation.nameDesigner()), clazz);

    Map<String, Field> fields = new HashMap<>();
    Arrays.stream(FieldUtils.getAllFields(clazz)).filter(fld -> fld.getAnnotation(MDField.class) != null)
      .forEach(field -> {
        var annotationFld = field.getAnnotation(MDField.class);
        if (annotationFld.isAttributeEDT()) {
          fields.put(annotationFld.nameEDT(), field);
        } else {
          var namespaceEDT = annotationFld.namespaceEDT().isEmpty() ? annotation.namespaceEDT() : annotationFld.namespaceEDT();
          fields.put(String.format("{%s}%s", namespaceEDT, annotationFld.nameEDT()), field);
          if (annotationFld.converterEDT().getSuperclass() == Converter.class) {
            try {
              converters.put(String.format("{%s}%s", namespaceEDT, annotationFld.nameEDT()), (Converter) annotationFld.converterEDT().getConstructor().newInstance());
            } catch (InstantiationException e) {
              e.printStackTrace();
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
          }
        }

        if (annotationFld.isAttributeDesigner()) {
          fields.put(annotationFld.nameDesigner(), field);
        } else {
          var namespaceDesigner = annotationFld.namespaceDesigner().isEmpty() ? annotation.namespaceDesigner() : annotationFld.namespaceDesigner();
          fields.put(String.format("{%s}%s", namespaceDesigner, annotationFld.nameDesigner()), field);
          if (annotationFld.converterDesigner().getSuperclass() == Converter.class) {
            try {
              converters.put(String.format("{%s}%s", namespaceDesigner, annotationFld.nameDesigner()), (Converter) annotationFld.converterDesigner().getConstructor().newInstance());
            } catch (InstantiationException e) {
              e.printStackTrace();
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
          }
        }


      });
    pojoFields.put(clazz, fields);
  }

  @SneakyThrows
  public Object read(File path) {
    var reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));


    while (reader.hasNext()) {
      XMLEvent nextEvent = reader.nextEvent();

      if (nextEvent.isStartDocument()) {
        continue;
      }

      if (nextEvent.isStartElement()) {
        var name = nextEvent.asStartElement().getName();
        var className = name.getLocalPart();
        var namespace = name.getNamespaceURI();
        Class<?> clazz = getPojoClass(name);
        if (clazz != null) {
          return readClass(clazz, reader, nextEvent);
        }
      }
      var a = 1;
//      nextEvent.isNamespace()
//
//      if (nextEvent.isStartElement()) {
//        StartElement startElement = nextEvent.asStartElement();
//        switch (startElement.getName().getLocalPart()) {
//          case "website":
//            website = new WebSite();
//            Attribute url = startElement.getAttributeByName(new QName("url"));
//            if (url != null) {
//              website.setUrl(url.getValue());
//            }
//            break;
//          case "name":
//            nextEvent = reader.nextEvent();
//            website.setName(nextEvent.asCharacters().getData());
//            break;
//          case "category":
//            nextEvent = reader.nextEvent();
//            website.setCategory(nextEvent.asCharacters().getData());
//            break;
//          case "status":
//            nextEvent = reader.nextEvent();
//            website.setStatus(nextEvent.asCharacters().getData());
//            break;
//        }
//      }
//      if (nextEvent.isEndElement()) {
//        EndElement endElement = nextEvent.asEndElement();
//        if (endElement.getName().getLocalPart().equals("website")) {
//          websites.add(website);
//        }
//      }
    }
    return null;
  }

  @SneakyThrows
  private <T> T readClass(Class<T> clazz, XMLEventReader reader, XMLEvent nextEvent) {
    T obj = clazz.getConstructor().newInstance();
    int level = 1;
    var attributes = readAttributes(nextEvent.asStartElement().getAttributes());
    var fields = pojoFields.get(clazz);
    attributes.forEach((name, value) -> {
      var field = fields.get(name);
      if (field != null) {
        try {
          FieldUtils.writeField(field, obj, value, true);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });

    while (reader.hasNext()) {
      nextEvent = reader.nextEvent();

      if (nextEvent.isStartElement()) {
        var element = nextEvent.asStartElement();
        var field = fields.get(element.getName().toString());
        if (field != null) {
          Object value;
          if(field.getType() == List.class) {
            value = field.getType().cast(FieldUtils.readField(field, obj, true));
            if(((List) value).isEmpty()) {
              value = field.getType().cast(new ArrayList<>());
            }
            Class<?> componentType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            if(componentType != null) {
              var oneVal = componentType.cast(readValue(componentType, reader, nextEvent));
              value.getClass().getDeclaredMethod("add", componentType).invoke(value, oneVal);
            }
          } else {
            value = field.getType().cast(readValue(field.getType(), reader, nextEvent));
          }
          if (value != null) {
            try {
              FieldUtils.writeField(field, obj, value, true);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            }
          }
        } else {
          level++;
        }
      } else if (nextEvent.isEndElement()) {
        level--;
        if (level == 0) {
          break;
        }
      } else {
        // noop
      }
    }
    return obj;
  }

  @SneakyThrows
  private <T> T readValue(Class<T> type, XMLEventReader reader, XMLEvent nextEvent) {
    var name = nextEvent.asStartElement().getName();
    var converter = converters.get(name.toString());
    if (converter != null) {
      return type.cast(converter.unmarshal(reader, nextEvent));
    } else if (getPojoClass(name) != null) {
      return type.cast(readClass(getPojoClass(name), reader, nextEvent));
    } else {
      T value = null;
      if (type == String.class) {
        nextEvent = reader.nextEvent();
        if (nextEvent.isCharacters()) {
          value = type.cast(nextEvent.asCharacters().getData());
          reader.nextEvent();
        }
      }
      return value;
    }
  }

  private Class<?> getPojoClass(QName schemaType) {
    return pojoClasses.get(schemaType.toString());
  }

  private Map<String, String> readAttributes(Iterator<Attribute> xmlAttributes) {
    Map<String, String> attributes = new HashMap<>();
    xmlAttributes.forEachRemaining(attribute -> attributes.put(
      attribute.getName().toString(), attribute.getValue()));
    return attributes;
  }


//
//
//  = XMLInputFactory.newInstance();
//  XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
}
