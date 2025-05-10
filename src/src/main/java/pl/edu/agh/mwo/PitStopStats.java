package pl.edu.agh.mwo;

import java.util.Comparator;
import java.util.List;

public class PitStopStats {
    public int countStops(List<PitStop> stops) {
        return stops.size();
    }
    public static PitStop longestStop(List<PitStop> stops) {
        return stops.stream()
                .max(Comparator.comparingDouble(PitStop::getPitDuration))
                .orElse(null);
    }
    public static List<PitStop> sortStops(List<PitStop> stops) {}
}
