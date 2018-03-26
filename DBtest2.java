import java.util.Scanner;

public class DBtest2 {
	public static void main(String args[]) {
		new DBtest2();
	}
	int i;
	public DBtest2() {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		final int N = reader.nextInt();
		final int M = (int) Math.ceil(Math.log(N) / Math.log(2.0));
		
		final DB2 b = new DB2(N, M);
				
		for (i = 0; i < N; i++) { 
						
			SimpleThread thread = new SimpleThread(i, b);
			thread.start();
			
		}
		
	}
	
	class SimpleThread extends Thread {
		
		public int id;
		public DB2 barrier;
		
		public SimpleThread(int id, DB2 barrier) {
			//System.out.println("Thread " + i + " started!");
			this.id = id;
			this.barrier = barrier;
		}
		@Override
		public void run() {
			// when thread starts
			try {
				//barrier.init();
				int i;
				for (i = 0; i < 3; i++) {
					System.out.println("Thread " + id + " in loop\t" + i);		
					barrier.await(this.id);		
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
	
}
