package app;

public class ComputerData implements ComputerInfo {
    private final String cpu;
    private final String ram;
    private final String disk;

    public ComputerData(String cpu, String ram, String disk) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
    }

    @Override
    public String getCpu() { return cpu; }
    @Override
    public String getRam() { return ram; }
    @Override
    public String getDisk() { return disk; }

    // Optional: a toString for debugging, but not required for the program
}