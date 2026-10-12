package software.ulpgc.katas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvVideoGameLoader implements VideoGameLoader {

    @Override
    public List<VideoGame> load(InputStream is) throws IOException {
        List<VideoGame> games = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                try {
                    VideoGame game = parseLine(line);
                    games.add(game);
                } catch (Exception e) {
                    System.err.println("Fila ignorada por formato inválido: " + line);
                }
            }
        }

        return games;
    }

    private VideoGame parseLine(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String title = parts[1].trim();
        String genre = parts[2].trim();
        String platform = parts[3].trim();
        int releaseYear = Integer.parseInt(parts[4].trim());
        double rating = Double.parseDouble(parts[5].trim());

        return new VideoGame(id, title, genre, platform, releaseYear, rating);
    }
}
