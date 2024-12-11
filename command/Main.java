package command;

interface Command {
    void exec();
}

class Control {
    private Command cmd;

    void setCmd(Command cmd) {
        this.cmd = cmd;
    }

    void execCmd() {
        cmd.exec();
    }
}

interface Device {
    void on();

    void off();
}

class TV implements Device {

    public void on() {
        System.out.println("TV on.");
    }

    public void off() {
        System.out.println("TV off.");
    }
}

class Stereo implements Device {

    public void on() {
        System.out.println("Stereo on.");
    }

    public void off() {
        System.out.println("Stereo off.");
    }
}

class OnCommand implements Command {
    private Device device;

    OnCommand(Device device) {
        this.device = device;
    }

    public void exec() {
        device.on();
    }
}

class OffCommand implements Command {
    private Device device;

    OffCommand(Device device) {
        this.device = device;
    }

    public void exec() {
        device.off();
    }
}

class Main {
    public static void main(String[] args) {
        TV tv = new TV();
        Command tvOn = new OnCommand(tv);
        Command tvOff = new OffCommand(tv);
        Control control = new Control();
        control.setCmd(tvOn);
        control.execCmd();
        control.setCmd(tvOff);
        control.execCmd();
        
    }
}