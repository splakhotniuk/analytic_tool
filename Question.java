import java.time.LocalDate;

public class Question {
    private String questType;
    private String responceType;
    private String date;
    private int minutes = 0;
    private int numberOfQuestions = 0;

    public Question(String questType, String responceType, String date) {
        this.questType = questType;
        this.responceType = responceType;
        this.date = date;
    }

    private boolean isQuestTypeMatch(String checkedQuestType) {
        StringBuffer strBuff = new StringBuffer(checkedQuestType);
        if ( this.questType.equals("*") ) {
            return true;
        }

        for ( int startIndex = strBuff.lastIndexOf("."); startIndex != -1; ) {
            if ( strBuff.toString().equals(this.questType) ) {
                return true;
            }
            strBuff.delete(startIndex, strBuff.length());
            startIndex = strBuff.lastIndexOf(".");
        }
        if ( strBuff.toString().equals(this.questType) ) {
            return true;
        }
        return false;
    }

    private boolean isResponceTypeMatch(String checkedResponceType) {
        return checkedResponceType.equals(this.responceType);
    }

    private LocalDate convertToDate(String dateStr) {
        String[] ddMmYy = dateStr.split("[.]");
        return LocalDate.of(Integer.parseInt(ddMmYy[2]), Integer.parseInt(ddMmYy[1]), Integer.parseInt(ddMmYy[0]));
    }
    
    private boolean isDateMatch(String checkedDate) {
        LocalDate chDate = convertToDate(checkedDate);
        String[] datePeriod = this.date.split("[-]");
        LocalDate date1 = convertToDate(datePeriod[0]);

        if ( datePeriod.length == 2 ) {
            LocalDate date2 = convertToDate(datePeriod[1]);
            return (chDate.isEqual(date1) || chDate.isAfter(date1)) && (chDate.isEqual(date2) || chDate.isBefore(date2));
        }
        return chDate.isEqual(date1);
    }
    
    public void minutesAccount (String questType, String responceType, String date, String minutes) {       
        if ( isQuestTypeMatch(questType) && isResponceTypeMatch(responceType) && isDateMatch(date) ) {
            this.minutes += Integer.parseInt(minutes);
            numberOfQuestions += 1;
        }
    }

    public int getAverageTime() {
        if ( numberOfQuestions > 0 ) {
            return this.minutes / this.numberOfQuestions;
        }
        return -1;
    } 
}
