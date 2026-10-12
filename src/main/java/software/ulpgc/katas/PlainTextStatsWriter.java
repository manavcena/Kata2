package software.ulpgc.katas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class PlainTextStatsWriter implements StatsWriter {

    @Override
    public void write(Map<String, Long> stats, OutputStream os) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        writer.write("--- RESUMEN DE JUEGOS POR GÉNERO ---");
        writer.newLine();

        for (Map.Entry<String, Long> entry : stats.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + " juegos");
            writer.newLine();
        }

        writer.newLine();
        writer.flush();
    }
}
