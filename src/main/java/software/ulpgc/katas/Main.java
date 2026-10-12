package software.ulpgc.katas;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        VideoGameLoader loader = new CsvVideoGameLoader();
        StatsWriter writer = new PlainTextStatsWriter();

        System.out.println("=== REPETICIÓN 2 (Parte 1): Archivo local -> Consola ===");
        try (InputStream is = new FileInputStream("src/main/resources/videogames.csv")) {
            List<VideoGame> games = loader.load(is);
            Map<String, Long> stats = VideoGameStatistics.countByGenre(games);
            writer.write(stats, System.out);
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }

        System.out.println("=== REPETICIÓN 2 (Parte 2): Memoria -> Fichero resumen.txt ===");
        String csvEnMemoria = """
                id,title,genre,platform,release_year,rating
                101,Hades II,Roguelike,PC,2024,9.5
                102,Silksong,Metroidvania,PC,2025,9.8
                103,Dead Cells,Roguelike,PC,2018,8.9
                """;

        File outputFile = new File("resumen.txt");
        try (InputStream memoryInput = new ByteArrayInputStream(csvEnMemoria.getBytes());
             OutputStream fileOutput = new FileOutputStream(outputFile)) {

            List<VideoGame> games = loader.load(memoryInput);
            Map<String, Long> stats = VideoGameStatistics.countByGenre(games);
            writer.write(stats, fileOutput);

            System.out.println("Fichero '" + outputFile.getName() + "' creado correctamente con el resumen.\n");

        } catch (IOException e) {
            System.err.println("Error procesando memoria/fichero: " + e.getMessage());
        }
    }
}