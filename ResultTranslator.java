import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultTranslator {
    private StringBuffer resultString;
    private ArrayList<Question> questions;
    private BufferedWriter filewriter;

    public ResultTranslator(ArrayList<Question> questions, String fileName) throws IOException {
        this.resultString = new StringBuffer();
        this.questions = questions;
        this.filewriter = new BufferedWriter(new FileWriter(fileName));

        createResultString();
        filewriter.write(resultString.toString());
        filewriter.close();

        System.out.print(resultString);
    }

    private void createResultString() {
        for (Question question : questions) {
            int result = question.getAverageTime();
            if ( result != -1 ) {
                resultString.append(result+"\n");
            } else {
                resultString.append("-\n");
            }
        }
    }
}
