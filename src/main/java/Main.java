import java.util.List;

public class Main {

    public static void main(String[] args) {
        Start.startWithFirstAnswer();
        Start.startWithSecondAnswer();

        for (List next : Start.resultList) {
            System.out.println(next);
        }
    }
}
