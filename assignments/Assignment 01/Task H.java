import java.util.Scanner;

public class Main {
    static class tinfo {
        String original;
        String name;
        String time;

        public tinfo(String original, String name, String time) {
            this.original = original;
            this.name = name;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        if (data.hasNextInt()) {
            int n = data.nextInt();
            data.nextLine();

            tinfo[] schedule = new tinfo[n];

            int[] weight = new int[256];
            String order = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < order.length(); i++) {
                weight[order.charAt(i)] = i;
            }

            for (int i = 0; i < n; i++) {
                String line = data.nextLine();
                String[] parts = line.split(" ");
                
                String trainName = parts[0];
                String departTime = parts[parts.length - 1];

                schedule[i] = new tinfo(line, trainName, departTime);
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    boolean swapNeeded = false;

                    int nDiff = checkName(schedule[j].name, schedule[j + 1].name, weight);
                    
                    if (nDiff > 0) {
                        swapNeeded = true;
                    } else if (nDiff == 0) {
                        int tDiff = checkTime(schedule[j].time, schedule[j + 1].time);
                        if (tDiff < 0) {
                            swapNeeded = true;
                        }
                    }

                    if (swapNeeded) {
                        tinfo temp = schedule[j];
                        schedule[j] = schedule[j + 1];
                        schedule[j + 1] = temp;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println(schedule[i].original);
            }
        }
    }

    public static int checkName(String s1, String s2, int[] weight) {
        int len1 = s1.length();
        int len2 = s2.length();
        int limit;
        
        if (len1 < len2) {
            limit = len1;
        } else {
            limit = len2;
        }

        for (int k = 0; k < limit; k++) {
            char c1 = s1.charAt(k);
            char c2 = s2.charAt(k);
            if (c1 != c2) {
                return weight[c1] - weight[c2];
            }
        }
        return len1 - len2;
    }

    public static int checkTime(String t1, String t2) {
        for (int k = 0; k < 5; k++) {
            char c1 = t1.charAt(k);
            char c2 = t2.charAt(k);
            if (c1 != c2) {
                return c1 - c2;
            }
        }
        return 0;
    }
}
