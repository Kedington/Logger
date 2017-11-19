import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public static void writeToTextFile(char tag, String content) {
        try {

            String msg = Long.toString(System.currentTimeMillis()) + " " + tag + " " + content + '\n';
            FileOutputStream out = new FileOutputStream(fileName, true);
            out.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
