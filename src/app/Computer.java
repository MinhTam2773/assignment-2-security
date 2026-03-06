public final class Computer {

    private final String CPU;
    private final String RAM;
    private final String disk;

    // Constructor: all values must and shouldbe provided when creating the object
    public Computer(String CPU, String RAM, String disk) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    //Alex Notes: removed setters to make the class immutable, values can only be set at construction time

    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getDisk() {
        return disk;
    }
}
