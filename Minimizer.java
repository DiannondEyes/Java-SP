import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Minimizer {
    public static void main(String[] args) {
        try {
            BufferedReader r = new BufferedReader(new FileReader("YoutubeDownloader.java"));
            StringBuilder optimized = new StringBuilder();  
            String line;
            while ((line = r.readLine()) != null) {
                if (!line.contains("//")) optimized.append(line);
            };
            r.close();
            BufferedWriter w = new BufferedWriter(new FileWriter("MinimizerResult\\YoutubeDownloader.java"));
            w.write(optimized.toString().replaceAll("/\\*(.|\\n)*?\\*/","").replaceAll("\\s+(?=[;{}()=,\\|])|(?<=[;{}()=,\\|])\\s+", "").replaceAll("\\s+", " "));
            w.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}