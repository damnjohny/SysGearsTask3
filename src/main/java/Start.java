import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Start {

    private static final String input = "src/input.json";
    private static final File output = new File("src/output.json");

    public static final List<String> innerList = new ArrayList<>();
    public static final List<List<String>> resultList = new ArrayList<>();

    private static Iterator iterator;
    private static Iterator iterator2;

    private static int counter = 0;
    private static int counter2 = 0;
    private static FileReader fileReader;

    private static String firstQuestionId;
    private static String questionId;

    private static JSONArray inquirerArray;

    static {
        try {
            fileReader = new FileReader(input);
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(fileReader);

            inquirerArray = (JSONArray) object.get("inquirer");

            firstQuestionId = "q1";
            questionId = firstQuestionId;

            iterator = inquirerArray.iterator();
            iterator2 = inquirerArray.iterator();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void startWithFirstAnswer() {
        while (iterator.hasNext()) {
            if (questionId.equals("") || !iterator.hasNext()) {
                List<String> copyOfInnerList = new ArrayList<>(innerList);
                resultList.add(copyOfInnerList);
                innerList.clear();
                questionId = firstQuestionId;
                break;
            }

            JSONObject jsonObject = (JSONObject) iterator.next();

            if (!jsonObject.containsKey(questionId)) {
                counter++;
                continue;
            }

            Parser answered = Parser.parseQuestion(jsonObject, questionId);
            innerList.add(answered.toString());
            questionId = Parser.getFirstAnswerNext();
            counter++;

            if (counter == inquirerArray.size()) {
                List<String> copyOfInnerList = new ArrayList<>(innerList);
                resultList.add(copyOfInnerList);
                innerList.clear();
                questionId = firstQuestionId;
            }

        }
    }

    public static void startWithSecondAnswer() {
        while (iterator2.hasNext()) {
            if (questionId.equals("")) {
                List<String> copyOfInnerList = new ArrayList<>(innerList);
                resultList.add(copyOfInnerList);
                innerList.clear();
                break;

            }

            JSONObject jsonObject = (JSONObject) iterator2.next();

            if (!jsonObject.containsKey(questionId)) {
                counter2++;
                continue;
            }

            Parser answered = Parser.parseQuestion(jsonObject, questionId);
            innerList.add(answered.toStringSecond());
            questionId = Parser.getSecondAnswerNext();
            counter2++;

            if (counter2 == inquirerArray.size()) {
                List<String> copyOfInnerList = new ArrayList<>(innerList);
                resultList.add(copyOfInnerList);
                innerList.clear();
            }
        }
    }
}
