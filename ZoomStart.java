import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class ZoomStart{
    public static void main(String[] args) throws ParseException {
        scheduleDate();
    }

    public static void scheduleDate() throws ParseException {
        Timer timer = new Timer(false);
        LocalTime lTime = LocalTime.now();
        String sTime = lTime.toString();
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String scheduledDate;
        String cmdFileDir = "{cmd file pass}"; // (1) Put your cmd file pass here.
        String scheduledTime = " 08:35:00"; // (2) Set time you would like to enter the Zoom meeting.(Format: "_HH:mm:ss")

        TimerTask task = new TimerTask(){
            public void run(){
                try {
                    ProcessBuilder pb = new ProcessBuilder();
                    pb.command(); 
                    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                    pb.redirectError(ProcessBuilder.Redirect.INHERIT);

                    Process process = pb.start();
                    timer.cancel();
                    } catch(IOException e) {
                        System.err.println(e.getMessage());
                }
            }
        };

        cal.setTime(nowDate);
        if(sTime.compareTo("08:40:00") == 1) cal.add(Calendar.DAY_OF_MONTH, 1);
        scheduledDate = sdf1.format(cal.getTime()) + scheduledTime;

        System.out.println("Enter your zoom meeting on " + scheduledDate + ".");
        timer.schedule(task, sdf2.parse(scheduledDate));
    }
}