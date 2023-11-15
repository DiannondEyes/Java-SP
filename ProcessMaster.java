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
        ExplorerRunner();
        PIDLister();
        PriorityGetter();
        FirstStartedProcessName();
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
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
            Runtime.getRuntime().exec("tasklist.exe /fo csv /nh").getInputStream()))) {
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
        try {
            Process process = Runtime.getRuntime().exec("powershell -command \"" +
            "Get-WmiObject -Query 'SELECT Name, Priority FROM Win32_Process' | ForEach-Object { " +
                    "[PSCustomObject] @{ 'Process Name' = $_.Name; 'Priority' = " +
                        "switch ($_.Priority) {" +
                            "0 {'System priority (0)'} " +
                            "4 {'Idle (4)'} " +
                            "6 {'Below normal (6)'} " +
                            "8 {'Normal (8)'} " +
                            "9 {'Normal (9)'} " +
                            "10 {'Above normal (10)'} " +
                            "11 {'Normal (11)'}; " + // Да, как ни странно
                            "13 {'High (13)'}; " +
                            "24 {'Real time (24)'} " +
                            "default {$_.Priority.ToString()}" +
                        "}" +
                    "}" +
                "} | Sort-Object 'Process Name' | Format-Table -AutoSize\"");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void FirstStartedProcessName() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            Runtime.getRuntime().exec("tasklist.exe /fo csv /nh").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    System.out.println("Самый первый процесс: " + parts[0].replace('"', ' ').trim());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}