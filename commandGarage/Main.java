package commandGarage;

interface Command {
    void exec();
}

class OpenCommand implements Command {
    private Door door;

    OpenCommand(Door door) {
        this.door = door;
    }

    public void exec() {
        door.open();
    }
}

class CloseCommand implements Command {
    private Door door;

    CloseCommand(Door door) {
        this.door = door;
    }

    public void exec() {
        door.close();
    }
}

interface Door {
    boolean open();

    boolean close();
}

class GarageDoor implements Door {
    boolean isOpen;

    public boolean open() {
        System.out.println("Opening garage door.");
        return true;
    }

    public boolean close() {
        System.out.println("Closing garage door.");
        return false;
    }

    void undo() {
        if (isOpen) {
            close();
            return;
        }
        open();
    }
}

class RemoteControl {
    private Command command;

    void setCommand(Command command){
        this.command = command;
    }

    void button() {
        command.exec();
    }
}

class Main {
    public static void main(String[] args) {
        Door door = new GarageDoor();
        Command openCommand = new OpenCommand(door);
        Command closeCommand = new CloseCommand(door);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(openCommand);
        remote.button();
        remote.setCommand(closeCommand);
        remote.button();
        
    }
}