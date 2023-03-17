package OS;
import java.util.*;
 
public class SJF {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println ("enter number of processes");
        int n = sc.nextInt();
       
        int arrivalTime[] = new int[n]; 
        int burstTime[] = new int[n]; 
        int completeTime[] = new int[n]; 
        int aroundTime[] = new int[n]; 
        int waitingTime[] = new int[n];
        int f[] = new int[n];  // to point if a process completed or not 
        int pid[] = new int[n];
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        System.out.println("Please Enter Arriving Time Of All Processes!!!!!");
                for(int i = 0; i < n; i++)
                    arrivalTime[i] = sc.nextInt();

        System.out.println("enter arriving time of the processes");
            for(int i=0;i<n;i++)
            {
                burstTime[i] = sc.nextInt();
                pid[i] = i+1;
                f[i] = 0;
            }
        boolean a = true;
        while(true){
            int c=n, min=999;
            if (tot == n) 
                break;
            for (int i=0; i<n; i++){
              
                if ((arrivalTime[i] <= st) && (f[i] == 0) && (burstTime[i]<min))
                {
                    min=burstTime[i];
                    c=i;
                }
            }
            
            if (c==n)
                st++;
            else{
                completeTime[c]=st+burstTime[c];
                st+=burstTime[c];
                aroundTime[c]=completeTime[c]-arrivalTime[c];
                waitingTime[c]=aroundTime[c]-burstTime[c];
                f[c]=1;
                tot++;
            }
        }
        System.out.println("\npid  arrival brust  complete turn waiting");
            for(int i=0;i<n;i++){
                avgwt+= waitingTime[i];
                avgta+= aroundTime[i];
                System.out.println(pid[i]+"\t"+arrivalTime[i]+"\t"+burstTime[i]+"\t"+completeTime[i]+"\t"+aroundTime[i]+"\t"+waitingTime[i]);
            }
            System.out.println ("\naverage aroundTime is "+ (float)(avgta/n));
            System.out.println ("average waitingTime is "+ (float)(avgwt/n));
            sc.close();
        }
    }