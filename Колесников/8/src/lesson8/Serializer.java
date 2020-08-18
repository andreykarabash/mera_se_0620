package src.lesson8;

import java.lang.reflect.Field;
import java.util.List;

public class Serializer {
    static String serialize(List<? extends Object> items) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder("[");

        for (Object item : items) {
            if (!item.getClass().isAnnotationPresent(JsonTypeName.class)){
                continue;
            }

            sb.append("{");

            sb.append("\"ClassType\"" + ":" + "\"" + item.getClass().getAnnotation(JsonTypeName.class).value() + "\",");

            for (Field field : item.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(JsonIgnore.class)) {
                    continue;
                }

                field.setAccessible(true);

                String prefix = "";
                String suffix = "";
                if (field.getType() == String.class) {
                    prefix = "\"";
                    suffix = "\"";
                }

                String fieldValue = prefix + field.get(item) + suffix;

                final String name = getFieldName(field);
                sb.append("\"" + name + "\"" + ":" + fieldValue + ",");
            }

            sb.append("}");
        }

        sb.append("]");
        String text = sb.toString();
        if (text.length() == 2){
            return "";
        }
        return text;
    }

    private static String getFieldName(Field field) {

        final JsonName annotation = field.getAnnotation(JsonName.class);
        if (annotation == null) {
            return field.getName();
        }


        final String value = annotation.value();
        if (!value.isEmpty()) {
            return value;
        }
        return field.getName();
    }
}
