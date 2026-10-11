package software.ulpgc.katas;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        VideoGameLoader loader = new CsvVideoGameLoader();
        StatsWriter writer = new PlainTextStatsWriter();

        System.out.println("=== CASO 1: Recurso local -> Consola ===");
        try (InputStream is = new FileInputStream("src/main/resources/videogames.csv")) {
            List<VideoGame> games = loader.load(is);
            Map<String, Long> stats = VideoGameStatistics.countByGenre(games);
            writer.write(stats, System.out);
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }

        System.out.println("\n=== CASO 2: Memoria -> Fichero resumen.txt ===");
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
            System.out.println("Fichero generado: " + outputFile.getName());

        } catch (IOException e) {
            System.err.println("Error procesando salida a fichero: " + e.getMessage());
        }

        System.out.println("\n=== CASO 3: Control de error - Fila mal formada ===");
        String csvConFilaCorrupta = """
                id,title,genre,platform,release_year,rating
                1,Zelda,Action-Adventure,Nintendo Switch,2017,9.7
                FILA_INVALIDA_SIN_CAMPOS_NECESARIOS
                2,Mario Odyssey,Platformer,Nintendo Switch,2017,9.5
                """;

        try (InputStream badStream = new ByteArrayInputStream(csvConFilaCorrupta.getBytes())) {
            List<VideoGame> games = loader.load(badStream);
            System.out.println("Juegos cargados correctamente tras omitir la fila rota: " + games.size());
            writer.write(VideoGameStatistics.countByGenre(games), System.out);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== CASO 4: Control de error - Archivo inexistente ===");
        File archivoFantasma = new File("archivo_inexistente.csv");
        try (InputStream is = new FileInputStream(archivoFantasma)) {
            loader.load(is);
        } catch (FileNotFoundException e) {
            System.out.println("Control de error correcto: archivo no encontrado (" + e.getMessage() + ")");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}