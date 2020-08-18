package com.mera.task8.serializer;

import com.mera.task8.annotations.XmlIgnore;
import com.mera.task8.annotations.XmlName;
import com.mera.task8.annotations.XmlTypeName;

import java.lang.reflect.Field;
import java.util.Collection;

public class XmlSerializer {

    private static final String XML_OPEN_NODE_PREFIX = "<";
    private static final String XML_CLOSE_NODE_PREFIX = "</";
    private static final String XML_NODE_SUFFIX = ">";

    public static String serialize(Collection<?> objects) throws IllegalAccessException {
        if(objects == null || objects.isEmpty()) {
            throw new IllegalArgumentException("Invalid or empty objects input.");
        }

        StringBuilder sb = new StringBuilder();

        for(Object object : objects) {
            String typeName = getReadableClassName(object.getClass());
            sb.append(createOpenNode(typeName));
            for(Field field : object.getClass().getDeclaredFields()) {
                sb.append(convertFieldToXmlString(object, field));
            }
            sb.append(createCloseNode(typeName));
        }

        return sb.toString();
    }

    public static <T> T deserialize(String xmlData, Class<T> klass) throws Exception {
        if(xmlData == null || xmlData.isEmpty()) {
            throw new IllegalArgumentException("Invalid or empty xml string input.");
        }

        String className = getReadableClassName(klass);
        String openNodeName = createOpenNode(className);
        String closeNodeName = createCloseNode(className);

        final int startIndex = xmlData.indexOf(openNodeName);
        final int endIndex = xmlData.indexOf(closeNodeName);

        if(startIndex < 0 || endIndex < 0) {
            throw new XmlSerializerException("Xml string input doesn't "
                                             + "contain object of class " + klass.getSimpleName() + ".");
        }
        if(startIndex >= endIndex) {
            throw new XmlSerializerException("Xml string format error: close node found before open node");
        }
        //Create new instance of the specified class (since we found xml node with readable class name)
        T object = klass.getDeclaredConstructor().newInstance();
        //Extract object field data from input XML data
        String objectFieldData = xmlData.substring(startIndex + openNodeName.length(), endIndex);
        if(objectFieldData.isEmpty()) {
            return object;
        }

        for (Field field : klass.getDeclaredFields()) {
            if(isFieldIgnored(field) || isFieldNotSerializable(field)) {
                continue;
            }
            field.setAccessible(true);
            final String fieldName = getReadableFieldName(field);
            final String fieldValue = extractFieldValueFromXml(objectFieldData, fieldName);
            if(!fieldValue.isEmpty()) {
                setFieldValue(object, field, fieldValue);
            }
        }

        return object;
    }

    private static String createOpenNode(String name) {
        return XML_OPEN_NODE_PREFIX + name + XML_NODE_SUFFIX;
    }

    private static String createCloseNode(String name) {
        return XML_CLOSE_NODE_PREFIX + name + XML_NODE_SUFFIX;
    }

    private static String getReadableClassName(Class<?> klass) {
        final XmlTypeName annotation = klass.getAnnotation(XmlTypeName.class);
        if(annotation == null) {
            return klass.getSimpleName();
        }

        final String name = annotation.name();
        if(!name.isEmpty()) {
            return name;
        }
        return klass.getSimpleName();
    }

    private static String convertFieldToXmlString(Object fieldOwner, Field field) throws IllegalAccessException {
        if(isFieldIgnored(field) || isFieldNotSerializable(field)) {
            return "";
        }

        field.setAccessible(true);
        final String fieldName = getReadableFieldName(field);
        return createOpenNode(fieldName) + field.get(fieldOwner) + createCloseNode(fieldName);
    }

    private static boolean isFieldNotSerializable(Field field) {
        Class<?> fieldType = field.getType();
        return (!fieldType.isPrimitive()) && (fieldType != String.class)
                && (fieldType != Integer.class) && (fieldType != Long.class)
                && (fieldType != Character.class) && (fieldType != Short.class)
                && (fieldType != Float.class) && (fieldType != Double.class)
                && (fieldType != Boolean.class) && (fieldType != Byte.class);
    }

    private static boolean isFieldIgnored(Field field) {
        return field.isAnnotationPresent(XmlIgnore.class);
    }

    private static String getReadableFieldName(Field field) {
        final XmlName annotation = field.getAnnotation(XmlName.class);
        if (annotation == null) {
            return field.getName();
        }

        final String name = annotation.name();
        if (!name.isEmpty()) {
            return name;
        }
        return field.getName();
    }

    private static String extractFieldValueFromXml(String xmlData, String fieldName) {
        String openNodeName = createOpenNode(fieldName);
        String closeNodeName = createCloseNode(fieldName);
        final int startIndex = xmlData.indexOf(openNodeName);
        final int endIndex = xmlData.indexOf(closeNodeName);
        if(startIndex < 0 || endIndex < 0 || startIndex >= endIndex ) {
            return "";
        }
        return xmlData.substring(startIndex + openNodeName.length(), endIndex);
    }

    private static void setFieldValue(Object filedOwner, Field field, String value) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if(fieldType == String.class) {
            field.set(filedOwner, value);
        }
        else if(fieldType == int.class || fieldType == Integer.class) {
            field.set(filedOwner, Integer.valueOf(value));
        }
        else if(fieldType == long.class || fieldType == Long.class) {
            field.set(filedOwner, Long.valueOf(value));
        }
        else if(fieldType == boolean.class || fieldType == Boolean.class) {
            field.set(filedOwner, Boolean.valueOf(value));
        }
        else if(fieldType == double.class || fieldType == Double.class) {
            field.set(filedOwner, Double.valueOf(value));
        }
        else if(fieldType == float.class || fieldType == Float.class) {
            field.set(filedOwner, Float.valueOf(value));
        }
        else if(fieldType == short.class || fieldType == Short.class) {
            field.set(filedOwner, Short.valueOf(value));
        }
        else if(fieldType == byte.class || fieldType == Byte.class) {
            field.set(filedOwner, Byte.valueOf(value));
        }
        else if(fieldType == char.class || fieldType == Character.class) {
            field.set(filedOwner, value.charAt(0));
        }
    }
}
