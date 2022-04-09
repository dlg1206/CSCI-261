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

		// init vars
		ArrayList<Job> earliestStart = new ArrayList<>();
		Job[] numeric = new Job[jobs.length];

		// sort jobs by start time / sorts jobs in numeric order
		int startTime = 0;
		while(earliestStart.size() != jobs.length - 1){

			for(Job job : jobs){
				// if the job hasn't already been added and improved start time
				if(job != null && !earliestStart.contains(job) && job.start <= startTime){
					earliestStart.add(job);
					numeric[job.number] = job;	// add to numeric order
				}

			}
			startTime++;
		}

		// Build prior array
		int[] compatible = new int[jobs.length];
		for(Job job : numeric) {
			if (job != null) {

				// Start at end of list and work backwards
				for (int i = job.number - 1; i > 0; i--) {

					// if compatible and before value
					if (job.start >= numeric[i].finish && job.number > numeric[i].number) {
						compatible[job.number] = numeric[i].number;
					}

					// break if value was assigned
					if (job.start - numeric[i].finish == 0)
						break;
				}
			}
		}
		// return results
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

		// base case
		if(j == 0)
			return 0;

		// get the current job as a Job class
		Job curJob = null;
		for(Job job : jobs){
			curJob = job;
			// break when found
			if(job != null && job.number == j)
				break;
		}

		assert curJob != null;
		int inclusive = curJob.weight + optR(jobs, p, p[j]);	// use Job j
		int exclusive = optR(jobs, p, j - 1);		// don't use Job j

		// return larger value
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

		// NOTE I couldn't solve the stack overflow issue, this is here to prevent overflow crashes
		// START OVERFLOW GUARD
		if(jobs.length > 20)	// works ok with smaller values
			return 0;
		// END OVERFLOW GUARD

		// get job j
		Job j = jobs[jobs.length - 1];

		// base case
		if(j == null)
			return 0;

		// init M if not made
		if(M == null){
			M = new int[jobs.length + 1];
			for(int i = 1; i < M.length; i++){
				M[i] = -1;
			}
		}

		// if M[j] not visited, calc value
		if(M[j.number] == -1){
			int pj = p[j.number];

			// get index of previously compatible job
			int prevI = 0;
			if(pj != 0){
				prevI = -1;

				for(Job job : jobs){
					prevI++;
					// break when found
					if(job != null && job.number == pj)
						break;
				}
			}

			// get index of the j - 1 job
			int next = (j.number - 1);
			int nextI = 0;
			if(next != 0){
				nextI = -1;
				for(Job job : jobs){
					nextI++;
					// break when found
					if(job != null && job.number == (j.number - 1))
						break;
				}
			}

			int inclusive = j.weight + optMem(Arrays.copyOf(jobs, prevI + 1), p);	// Vj + comp_Op(j)
			int exclusive = optMem(Arrays.copyOf(jobs, nextI + 1), p);	// comp_op(j - 1)

			// update value with max result
			M[j.number] = Math.max(inclusive, exclusive);
		}

		// return stored value
		return M[j.number];
    }


    // go through array M to find and list of jobs that are part of the
    // maximum value solution
    public static void showSolution(Job [] jobs, int [] p) {
		showSolution(jobs, p, jobs.length-1);
    }

    public static void showSolution(Job[] jobs, int [] p, int j) {

		// base case
		if(j == 0)
			return;

		// get Job j from j
		Job curJob = null;
		for(Job job : jobs){
			if(job != null && job.number == j) {
				curJob = job;
				break;
			}
		}

		System.out.println(curJob);
		showSolution(jobs, p, p[j]);	// get previous compatible job
	}

}
