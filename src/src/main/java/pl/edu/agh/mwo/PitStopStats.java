package pl.edu.agh.mwo;

import org.example.model.PitStop;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class PitStopStats {
    public int countStops(List<PitStop> stops) {
        return stops.size();
    }
    public static PitStop longestStop(List<PitStop> stops) {
        return stops.stream()
                .max(Comparator.comparingDouble(PitStop::getPitDuration))
                .orElse(null);
    }
    public static double averageDuration(List<PitStop> stops) {
        OptionalDouble avg = stops.stream()
                .mapToDouble(PitStop::getPitDuration)
                .average();
        return avg.orElse(0.0);
    }
    public static int lastLap(List<PitStop> stops) {
        return stops.stream()
                .mapToInt(PitStop::getLapNumber)
                .main()
                .orElse(-1);
    }
    public static int lastLap(List<PitStop> stops) {
        return stops.stream()
                .mapToInt(PitStop::getLapNumber)
                .max()
                .orElse(-1);
    }
}
