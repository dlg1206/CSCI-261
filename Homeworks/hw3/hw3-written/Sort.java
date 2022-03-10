import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Derek Garcia
 **/

public class Sort {

    private static ArrayList<Job> parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        ArrayList<Job> jobs = new ArrayList<>();

        int jobID = 0; // nodes start at 1
        String line = br.readLine();    // get first node info
        // Read entire file
        do {

            int pTime = Integer.parseInt(line);

            jobs.add(new Job(jobID++, pTime));

            line = br.readLine();

        } while (line != null);

        br.close();
        return jobs;
    }

    private static ArrayList<Job> sort(ArrayList<Job> jobs){
        // bubble sort
        for(int i = 0; i < jobs.size() - 1; i++){
            if(jobs.get(i).getpTime() > jobs.get(i + 1).getpTime()){
                swap(jobs, i, i + 1);
            }
        }

        return jobs;

    }

    private static ArrayList<Job> process(ArrayList<Job> jobs){
        int fTime = 0;

        for(Job job : jobs){
            fTime += job.getpTime();
            job.setfTime(fTime);
        }

        return jobs;
    }

    private static ArrayList<Job> swap(ArrayList<Job> jobs, int a, int b){
        Job ja = jobs.get(a);
        Job jb = jobs.get(b);
        jobs.set(a, jb);
        jobs.set(b, ja);

        return jobs;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Job> jobs = parseFile(args[0]);
        System.out.println("Input");
        for(Job job : jobs){
            System.out.println(job);
        }
        System.out.println("\nSorted");
        process(sort(jobs));
        double sum = 0;
        for (Job job : jobs){
            sum += job.getfTime();
            System.out.println(job);
        }
        double avg = sum / jobs.size();
        System.out.println("Avg time: " + avg);


    }
}
