package scheduling;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author vietn
 */
public class Statistic {

    private double CPUPerformance;
    private double throughPut;
    private int[] turnAroundTime;
    private int[] waitingTime;
    private int[] responseTime;

    public Statistic(CPUManagement cpum) {
        ArrayList<Process> processes = cpum.getReadyQueue().getResults();
        int size = processes.size();
        int totalTime = cpum.getTime();
        this.turnAroundTime = new int[size];
        this.waitingTime = new int[size];
        this.responseTime = new int[size];
        for (int i = 0; i < size; i++) {
            Process temp = processes.get(i);
            this.turnAroundTime[i] = temp.calculateTurnAroundTime();
            this.waitingTime[i] = temp.calculateWaitingTime();
            this.responseTime[i] = temp.calculateResponseTime();
        }
        this.CPUPerformance = 1;
        this.throughPut = (double) size / (double) totalTime;
    }

    public int calculateWaitingTime() {
        int total = 0;
        for (int i = 0; i < this.waitingTime.length; i++) {
            total += this.waitingTime[i];
        }
        return total;
    }

    public double calculateAverageWaitingTime() {
        int total = this.calculateWaitingTime();
        return (double) total / this.waitingTime.length;
    }

    public int calculateTurnAroundTime() {
        int total = 0;
        for (int i = 0; i < this.turnAroundTime.length; i++) {
            total += this.turnAroundTime[i];
        }
        return total;
    }

    public double calculateAverageTurnAroundTime() {
        int total = this.calculateTurnAroundTime();
        return (double) total / this.turnAroundTime.length;
    }

    public int calculateResponseTime() {
        int total = 0;
        for (int i = 0; i < this.responseTime.length; i++) {
            total += this.responseTime[i];
        }
        return total;
    }

    public double calculateAverageResponseTime() {
        int total = this.calculateResponseTime();
        return (double) total / this.responseTime.length;
    }

    @Override
    public String toString() {

        return "performance: 100%\n"
                + "Through put: " + String.valueOf(this.throughPut) + "\n"
                + "Turn Around Time: " + Arrays.toString(this.turnAroundTime) + ":" + this.calculateTurnAroundTime() + " ~" + this.calculateAverageTurnAroundTime() + "\n"
                + "Waiting Time: " + Arrays.toString(this.waitingTime) + ":" + this.calculateWaitingTime() + " ~" + this.calculateAverageWaitingTime() + "\n"
                + "Response Time: " + Arrays.toString(this.responseTime) + ":" + this.calculateResponseTime() + " ~" + this.calculateAverageResponseTime();
    }

}
