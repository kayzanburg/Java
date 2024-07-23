package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class myTimerLoggings {
    private static final Logger logger = LogManager.getLogger(myTimerLoggings.class);

    public static void main(String[] args) {
        logger.debug("Uygulama başlatıldı");
        Timer timer = new Timer();

        // Debug Görevi: Her saniye loglama
        TimerTask debugTask = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(new Date());
                logger.debug(currentTime);
            }
        };

        // Info Görevi: Her dakika loglama
        TimerTask infoTask = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:00");
                String currentTime = sdf.format(new Date());
                logger.info(currentTime);
            }
        };

        // Error Görevi: Her saat loglama
        TimerTask errorTask = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:00:00");
                String currentTime = sdf.format(new Date());
                logger.error(currentTime);
            }
        };

        // Görevleri zamanla
        timer.scheduleAtFixedRate(debugTask, 0, 1000);  // Her saniye
        timer.scheduleAtFixedRate(infoTask, 0, 60000);  // Her dakika
        timer.scheduleAtFixedRate(errorTask, 0, 3600000);  // Her saat
    }
}
