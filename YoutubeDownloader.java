import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class YoutubeDownloader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ссылку на видео YouTube: ");
        String request = in.next();
        in.close();
        BufferedReader r = null;
        String audio_url = null;
        String title = null;
        try {
            ProcessBuilder builder = new ProcessBuilder("yt-dlp", "--get-url", "--get-title", request);
            Process p = builder.start();
            r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            for (int i = 0; i < 3; i++) {
                line = r.readLine();
                if (line == null) break;
                if (i == 0) title = line;
                if (i == 2) audio_url = line;
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Скачиваю музыку: "+title);
        try {
            urlRetrieve(audio_url, title+".weba");
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public static void urlRetrieve(String fileURL, String savePath) throws IOException, URISyntaxException {
        try (InputStream in = new URI(fileURL).toURL().openStream(); FileOutputStream out = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}