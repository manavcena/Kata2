package software.ulpgc.katas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        VideoGameLoader loader = new CsvVideoGameLoader();
        StatsWriter writer = new PlainTextStatsWriter();

        System.out.println("=== REPETICIÓN 1: Archivo local -> Consola ===");
        try (InputStream is = new FileInputStream("src/main/resources/videogames.csv")) {
            List<VideoGame> games = loader.load(is);
            Map<String, Long> stats = VideoGameStatistics.countByGenre(games);
            writer.write(stats, System.out);
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}