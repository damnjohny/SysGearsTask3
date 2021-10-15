import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final String input = "src/input.json";
    private static final File output = new File("src/output.json");

    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader(input);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONArray inquirer = (JSONArray) jsonObject.get("inquirer");
            JSONObject mainQuestion = (JSONObject) inquirer.get(0);
            JSONObject main = new JSONObject();
            main.put(mainQuestion.get("main_question"), mainQuestion.get("main_answer"));

            var iterator = inquirer.iterator();

            List<List<JSONObject>> singleLists = new ArrayList<>();
            List<List<JSONObject>> marriedLists = new ArrayList<>();

            List<JSONObject> singleList = new ArrayList<>();
            singleList.add(main);
            List<JSONObject> marriedList = new ArrayList<>();
            marriedList.add(main);

// первый раз в список записываем main и следующий обьект
// следущие разы мы копируем старый список и добавляем в него следующий элемент

            while (iterator.hasNext()) {
                JSONObject innerObject = (JSONObject) iterator.next();
                if (innerObject.containsKey("Single")) {
                    List<JSONObject> innerList = new ArrayList<>();
                    innerList.addAll(singleList);
                    JSONObject json = new JSONObject();
                    json.put(innerObject.get("Single"), innerObject.get("answer"));
                    singleList.add(json);
                    singleLists.add(innerList);


                } else if (innerObject.containsKey("Married")) {
                    List<JSONObject> innerList = new ArrayList<>();
                    innerList.addAll(marriedList);
                    JSONObject json = new JSONObject();
                    json.put(innerObject.get("Married"), innerObject.get("answer"));
                    marriedList.add(json);
                    marriedLists.add(marriedList);

                }
            }

            for (List next : singleLists) {
                System.out.println(next);
            }

            for (List next : marriedLists) {
                System.out.println(next);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
