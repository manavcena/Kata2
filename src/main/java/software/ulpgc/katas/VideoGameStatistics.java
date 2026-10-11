package software.ulpgc.katas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VideoGameStatistics {
    public static Map<String, Long> countByGenre(List<VideoGame> games) {
        return games.stream()
                .collect(Collectors.groupingBy(
                        VideoGame::genre,
                        Collectors.counting()
                ));
    }
}
