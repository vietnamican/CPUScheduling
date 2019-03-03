package scheduling;

/**
 *
 * @author vietn
 */
public class Scheduling {

    public static String separator() {
        return "----------------------------------------------------------------";
    }

    public static void firstComeFirstServe(Process[] processes) {
        System.out.println(Scheduling.separator());
        System.out.println("First come first serve: \n");
        Algorithm algorithm = new FirstComeFirstServe();
        Scheduling.run(processes, algorithm);
        System.out.println(Scheduling.separator());
    }

    public static void shortestJobFirst(Process[] process) {
        System.out.println(Scheduling.separator());
        System.out.println("Shortest job first");
        Algorithm algorithm = new ShortestJobFirst();
        Scheduling.run(process, algorithm);
        System.out.println(Scheduling.separator());
    }

    public static void shortestRemainingFirst(Process[] process) {
        System.out.println(Scheduling.separator());
        System.out.println("Shortest remaining first");
        Algorithm algorithm = new ShortestRemainingFirst();
        Scheduling.run(process, algorithm);
        System.out.println(Scheduling.separator());
    }

    public static void roundRobin(Process[] processes, int q) {
        System.out.println(Scheduling.separator());
        System.out.println("Round Robin with q = " + q);
        Algorithm algorithm = new RoundRobin(q);
        Scheduling.run(processes, algorithm);
        System.out.println(Scheduling.separator());
    }

    public static void run(Process[] processes, Algorithm algorithm) {
        CPUManagement cpuManagement = new CPUManagement(processes, algorithm);

        //main interite
        cpuManagement.execute();

        //print result
        System.out.println(cpuManagement.toString());
        System.out.println(cpuManagement.getTime());

        Statistic statistic = new Statistic(cpuManagement);
        System.out.println(statistic.toString());
    }

    public static Process[] cloneProcess(Process[] processes) {
        Process[] result = new Process[processes.length];
        for (int i = 0; i < processes.length; i++) {
            result[i] = new Process(processes[i]);
        }
        return result;
    }

    public static void main(String[] args) {

        /*
        You are here
         */
        Process p0 = new Process(68, 0);
        Process p1 = new Process(19, 7);
        Process p2 = new Process(31, 12);
        Process p3 = new Process(42, 15);
        Process[] rawProcesses = new Process[]{p0, p1, p2, p3};
        Process[] processes;

        processes = Scheduling.cloneProcess(rawProcesses);
        Scheduling.firstComeFirstServe(processes);

        processes = Scheduling.cloneProcess(rawProcesses);
        Scheduling.shortestJobFirst(processes);

        processes = Scheduling.cloneProcess(rawProcesses);
        Scheduling.shortestRemainingFirst(processes);

        processes = Scheduling.cloneProcess(rawProcesses);
        Scheduling.roundRobin(processes, 15);
    }
}
