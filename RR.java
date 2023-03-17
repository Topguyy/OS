package OS;

import java.util.*;

public class RR {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 0;
        int TheQueue = 0;
        int timer = 0;
        int maxProccessIndex = 0;
        float averageWait = 0, avgTT = 0;
        System.out.println("Please Enter q!!!!");
        TheQueue = sc.nextInt();
        System.out.println("Please Enter Number Of Processes!!!!!");
        n = sc.nextInt();
        int arrivalTime[] = new int[n];
        int burst[] = new int[n];
        int waitingTime[] = new int[n];
        int turn[] = new int[n];
        int queue[] = new int[n];
        int tempBurst[] = new int[n];
        boolean Completed[] = new boolean[n];

        System.out.println("enter arriving time of the processes");
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = sc.nextInt();
        }

        System.out.println("enter burst time of the processes");
        for (int i = 0; i < n; i++) {
            burst[i] = sc.nextInt();
            tempBurst[i] = burst[i];
        }

        for (int i = 0; i < n; i++) {   
            Completed[i] = false;
            queue[i] = 0;
        }
        while (timer < arrivalTime[0]) 
        {
            timer++;
        }
        queue[0] = 1;

        while (true) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (tempBurst[i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }

            for (int i = 0; (i < n) && (queue[i] != 0); i++) {
                int ctr = 0;
                while ((ctr < TheQueue) && (tempBurst[queue[0] - 1] > 0)) {
                    tempBurst[queue[0] - 1] -= 1;
                    timer += 1;
                    ctr++;

                    checkNewArrival(timer, arrivalTime, n, maxProccessIndex, queue);
                }
                if ((tempBurst[queue[0] - 1] == 0) && (Completed[queue[0] - 1] == false)) {
                    turn[queue[0] - 1] = timer;        
                    Completed[queue[0] - 1] = true;
                }

               
                boolean idle = true;
                if (queue[n - 1] == 0) {
                    for (int k = 0; k < n && queue[k] != 0; k++) {
                        if (Completed[queue[k] - 1] == false) {
                            idle = false;
                        }
                    }
                } else {
                    idle = false;
                }

                if (idle) {
                    timer++;
                    checkNewArrival(timer, arrivalTime, n, maxProccessIndex, queue);
                }

    
                queueMaintaining(queue, n);
            }
        }

        for (int i = 0; i < n; i++) {
            turn[i] = turn[i] - arrivalTime[i];
            waitingTime[i] = turn[i] - burst[i];
        }

        System.out.print("\nProgram Number\tArrival Time\tBurst Time\tWait Time\tTurnAround Time"
                + "\n");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t" + arrivalTime[i] + "\t\t" + burst[i]
                    + "\t\t" + waitingTime[i] + "\t\t" + turn[i] + "\n");
        }
        for (int i = 0; i < n; i++) {
            averageWait += waitingTime[i];
            avgTT += turn[i];
        }
        System.out.println("average waitingTime time : " + (averageWait / n) + "\naverage Turn Around Time : " + (avgTT / n));
    }

    public static void queueUpdation(int queue[], int timer, int arrivalTime[], int n, int maxProccessIndex) {
        int zeroIndex = -1;
        for (int i = 0; i < n; i++) {
            if (queue[i] == 0) {
                zeroIndex = i;
                break;
            }
        }
        if (zeroIndex == -1) {
            return;
        }
        queue[zeroIndex] = maxProccessIndex + 1;
    }

    public static void checkNewArrival(int timer, int arrivalTime[], int n, int maxProccessIndex, int queue[]) {
        if (timer <= arrivalTime[n - 1]) {
            boolean newArrival = false;
            for (int j = (maxProccessIndex + 1); j < n; j++) {
                if (arrivalTime[j] <= timer) {
                    if (maxProccessIndex < j) {
                        maxProccessIndex = j;
                        newArrival = true;
                    }
                }
            }
            if (newArrival) //adds the index of the arriving process(if any)
            {
                queueUpdation(queue, timer, arrivalTime, n, maxProccessIndex);
            }
        }
    }

    public static void queueMaintaining(int queue[], int n) {

        for (int i = 0; (i < n - 1) && (queue[i + 1] != 0); i++) {
            int temp = queue[i];
            queue[i] = queue[i + 1];
            queue[i + 1] = temp;
        }
    }
}
