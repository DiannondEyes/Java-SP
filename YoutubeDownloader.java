import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

class DownloadTask implements Runnable {
    private final String fileURL;
    private final String savePath;

    public DownloadTask(String fileURL, String savePath) {
        this.fileURL = fileURL;
        this.savePath = savePath;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URI(this.fileURL).toURL().openConnection();
            httpURLConnection.setRequestMethod("GET");
            try (InputStream in = httpURLConnection.getInputStream(); FileOutputStream out = new FileOutputStream(savePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            System.out.println("Скачивание "+this.savePath+" завершено");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

public class YoutubeDownloader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ссылку на видео YouTube: ");
        String request = in.next();
        BufferedReader r = null;
        String audio_url = null;
        String title = null;
        String thumbnail_url = null;
        try {
            // Использование yt-dlp для получения названия видео и ссылок на обложку, аудиодорожку
            ProcessBuilder builder = new ProcessBuilder("yt-dlp", "--get-url", "--get-title", "--get-thumbnail", request);
            Process p = builder.start();
            r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            for (int i = 0; i < 4; i++) {
                line = r.readLine();
                if (line == null) break;
                if (i == 0) title = line;
                if (i == 2) audio_url = line;
                if (i == 3) thumbnail_url = line;
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Скачиваю музыку: " + title);
        // Параллельное скачивание
        Thread audioThread = new Thread(new DownloadTask(audio_url, title + ".weba"));
        Thread thumbnailThread = new Thread(new DownloadTask(thumbnail_url, title + ".webp"));
        audioThread.start();
        thumbnailThread.start();
        try {
            // Ожидаю только загрузки изображения, аудиофайл будет воспроизводиться при помощи потока (stream)
            thumbnailThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Открытие изображения
            Desktop.getDesktop().open(new File(title + ".webp"));
            // Открытие аудиофайла через ffplay
            Process ffplay = new ProcessBuilder("ffplay", audio_url, "-autoexit", "-nodisp").start();
            while (true) {
                if (in.next().equals("stop")) {
                    System.out.println("Стоп!");
                    in.close();
                    ffplay.destroy();
                    break;
                }
            }
            audioThread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}