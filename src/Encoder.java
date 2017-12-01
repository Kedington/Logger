import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Used by the Logger.
 * Encodes messages and writes to the decoder file.
 */
public class Encoder {

    private final String classNameKey = "ClassNameKey.txt";
    private final String logContentKey = "LogContentKey.txt";

    private File classKey = new File(classNameKey);
    private File logKey = new File(logContentKey);

    private BufferedWriter classWriter;
    private BufferedWriter logWriter;

    // Used to Keep track of the current class names and log contents
    private Map<String, String> classNamesMap = new HashMap<String, String>(256);
    private Map<String, String> logContentsMap = new HashMap<String, String>(256);

    private long startTime = 0;


    /**
     * Checks to see if there already exists a decoder file in which case it populates
     * the Dictionaries.
     * <p>
     * If the file does not exists it initiates it with the current time.
     * Todo Implement time
     */
    public Encoder() {
        if (readIntoMap(classKey, classNamesMap) && readIntoMap(logKey, logContentsMap)) {

        } else {

        }
        try {
            classWriter = new BufferedWriter(new FileWriter(classKey));
            logWriter = new BufferedWriter(new FileWriter(logKey));
        } catch (IOException e) {
            e.printStackTrace();
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
                    mapObject.put(parts[1], parts[0]);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * If a Class name of log message is not yet in the decoder files adds them and adds them to the mappings.
     * Writes the Log message to the encoded file.
     * @param className         Name of the class called from
     * @param content           Contents of the log message
     * @return                  true if successfully written
     * @throws IOException
     */
    public boolean addMessage(String className, String content) throws IOException {
        if (!classNamesMap.containsKey(className)) {
            classWriter.write(Integer.toString(classNamesMap.size()) + "-" + className + "\n");
            classNamesMap.put(className, Integer.toString(classNamesMap.size()));
        }

        if (!logContentsMap.containsKey(content)) {
            logWriter.write(Integer.toString(logContentsMap.size()) + "-" + content + "\n");
            logContentsMap.put(className, Integer.toString(logContentsMap.size()));
        }

        System.out.println(" " + classNamesMap.size());
        System.out.println(" " + logContentsMap.size());
        classWriter.flush();
        logWriter.flush();
        return true;
    }
}
