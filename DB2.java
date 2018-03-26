public class DB2 {
	int[][] answers;
	int[][] intended;
	int mehrzad;
	
	final int M;
	final int N;

	public DB2(int N, int M) {
		this.N = N;
		this.M = M;
		
		answers = new int[N][M];
		intended = new int[N][M];
		init();
	}

	public void init() {
		//System.out.println("INIT");

		int power = 1;
		for (int instance = 0; instance < M; instance++) {
			for (int process = 0; process < N; process++) {
				intended[process][instance] = (power + process) % N;
				answers[process][instance] = 0;
			}
			power = power * 2;
		}
//		printAnswers();
//		printIntended();
	}

	public void printIntended() {
		synchronized (this) {

			for (int j = 0; j < intended.length; j++) {
				for (int k = 0; k < intended[0].length; k++) {
					System.out.print(intended[j][k] + " ");
				}
				System.out.println();
			}

		}
	}

	public void printAnswers() {
		synchronized (this) {

			for (int j = 0; j < answers.length; j++) {
				for (int k = 0; k < answers[0].length; k++) {
					System.out.print(answers[j][k] + " ");
				}
				System.out.println();
			}

		}
	}

	public void await(int myid) throws InterruptedException {
		int instance;
		for (instance = 0; instance < M; instance++) {
//			System.out.println("Thread " + myid + " Round " + instance);
			while (answers[intended[myid][instance]][instance] == 1) {Thread.sleep(1);}
			answers[intended[myid][instance]][instance] = 1;
//			System.out.println("Thread " + myid + " Round " + instance+" answer"+intended[myid][instance]+","+instance+" = "+answers[intended[myid][instance]][instance]);
			while (answers[myid][instance] == 0) {Thread.sleep(1);}
			answers[myid][instance] = 0;
//			System.out.println("Thread " + myid + " Round " + instance+" answer"+myid+","+instance+" = "+answers[myid][instance]);	
		}
	}
}
