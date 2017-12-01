import java.io.IOException;

/**
 * Used to test the Logger
 */
public class test {
    public static void main(String[] args) throws IOException {
        Logger.standard("The User has been added");
        Logger.error("The query timed out");
        Decoder decode = new Decoder();
        decode.decodeLogs();
    }
}
