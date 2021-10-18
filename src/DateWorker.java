import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateWorker {

    public static void main(String[] args) throws ParseException {
        DateWorker dateWorker = new DateWorker();
        System.out.println(dateWorker.getDay("1999","12","32"));
        System.out.println(dateWorker.isLeap("2004"));
        System.out.println(dateWorker.getInterval("25.15.1999", "27.11.1999"));
    }

    public String getDay(String year, String month, String day) throws ParseException {
        Date date = new SimpleDateFormat("dd/M/yyyy").parse(String.valueOf(day +
                "/" +
                month +
                "/" +
                year));
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }

    public Boolean isLeap(String year){
        int intYear = new Integer(year);
        return ((intYear % 4 == 0) && (intYear % 100 != 0) || (intYear % 400 == 0));
    }

    public String getInterval(String firstDate, String secondDate) throws ParseException {
        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(firstDate);
        Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse(secondDate);
        long milliseconds = Math.abs(date2.getTime() - date1.getTime());
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        return (String.valueOf(days));
    }
}
