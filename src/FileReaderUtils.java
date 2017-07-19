import java.io.*;

/**
 * Created by David et Monireh on 19/07/2017.
 */
public class FileReaderUtils {

    public static String[] readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString().split("\n");
    }

    public static String[] readLine(String fileName, String separator) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String line = br.readLine();
                sb.append(line);
            } finally {
                br.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString().split(separator);
    }

    public static void writeToFile(StringBuilder stringBuilder, String path) {
        File file = new File(path);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(stringBuilder.toString());
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLine(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}

