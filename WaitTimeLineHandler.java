import java.util.ArrayList;
import java.util.HashMap;

public class WaitTimeLineHandler {
    HashMap<String, ArrayList<Integer>> services;
    ArrayList<Question> questions;

    public WaitTimeLineHandler(HashMap<String, ArrayList<Integer>> services, ArrayList<Question> questions) {
        this.services = services;
        this.questions = questions;
    }

    private void serviceTimeAccount(String serviceId, String questType, String responceType, String date, String minutes) {
        ArrayList<Integer> numbersArr = services.get(serviceId);
        if ( numbersArr != null ) {
            for (Integer num : numbersArr) {
                questions.get(num).minutesAccount(questType, responceType, date, minutes);
            }
        }
    }
    
    public void extractData(String line) {
        String[] parts = line.split(" ");
        StringBuffer strBuff = new StringBuffer(parts[1]);

        for ( int lastPointIndex = strBuff.lastIndexOf("."); lastPointIndex != -1; ) {
            serviceTimeAccount(strBuff.toString(), parts[2], parts[3], parts[4], parts[5]);
            strBuff.delete(lastPointIndex, strBuff.length());
            lastPointIndex = strBuff.lastIndexOf(".");
        }
        serviceTimeAccount(strBuff.toString(), parts[2], parts[3], parts[4], parts[5]);        
        serviceTimeAccount("*", parts[2], parts[3], parts[4], parts[5]);
    }
}
