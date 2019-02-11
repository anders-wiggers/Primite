package a.asd.shooterclicker.patterns;

import java.text.DecimalFormat;
import java.util.Random;

public class Generator {
    public Generator() {
    }

    public static int generateRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static double format(double longToFormat){
        DecimalFormat decimalFormat = new DecimalFormat("#####.##");
        //double twoDigitsFormat = Float.valueOf( decimalFormat.format(longToFormat));
        return longToFormat;
    }
}
