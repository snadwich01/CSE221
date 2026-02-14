import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        int T = data.nextInt();

        for (int t = 0; t < T; t++) {
            int N = data.nextInt();

            int[] id = new int[N];
            int[] mark = new int[N];

            for (int i = 0; i < N; i++) {
                id[i] = data.nextInt();
            }
            for (int i = 0; i < N; i++) {
                mark[i] = data.nextInt();
            }

            Student[] students = new Student[N];
            for (int i = 0; i < N; i++) {
                students[i] = new Student(id[i], mark[i], i);
            }

            Student[] ranked = new Student[N];
            boolean[] used = new boolean[N];

            for (int i = 0; i < N; i++) {
                int select = -1;

                for (int j = 0; j < N; j++) {
                    if (!used[j]) {
                        if (select == -1) {
                            select = j;
                        } else if (students[j].mark > students[select].mark) {
                            select = j;
                        } else if (students[j].mark == students[select].mark
                                && students[j].id < students[select].id) {
                            select = j;
                        }
                    }
                }

                ranked[i] = students[select];
                used[select] = true;
            }

            int[] targetPos = new int[N];
            for (int i = 0; i < N; i++) {
                targetPos[ranked[i].pos] = i;
            }

            boolean[] visited = new boolean[N];
            int swaps = 0;

            for (int i = 0; i < N; i++) {
                if (!visited[i] && targetPos[i] != i) {
                    int cnt = 0;
                    int cur = i;

                    while (!visited[cur]) {
                        visited[cur] = true;
                        cur = targetPos[cur];
                        cnt++;
                    }

                    if (cnt > 1) {
                        swaps += cnt - 1;
                    }
                } else {
                    visited[i] = true;
                }
            }

            System.out.println("Minimum swaps: " + swaps);
            for (int i = 0; i < N; i++) {
                System.out.println("ID: " + ranked[i].id + " Mark: " + ranked[i].mark);
            }
        }

        data.close();
    }

    //student constructor
    static class Student {
        int id;
        int mark;
        int pos;

        Student(int id, int mark, int pos) {
            this.id = id;
            this.mark = mark;
            this.pos = pos;
        }
    }
}
