import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	
	Queue<Integer> directionQueue = new LinkedList<Integer>();
	
	public CarQueue() {
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			directionQueue.add(rand.nextInt(4));
		}
	}

	public void addToQueue() {
		
		class runner implements Runnable {
			
			public void run() {
				try {
					Random rand = new Random();
					while (true) {
						int randomInteger = rand.nextInt(4);
						directionQueue.add(randomInteger);
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		
		}
		
		Runnable r = new runner();
		Thread t = new Thread(r);
		t.start();
	}

	public int deleteQueue() {
		return directionQueue.remove();
	}

}
