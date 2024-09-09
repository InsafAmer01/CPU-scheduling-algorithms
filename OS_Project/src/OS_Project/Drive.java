package OS_Project;

/*
 * simulation of Round Robin (RR) CPU scheduling algorithm
 */
public class Drive {
	public static CircularQueue<PCB> readyQ; // ready Queue
	public static CircularQueue<PCB> jobQ; // Jop Queue
	public static CircularQueue<PCB> ioQ; // I/o Queue
	static int R1 = 0, R2 = 0; // regester To switch
	static final int quantumTime = 10;

	public static void main(String[] args) {

		initialize();
		System.out.println(
				"\n\n\t\t\t\t\t\t....................................After initialize 3 Queues ...............................................");
		System.out.println("\t\t\t*The Read Queue is -- > " + readyQ.toString());
		System.out.println("\t\t\t*The jop Queue is -- > " + jobQ.toString());
		System.out.println("\t\t\t*The IO  Queue -- > " + ioQ.toString());

		// call function to scheduling jobs
		scheduleRR(readyQ.dequeue());

		System.out.println(
				"\n\n\t\t\t\t\t\t.......................................... The final result for 3 Queues ...........................................");
		System.out.println("\t\t\t*The Read Queue is -- > " + readyQ.toString());
		System.out.println("\t\t\t*The jop Queue is -- > " + jobQ.toString());
		System.out.println("\t\t\t*The IO  Queue -- > " + ioQ.toString());

	}

	public static void initialize() {

		// initialize
		readyQ = new CircularQueue<PCB>(8);
		jobQ = new CircularQueue<PCB>(9);
		ioQ = new CircularQueue<PCB>(17);

		// Create 8 processes in the readyQ
		for (int i = 0; i < readyQ.length(); i++)
			readyQ.enqueue(new PCB(i + 1));

		// Create 9 processes in the jobQ
		for (int i = 0; i < jobQ.length(); i++)
			jobQ.enqueue(new PCB(i + 9));
	}

	public static void scheduleRR(PCB process) {

		int quantum_Count = 1;
		boolean isIO = false; // if requred I/O or not .

		while (!readyQ.isEmpty()) {

			System.out.print(
					"\n\n\t\t\t-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");

			System.out.println("\n\t\t\t>>Explanation of the data in each Queues : ");
			System.out.println("\t\t\t*The Read Queue is -- > " + readyQ.toString());
			System.out.println("\t\t\t*The jop Queue is -- > " + jobQ.toString());
			System.out.println("\t\t\t*The IO  Queue -- > " + ioQ.toString() + " \n");
			System.out.println("\t\t\t>>The sequence of executing for the Process In REDY QUEUE With ID =  "
					+ process.pro_id + " :");

			while (quantum_Count <= quantumTime && process.getCPU_Burst() >= 1 && (!isIO)) {

				System.out.println("\t\t\t\t\t\t\t\t\t ^ The process with Id = " + process.pro_id
						+ " is starting execution in case choice " + process.getPC() + " --> " + process.toString());

				/*
				 * consider a switch statement with 10 options which starts with
				 * PC=100,200,…1000. The process changes PC each time it executes one of the
				 * switch statement options (choices).
				 */
				switch (process.getPC()) {

				case 100:
					/* 
					 * service for one of the processes in the ioQ and swap it into the readyQ
					 */
					
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(600);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					if (ioQ.isEmpty())
						System.out
								.println("\t\t\t\t\t\t\t\t\t * The IO Queue IS Impty , can not Reseve process in case "
										+ process.getPC());
					else {
						if (ioQ.getFront().getIO_Burst() == 0) {
							PCB p = ioQ.dequeue();
							readyQ.enqueue(p);
							System.out.println("\t\t\t\t\t\t\t\t\t * The process  With id = " + p.pro_id
									+ " is being serviced in the ioQ");
						} else
							System.out.println("\t\t\t\t\t\t\t\t\t * The process with Id = " + ioQ.getFront().pro_id
									+ " was not completed IO equired");
					}
					break;
					
				case 200:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);

					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					R2 = (int) (Math.random() * 70);
					System.out.println("\t\t\t\t\t\t\t\t\t * The R2= " + R2 + "  in case " + process.getPC());
					process.setPC(700);

					break;

				case 300:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					R1 = (int) (Math.random() * 70);
					System.out.println("\t\t\t\t\t\t\t\t\t * The R1= " + R1 + "  in case " + process.getPC());
					process.setPC(500);
					break;

				case 400:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(800);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);
					break;

				case 500:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(200);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);
					break;

				case 600:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(300);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);
					break;

