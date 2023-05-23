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
        String scheduledTime = "08:30:00"; // (TO DO) Set time you enter the meeting room.(Format: "HH:mm:ss")
        scheduledDate(scheduledTime);
    }

    public static void scheduledDate(String scheduledTime) throws ParseException {
        Timer timer = new Timer(false);
        LocalTime lTime = LocalTime.now();
        String sTime = lTime.toString();
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String scheduledDate;
        String cmdFileDir = "zoom_start.cmd";
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                try {
                    ProcessBuilder pb = new ProcessBuilder();
                    pb.command(cmdFileDir);
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
        if(sTime.compareTo(scheduledTime) >= 0){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        scheduledDate = sdf1.format(cal.getTime()) + " " + scheduledTime;
        System.out.println("Enter your zoom meeting on " + scheduledDate + ".");
        timer.schedule(task, sdf2.parse(scheduledDate));  
    }
}