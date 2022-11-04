import java.util.function.BiFunction; // bifunctions!!

public class Simulation {
    private int day;
    private int time; // e.g. 0 - 3900; time of 1 = 1 minute;
    private static double environmentMetric = 0; // REMOVE? Original comment: Break this down. This should be measured based on time of day, temperature, air pressure, humidity.
    private static double envTemp, envAirPressure, envHumidity, envDistractionIndex; // eDI should be broken down more -- into things like external sounds, extreme temp effects... this is really the index after taking into account everything

    public <T> BiFunction<Integer, Integer, String> parseTime(T intTime) { // Parse time from int <-> human-readable form (String of XX:XX)

    }

    // Roll environment and day metrics here
    // Keep track of days n' crap
}
