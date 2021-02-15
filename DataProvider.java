import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvider {
    private BufferedReader br;
    private int stringsNumber;
    private String[] inputStrings;

    public DataProvider(String fileName) throws FileNotFoundException, IOException {
        this.br = new BufferedReader(new FileReader(fileName));
        this.stringsNumber = Integer.parseInt(br.readLine());
        this. inputStrings = new String[stringsNumber];

        for ( int i = 0; i < stringsNumber && (inputStrings[i] = br.readLine()) != null; i++ );
        br.close();
    }

    public String[] getInputStrings() {
        return this.inputStrings;
    }
}
