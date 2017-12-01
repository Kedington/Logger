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

    /**
     * Extract the class name of the method that called the logger and the time.
     * Add the Message to the buffer to be written to the file.
     * @param tag       An indicator to represent the severity of the log message
     * @param content   The body of the log message.
     */
    public static void writeToTextFile(char tag, String content) {
        try {
            String className = new Exception().getStackTrace()[1].getClassName();
            String msg = Long.toString(System.currentTimeMillis()) + " " + tag + " " + content + " " + className + '\n';
            FileOutputStream out = new FileOutputStream(fileName, true);
            out.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
