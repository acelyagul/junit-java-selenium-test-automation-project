package utilities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    public static void saveReferenceNumber(String referenceNumber, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            writer.write("Application Reference Number: " + referenceNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 