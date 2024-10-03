import java.util.ArrayList;
import java.util.List;

public class SJF implements Scheduler {

	private List<SimProcess> queue;
	private List<Integer> waitingTimes;

	public SJF() {
		queue = new ArrayList<SimProcess>();
		waitingTimes = new ArrayList<Integer>();
	}

	@Override
	public void onProcessArrival(SimProcess p, int time) {queue.add(p);}

	@Override
	public void onProcessExit(SimProcess p, int time) {
		SimProcess smallestBurst = queue.get(0);

		for (int i = 1; i < queue.size(); ++i) {
			SimProcess newBurst = queue.get(i);

			int smallestNumber = smallestBurst.getBurstTime();
			int newNumber = newBurst.getBurstTime();

			if (newNumber < smallestNumber) {
				smallestBurst = newBurst;
			}
		}

		queue.remove(smallestBurst);

		int waitingTime = time - p.getTimeOfArrival() - p.getBurstTime();
		System.out.println(p.getId() + " finished at time " + time + ". Its waiting time is " + waitingTime);
		waitingTimes.add(waitingTime);
		System.out.println("Current average waiting time: " + calculateAvgWaiting());
	}

	private double calculateAvgWaiting() {
		double totalWaiting = 0;
		for (int waitingTime: waitingTimes) {
			totalWaiting += waitingTime;
		}
		return totalWaiting / waitingTimes.size();
	}

	@Override
	public void onClockInterrupt(int timeElapsed, int time) {
		System.out.println("Clock interrupt! Current time: " + time + " Time Elapsed: " +timeElapsed );
	}

	@Override
	public String getAlgorithmName() {return "SJF";}

	@Override
	public SimProcess currentProcess() {
		if (queue.size() == 0) {
			return null;
		}

		//need to update this to only run when called
		SimProcess smallestBurst = queue.get(0);

		for (int i = 1; i < queue.size(); ++i) {
			SimProcess newBurst = queue.get(i);

			int smallestNumber = smallestBurst.getBurstTime();
			int newNumber = newBurst.getBurstTime();

			if (newNumber < smallestNumber) {
				smallestBurst = newBurst;
			}
		}

		return smallestBurst;
	}

	@Override
	public boolean isEmpty() {return queue.isEmpty(); }
}
