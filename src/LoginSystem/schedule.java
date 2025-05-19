package LoginSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * a little helper class that will help calling a method 
 * to increment all the users age duirng the 1 Januarry 12 am
 * 
 */
public class schedule {
    
    private final Runnable _incAge;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public schedule(Runnable _incAge) {
        this._incAge = _incAge;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            if (
                date.getMonthValue() == 1 && 
                date.getDayOfMonth() == 1 && 
                time.getHour() == 0 && 
                time.getMinute() == 0
            ) _incAge.run();
            
        }, 0, 1, TimeUnit.MINUTES);
    }

}
