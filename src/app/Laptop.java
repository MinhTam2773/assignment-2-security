package app;

//Laptop computer: adds screen size to other Computer info

public class Laptop implements ComputerInfo { //Laptop dose not inherits from Computer
	private final Computer computer; //compostion
	private final String screenSize;
	
	//constructor
	public Laptop(String CPU, String RAM, String disk, String screenSize) {
        this.computer = new Computer(CPU, RAM, disk);
        this.screenSize = screenSize;
    }
	
	//getters
	public String getCPU() {
        return computer.getCPU();
    }

    public String getRAM() {
        return computer.getRAM();
    }

    public String getDisk() {
        return computer.getDisk();
    }

    public String getScreenSize() {
        return screenSize;
    }

    public Computer getComputer() {
        return computer;
    }
    
    //display format information
    @Override
    public String toString() {
        return "Type:Laptop\tCPU:" + computer.getCPU()
                + "\tRAM:" + computer.getRAM()
                + "\tDisk:" + computer.getDisk()
                + "\tScreen:" + screenSize;
    }

	
    
}