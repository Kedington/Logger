import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to Decode the encoded logs
 */
public class Decoder {

    private static String encodedfile = "default.txt";

    private final String classNameKey = "ClassNameKey.txt";
    private final String logContentKey = "LogContentKey.txt";

    private File classKey = new File(classNameKey);
    private File logKey = new File(logContentKey);

    // Used to Keep track of the current class names and log contents
    private Map<String, String> classNamesMap = new HashMap<String, String>(256);
    private Map<String, String> logContentsMap = new HashMap<String, String>(256);

    /**
     * Checks to see if there already exists a key files in which case it populates
     * the Dictionaries.
     */
    public Decoder() {
        if (readIntoMap(classKey, classNamesMap) && readIntoMap(logKey, logContentsMap)) {

        } else {

        }
    }


    /**
     * Read the lines of a file adding in a mapping from String to String.
     *
     * @param file      Name of the file reading from.
     * @param mapObject Map Object we are populating.
     * @return true if successfully read from file.
     * false if unable to read from file or file does not exist.
     */
    private boolean readIntoMap(File file, Map<String, String> mapObject) {
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split("-");
                    mapObject.put(parts[0], parts[1]);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * Used to decode the encoded logs
     */
    public void decodeLogs() {
        boolean flag = true;
        try {
            FileInputStream in = new FileInputStream(encodedfile);
            int bytes;
            while ((bytes = in.read()) != -1) {
                System.out.println(bytes);
                if (flag) {
                    if (bytes > 127)
                        System.err.println(classNamesMap.get(Byte.toString((byte) (bytes - 254))));
                    else
                        System.out.println(classNamesMap.get(Byte.toString((byte) bytes)));
                }
                else
                    System.out.println(logContentsMap.get(Byte.toString((byte) bytes )));

                flag = !flag;

            }

        } catch (IOException e) {

        }
    }
}
