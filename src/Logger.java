import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Logger manages incoming logs from users and writes them to a specified text file
 */
public class Logger {
    private static String fileName = "default.txt";

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Logger.fileName = fileName;
    }

    public static void writeToTextFile(String content) {
        try {
            Files.write(Paths.get(Logger.fileName), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
