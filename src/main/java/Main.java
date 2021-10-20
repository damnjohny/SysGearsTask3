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

    private static final List<String> innerList = new ArrayList<>();
    private static final List<List<String>> resultList = new ArrayList<>();

    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader(input);
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(fileReader);

            JSONArray inquirerArray = (JSONArray) object.get("inquirer");

            String firstQuestionId = "q1";
            String questionId = firstQuestionId;

            var iterator = inquirerArray.iterator();

            int counter = 0;

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

            var iterator2 = inquirerArray.iterator();

            int counter2 = 0;

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

            for (List next : resultList) {
                System.out.println(next);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
