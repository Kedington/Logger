import java.io.IOException;

/**
 * Logger manages incoming logs from users and writes them to a specified text file
 */
public class Logger {

    static Encoder encode = new Encoder();

    /**
     * Write a standard log message to the file
     * @param content
     */
    public static void standard(String content)  {
        try {
            String className = new Exception().getStackTrace()[1].getClassName();
            encode.addMapping(className, content, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write an error message to the log
     * @param content
     */
    public static void error(String content) {
        try {
            String className = new Exception().getStackTrace()[1].getClassName();
            encode.addMapping(className, content, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
