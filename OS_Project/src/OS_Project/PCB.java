package OS_Project;

public class PCB {

	int pro_id;// each p has id
	int PC; // Program Counter (Instruction Pointer IP)
	int CPU_Burst;
	int IO_Burst = 0;
	// public int quantum = 10 ;

	public PCB(int pro_id) {
		this.pro_id = pro_id;
		//Set program counter PC to 100 in all processes.
		this.PC = 100;

		do {	
			//Set CPU-Burst(service time) to a value between 1-100 using random function
			this.CPU_Burst = (int) (Math.random() * 100);
		} while (CPU_Burst == 0);
	}

	public PCB(int pro_id, int newBurst) {
		this.pro_id = pro_id;
		this.PC = 100;
		this.CPU_Burst = newBurst;
		// this.CPU_Burst = (int) (Math.random() *100)/////////
	}

	public int getIO_Burst() {
		return IO_Burst;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public void setIO_Burst(int iO_Burst) {
		IO_Burst = iO_Burst;
	}

	public PCB() {

	}

	public int getPC() {
		return PC;
	}

	public void setPC(int pC) {
		PC = pC;
	}

	public int getCPU_Burst() {
		return CPU_Burst;
	}

	public void setCPU_Burst(int cPU_Burst) {
		CPU_Burst = cPU_Burst;
	}


	@Override
	public String toString() {
		return "  [" + "pro_id=" + pro_id + ", PC=" + PC + ", CPU_Burst=" + CPU_Burst + ", IO_Burst=" + IO_Burst + "]  ";
	}

}
