import java.io.IOException;

/**
 * Used to test the Logger
 */
public class test {
    public static void main(String[] args) throws IOException {
        Logger.setFileName("wow.txt");
        Logger.writeToTextFile("Write Something");
    }
}
