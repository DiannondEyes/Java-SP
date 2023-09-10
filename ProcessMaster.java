import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProcessMaster {
    // 1) Запускать процесс блокнота 10 раз с интервалом который задается системным таймером
    // 2) Получить отсортированный список PID'ов 
    // 3) Получить список имен процессов и их приоритета
    // 4) Получить процесс, который первый стартовался
    static int counter = 0;

    public static void main(String[] args) {
        // ExplorerRunner();
        PIDLister();
    }

    public static void ExplorerRunner() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    Runtime.getRuntime().exec("explorer.exe");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                counter++;
                if (counter == 10) {
                    timer.cancel();
                }
            }
        }, 0, 3000);
    }

    public static void PIDLister() {
        List<Integer> pidList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("tasklist.exe /fo csv /nh").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    pidList.add(Integer.parseInt(parts[1].replace('"', ' ').trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(pidList);
        for (int pid : pidList) {
            System.out.println(pid);
        }
    }


    public static void PriorityGetter() {
    }

}