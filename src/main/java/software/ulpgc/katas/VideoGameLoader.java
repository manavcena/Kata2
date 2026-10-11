package software.ulpgc.katas;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public interface VideoGameLoader {
    List<VideoGame> load(InputStream is) throws IOException;
}

