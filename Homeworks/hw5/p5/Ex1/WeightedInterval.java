package Ex1;	// TODO REMOVE THIS

import java.util.*;
import java.io.*;

public class WeightedInterval {

    public static int [] M;
    
    public static void main(String [] args) throws FileNotFoundException {
        // read input file
	Scanner sc = new Scanner(new File(args[0]));
	int n = Integer.parseInt(sc.nextLine());
	// Jobs are indexed 1 .. n
	Job [] jobs = new Job[n+1];
	// Job 0 is a fake job
	jobs[0] = null;
	for (int i = 1; i <= n; i++) {
	    String line = sc.nextLine();
	    String [] parts = line.split(" ");
	    int job = Integer.parseInt(parts[0]);
	    int start = Integer.parseInt(parts[1]);
	    int finish = Integer.parseInt(parts[2]);
	    int weight = Integer.parseInt(parts[3]);
	    jobs[i] = new Job(job, start, finish, weight);
	}

	// sort the jobs by finish time
	Arrays.sort(jobs,1,jobs.length);

	// For each job j, get the index of the first job to the left
	// of Job j that is compatible with job j
	int [] p = prior(jobs);

	// Find max sum of weights of compatible jobs recursively.
	System.out.println("Find max weighted interval recursive ");
	long start = System.currentTimeMillis();
	int opt = optR(jobs, p, jobs.length-1);
	long stop = System.currentTimeMillis();
	System.out.println("Max weight sum = " + opt);
	System.out.println("Time = " + (stop-start));

	// Find max sum of weights of compatible jobs using memoization	
	System.out.println("\nFind max weighted interval iteratively/memoized");
	start =  System.currentTimeMillis();
	opt = optMem(jobs, p);
	stop =   System.currentTimeMillis();
	System.out.println("Max weight sum = " + opt);
	System.out.println("Time = " + (stop-start));

	// Find max sum of weights of compatible jobs using memoization		
	System.out.println("\nSolution: " );
	showSolution(jobs, p);

    }


    /**
     * @ param jobs a list of jobs sorted by finish time
     * @ return int [] p, p[j] is the first job to the left of jobs[j]
     *                    that is compatible with jobs[j]
     */
    public static int [] prior(Job[] jobs) {
		// todo comment this
		ArrayList<Job> earliestStart = new ArrayList<>();

		int startTime = 0;
		while(earliestStart.size() != jobs.length - 1){
			for(Job job : jobs){
				if(job != null && !earliestStart.contains(job) && job.start <= startTime)
					earliestStart.add(job);
			}
			startTime++;
		}
		int[] compatible = new int[jobs.length];

		for(int i = 1; i < jobs.length; i++){

			for(Job other : earliestStart){
				if(other.start >= jobs[i].finish)
					compatible[other.number] = jobs[i].number;
			}
		}
//
//		int previous = 0;
//		while (!earliestStart.isEmpty()){
//			Job curJob = earliestStart.remove(0);
//
//			if(curJob.start >= jobs[previous + 1].finish){
//				compatible[curJob.number] = jobs[previous++ + 1].number;
//
//			} else if(previous != 0){
//				compatible[curJob.number] = jobs[previous + 1].number;
//			}
//		}


		return compatible;
    }

    
    /**
     *
     * param jobs - sorted by ascending finish time
     * param p - array of indices into jobs, jobs[p[j]] is first job
     *           to left of Job j compatible with j
     *       j - the index of job currently under consideration to
     *           be part of the optimal solution
     * return max sum of weights of compatible jobs
     */
    public static int optR(Job[] jobs, int[] p, int j) {
	// todo comment
		if(j == 0){
			return 0;
		}

		int foo = p[j];
		int inclusive = jobs[j].weight + optR(jobs, p, p[j]);
		int exclusive = optR(jobs, p, j - 1);

		return Math.max(inclusive, exclusive);

    }

    /**
     *
     * param jobs - sorted by ascending finish time
     * param p - array of indices into jobs, jobs[p[j]] is first job
     *           to left of Job j compatible with j
     * return max sum of weights of compatible jobs
     */    
    public static int optMem(Job[] jobs, int[] p) {
	// todo FINISH ME
		return 0;
    }


    // go through array M to find and list of jobs that are part of the
    // maximum value solution
    public static void showSolution(Job [] jobs, int [] p) {
	showSolution(jobs, p, jobs.length-1);
    }

    public static void showSolution(Job[] jobs, int [] p, int j) {

		// todo finish
		return;
    }    

}
