package software.ulpgc.katas;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface StatsWriter {
    void write(Map<String, Long> stats, OutputStream os) throws IOException;
}