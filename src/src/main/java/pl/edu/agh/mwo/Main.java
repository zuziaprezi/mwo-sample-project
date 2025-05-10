package pl.edu.agh.mwo;

// src/main/java/org/example/Main.java


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.PitStop;
import org.example.service.PitStopStats;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Aplikacja konsolowa: wczytuje JSON i wypisuje statystyki pit-stopów.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Użycie: java -jar f1-stats.jar <ścieżka_do_pliku.json>");
            System.exit(1);
        }
        String filePath = args[0];
        try {
            // wczytanie listy z JSON
            Gson gson = new Gson();
            Type listType = new TypeToken<List<PitStop>>() {}.getType();
            List<PitStop> stops = gson.fromJson(new FileReader(filePath), listType);

            // obliczenia
            int count       = PitStopStats.countStops(stops);
            PitStop longest = PitStopStats.longestStop(stops);
            double avg      = PitStopStats.averageDuration(stops);
            int firstLap    = PitStopStats.firstLap(stops);
            int lastLap     = PitStopStats.lastLap(stops);

            // wypisanie
            System.out.println("Liczba pit-stopów: " + count);
            if (longest != null) {
                System.out.printf("Najdłuższy pit-stop: %.1f s (kierowca #%d)%n",
                        longest.getPitDuration(), longest.getDriverNumber());
            }
            System.out.printf("Średni czas pit-stopu: %.1f s%n", avg);
            System.out.println("Pierwszy zjazd na pit: okrążenie " + firstLap);
            System.out.println("Ostatni zjazd na pit: okrążenie " + lastLap);

        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku: " + filePath);
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Błąd podczas przetwarzania: " + e.getMessage());
            System.exit(3);
        }
    }
}
