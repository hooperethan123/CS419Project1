

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\jayfe\\Downloads\\Project 1\\Hand Out\\CS419Project1\\src\\schedule1.txt";

		Scheduler s; 
		s = new FCFS();
//		s = new SRTF();
//		s = new SJF();
//		s = new RR(5);
        Simulation.run(file, s);
	}                                       
	
}
