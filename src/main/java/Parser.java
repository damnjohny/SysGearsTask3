import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Parser {

    public static String id;
    public static String question;
    public static String firstAnswer;
    public static String firstAnswerNext;
    public static String secondAnswer;
    public static String secondAnswerNext;

    private Parser(String question, String firstAnswer, String firstAnswerNext,
                   String secondAnswer, String secondAnswerNext, String id) {
    }

    public static Parser parseQuestion(JSONObject json, String next) {
        JSONObject jsonObject = (JSONObject) json.get(next);
        setId(jsonObject.get("id").toString());
        setQuestion(jsonObject.get("question").toString());

        JSONArray jsonArray = (JSONArray) jsonObject.get("answers");
        JSONObject answersObject1 = (JSONObject) jsonArray.get(0);
        JSONObject answersObject2 = (JSONObject) jsonArray.get(1);

        setFirstAnswer(answersObject1.get("answer").toString());
        setSecondAnswer(answersObject2.get("answer").toString());

        setFirstAnswerNext(answersObject1.get("next").toString());
        setSecondAnswerNext(answersObject2.get("next").toString());

        return new Parser(getQuestion(), getFirstAnswer(), getFirstAnswerNext(),
                getSecondAnswer(), getSecondAnswerNext(), getId());
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Parser.id = id;
    }

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        Parser.question = question;
    }

    public static String getFirstAnswer() {
        return firstAnswer;
    }

    public static void setFirstAnswer(String firstAnswer) {
        Parser.firstAnswer = firstAnswer;
    }

    public static String getFirstAnswerNext() {
        return firstAnswerNext;
    }

    public static void setFirstAnswerNext(String firstAnswerNext) {
        Parser.firstAnswerNext = firstAnswerNext;
    }

    public static String getSecondAnswer() {
        return secondAnswer;
    }

    public static void setSecondAnswer(String secondAnswer) {
        Parser.secondAnswer = secondAnswer;
    }

    public static String getSecondAnswerNext() {
        return secondAnswerNext;
    }

    public static void setSecondAnswerNext(String secondAnswerNext) {
        Parser.secondAnswerNext = secondAnswerNext;
    }

    @Override
    public String toString() {
        return "{" + "\"" + question + "\"" + " : " + "\"" + firstAnswer + "\"}";
    }

    public String toStringSecond() {
        return "{" + "\"" + question + "\"" + " : " + "\"" + secondAnswer + "\"}";
    }
}
