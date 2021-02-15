
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ToolApp {
        public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] inputStrings = new DataProvider("input.txt").getInputStrings();
        HashMap<String, ArrayList<Integer>> services = new HashMap<>();
        ArrayList<Question> questions = new ArrayList<Question>();
        WaitTimeLineHandler waitTimeLineHandler = new WaitTimeLineHandler(services, questions);
        int currStringNumber = inputStrings.length - 1;
        
        for ( ; currStringNumber >= 0 && inputStrings[currStringNumber].charAt(0) != 'D'; currStringNumber-- );
        
        for ( int resultNumber = 0; currStringNumber >= 0; currStringNumber-- ) {
            if ( inputStrings[currStringNumber].charAt(0) == 'D' ) { 
                String[] parts = inputStrings[currStringNumber].split(" ");

                services.putIfAbsent(parts[1], new ArrayList<Integer>());
                services.get(parts[1]).add(resultNumber);
                questions.add(new Question(parts[2], parts[3], parts[4]));
                resultNumber += 1;
            }

            if ( inputStrings[currStringNumber].charAt(0) == 'C' ) {
                waitTimeLineHandler.extractData(inputStrings[currStringNumber]);
            }
        }

        if ( !questions.isEmpty() ) {
            if ( questions.size() > 1 ) {
                Collections.reverse(questions);
            }
            new ResultTranslator(questions, "output.txt");
        }
    }
}