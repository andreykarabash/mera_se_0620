package src.lesson9;

import java.util.ArrayList;
import java.util.HashMap;


public class Deserializer {
    static ArrayList<Object> deserialize(String json) throws IllegalAccessException, InstantiationException {
        ArrayList<Object> classes = getClasses();
        ArrayList<Object> deserialisationResalt = new ArrayList<>();
        if(json.isEmpty() || classes.isEmpty()){
            return deserialisationResalt;
        }

        ArrayList<HashMap<String, String>> jsonSplitResult = jsonSplit(json);

        for(HashMap<String, String> i: jsonSplitResult){
            for(Object k: classes){
                if(i.get("ClassType").equals(k.getClass().getAnnotation(JsonTypeName.class).value())){
                    deserialisationResalt.add(k.getClass().newInstance());
                }
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

    private static ArrayList<Object> getClasses() throws InstantiationException, IllegalAccessException{
        ArrayList<Object> classes = new ArrayList<>();
        classes.add(MonsterMeleeAttack.class.newInstance());
        classes.add(MagicLightning.class.newInstance());
        classes.add(ChainLightning.class.newInstance());
        classes.add(Healing.class.newInstance());
        classes.add(Firewall.class.newInstance());
        classes.add(BurningTouch.class.newInstance());
        classes.add(Migraine.class.newInstance());
        classes.add(Exorcism.class.newInstance());
        return classes;
    }
}