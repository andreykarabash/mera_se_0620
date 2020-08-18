package src.lesson8;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Deserializer {
    static List<Object> deserialize(String json, List<? extends Class> classes) throws IllegalAccessException, InstantiationException {
        List<Object> deserialisationResalt = new ArrayList<>();
        if(json.isEmpty() || classes.isEmpty()){
            return deserialisationResalt;
        }
        ArrayList<HashMap<String, String>> jsonSplitResult = jsonSplit(json);


        for (Class i:classes){

            for(HashMap<String,String> jsonObject: jsonSplitResult){
                Object instance = i.newInstance();

                if (!jsonObject.containsKey("ClassType")){
                    continue;
                }
                if (!jsonObject.get("ClassType").equals(instance.getClass().getAnnotation(JsonTypeName.class).value())){
                    continue;
                }

                Field[] fields = instance.getClass().getDeclaredFields();
                for (Field field: fields){
                    if(field.isAnnotationPresent(JsonIgnore.class)){
                        continue;
                    }
                    field.setAccessible(true);
                    if(field.isAnnotationPresent(JsonName.class)) {
                        if (field.getType()==int.class) {
                            field.set(instance, Integer.parseInt(jsonObject.get(field.getAnnotation(JsonName.class).value())));
                        }
                        else if (field.getType()==boolean.class) {
                            field.set(instance, Boolean.valueOf(jsonObject.get(field.getAnnotation(JsonName.class).value())));
                        }
                        else{
                            field.set(instance, jsonObject.get(field.getAnnotation(JsonName.class).value()));
                        }
                    }
                    else{
                        if (field.getType()==int.class) {
                            field.set(instance, Integer.parseInt(jsonObject.get(field.getName())));
                        }
                        else if(field.getType()==boolean.class){
                            field.set(instance, Boolean.valueOf(jsonObject.get(field.getName())));
                        }
                        else{
                            field.set(instance, jsonObject.get(field.getName()));
                        }
                    }
                }
                deserialisationResalt.add(instance);
            }
        }
        return deserialisationResalt;
    }

    private static ArrayList<HashMap<String, String>> jsonSplit(String json){
        ArrayList<HashMap<String, String>> jsonOojectResult = new ArrayList<>();

        json = json.replace("\"", "");
        json = json.substring(1);
        json = json.substring(0, json.length()-1);

        String[] jsonObjects = json.split("}");

        for(String i: jsonObjects){
            HashMap<String, String> jsonObject = new HashMap<>();
            i = i.substring(1);
            i = i.substring(0, i.length()-1);

            String[] jsonObjectsfields = i.split(",");
            for (String k: jsonObjectsfields){

                String[] jsonObjectsfieldsValue = k.split(":");
                jsonObject.put(jsonObjectsfieldsValue[0], jsonObjectsfieldsValue[1]);
            }
            jsonOojectResult.add(jsonObject);
        }
        return jsonOojectResult;
    }
}
