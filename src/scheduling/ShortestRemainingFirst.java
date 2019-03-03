package scheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author vietn
 */
public class ShortestRemainingFirst implements Algorithm {

    class Sorter implements Comparator<Process> {

        private int currentTime;

        public Sorter(int currentTime) {
            this.currentTime = currentTime;
        }

        @Override
        public int compare(Process p1, Process p2) {
            int time1 = p1.getStartTime();
            int time2 = p2.getStartTime();
            if (time1 > currentTime && time2 > currentTime) {
                return 0;
            }
            if (time1 > currentTime && time2 <= currentTime) {
                return 1;
            }
            if (time1 <= currentTime && time2 > currentTime) {
                return -1;
            }
            return p1.getAmount() - p2.getAmount();
        }
    }

    @Override
    public ArrayList<Process> sort(ArrayList<Process> processes, int currentTime) {
        Collections.sort(processes, new Sorter(currentTime));
        return processes;
    }

    @Override
    public int interrupt(ArrayList<Process> processes, int currentTime) {

        //get interrupt in startTime
        int size = processes.size();
        int[] defaultInterrupt = new int[size];
        for (int i = 0; i < size; i++) {
            defaultInterrupt[i] = processes.get(i).getStartTime();
        }
        Arrays.sort(defaultInterrupt);
        for (int i = 0; i < size; i++) {
            int temp = defaultInterrupt[i];
            if (currentTime < temp) {
                return temp;
            }
        }
        return currentTime + processes.get(0).getAmount();
    }

}
