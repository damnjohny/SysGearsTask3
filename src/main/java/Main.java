import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String input = "src/input.json";
    private static final File output = new File("src/output.json");

    private static List<JSONObject> objectList = new ArrayList<>();
    private static List<List<JSONObject>> objectLists = new ArrayList<>();

    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader(input);
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(fileReader);

            JSONArray inquirerArray = (JSONArray) object.get("inquirer");

            var iterator = inquirerArray.iterator();
            JSONObject questionObject = (JSONObject) iterator.next(); // {"question":"What is your marital status?","answer":["Single","Married"]}

            JSONArray questionArray = (JSONArray) questionObject.get("answer"); // ["Single","Married"]

            String first_answer = questionArray.get(0).toString(); // Single
            String second_answer = questionArray.get(1).toString(); // Married

            while (iterator.hasNext()) {
                JSONObject nextQuestion = (JSONObject) iterator.next();

                if (nextQuestion.containsKey(first_answer)) {

                }
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