				case 700:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					R1 = R2;
					System.out.println("\t\t\t\t\t\t\t\t\t * The R2=  R1 = " + R1 + "  in case " + process.getPC());
					process.setPC(400);

					break;

				case 800:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(process.getPC() + 100);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					break;

				case 900:
					//the process CPU-time in decremented by 1
					process.setCPU_Burst(process.getCPU_Burst() - 1);
					process.setPC(process.getPC() + 100);
					quantum_Count += 1;

					// to decremented by 1 of process that existing in front in ioQ
					if (!ioQ.isEmpty() && ioQ.getFront().getIO_Burst() > 0)
						ioQ.getFront().setIO_Burst(ioQ.getFront().getIO_Burst() - 1);

					break;

				case 1000:
					/*
					 *  the process wait on the ioQ 
					 */
					process.setIO_Burst((int) (Math.random() * 5));
					if (process.getIO_Burst() != 0)
						isIO = true;

					process.setPC(100);
					quantum_Count += 1;

					break;

				}

			}

			if (isIO) {
				if (ioQ.isFull()) {
					System.out.println("can not be added on IO Queue , it is full !!");
					readyQ.enqueue(process);
				} else {
					System.out.println("\t\t\t\t\t\t\t\t\t * The process With id = " + process.pro_id
							+ " is swapped out to the ioQ that is mean this process required IO  ");
					//	When a process needs I/O, it is moved to the ioQ to be serviced.
					ioQ.enqueue(process);
					if (!readyQ.isEmpty())
						process = readyQ.dequeue();
				}

				isIO = false;
				quantum_Count = 1;
			}

			if (process.CPU_Burst == 0) {
				/*
				 * When the process finishes execution, the process is moved or swapped out from
				 * the readyQ to the jobQ initializing its PCB fields to new values and another
				 * process is brought (swapped in) from the jobQ ..
				 */

				System.out.println("\t\t\t >> The process With Id = " + process.pro_id
						+ "  is finished execution and moved to the jobQ showing its new PCB values ");

				if (jobQ.getFront().getCPU_Burst() != 0) {
					PCB p = jobQ.dequeue();
					jobQ.enqueue(new PCB(process.pro_id, 0));
					readyQ.enqueue(p);
					System.out.println(
							"\t\t\tAnd the process With id = " + p.pro_id + " is swapped in the readyQ from JopQ  ");
				}

				if (!readyQ.isEmpty())
					process = readyQ.dequeue();

				quantum_Count = 1;
			}

			else if (process.CPU_Burst != 0 && quantum_Count > quantumTime) {
				// The quantum_Count is fineshed And CPU_Burst is not Finshed execution
				readyQ.enqueue(process);
				quantum_Count = 1;
				process = readyQ.dequeue();
			}

		}

		if (!ioQ.isEmpty() /* && ioQ.getFront().getIO_Burst() == 0 */) {
			process = ioQ.dequeue();
			process.setIO_Burst(0);
			quantum_Count = 1;
			readyQ.enqueue(process);
			isIO = false;
			scheduleRR(process);
		}

	}

	public static void IOFunction(PCB process) {

		if (ioQ.isFull()) {
			System.out.println("can not be added on IO Queue , it is full !!");
			readyQ.enqueue(process);

		} else {
			System.out.println("\n\t\t\t\t\t\t\t\t\tThe process With id = " + process.pro_id
					+ " is swapped out to the ioQ that is mean this process required IO  ");
			ioQ.enqueue(process);
			if (!readyQ.isEmpty())
				process = readyQ.dequeue();
		}
	}

}
