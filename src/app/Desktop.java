// Desktop computer: 
// adds GPU type (now immutable with compositional attributes)

public final class Desktop { // Doesn't inherit from Computer
    
	private final Computer computer;
	private final String GPUType;

    //Constructors
	//No-arg constructor removed

    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        // Doesn't inherit from Computer superclass (desktop has a computer object)
        this.computer = new Computer(CPU, RAM, disk);

        // Only in Desktop class
        this.GPUType=GPUType;
    }

    // NO Setters cuz they don work in final class structure (immutability)
    
    //    public void setGPUType(String GPUType) {
	//        this.GPUType=GPUType;
	//    }
    
    
    //	   Getters  
    public String getCPU() {
        return computer.getCPU();
    }

    public String getRAM() {
        return computer.getRAM();
    }

    public String getDisk() {
        return computer.getDisk();
    }


    public String getGPUType() {
        return this.GPUType;
    }

    // Return formatted version of data
    public String toString() {
        return "Type:Desktop\tCPU:" + getCPU() + "\tRAM:" + getRAM() + "\tDisk:" + getDisk() + "\tGPU:" + getGPUType();
    }

}




