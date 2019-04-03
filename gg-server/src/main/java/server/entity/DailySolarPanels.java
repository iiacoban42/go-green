/*package server.entity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class DailySolarPanels extends TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        // Schedule to run every Sunday in midnight
        timer.schedule(new SolarPanels(), date.getTime(), 1000 * 60 * 60 * 24);
    }
}*/