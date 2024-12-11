package textEditorCommand;

import java.util.Stack;

interface Command {
    void action();
}

class Type implements Command {
    private Document document;

    Type(Document document) {
        this.document = document;
    }

    public void action() {
        document.type();
    }
}

class Delete implements Command {
    private Document document;

    Delete(Document document) {
        this.document = document;
    }

    public void action() {
        document.delete();
    }
}

class Copy implements Command {
    private Document document;

    Copy(Document document) {
        this.document = document;
    }

    public void action() {
        document.copy();
    }
}

class Paste implements Command {
    private Document document;

    Paste(Document document) {
        this.document = document;
    }

    public void action() {
        document.paste();
    }
}

class Undo implements Command {
    private Document document;

    Undo(Document document) {
        this.document = document;
    }

    public void action() {
        document.undo();
    }

}

class Redo implements Command {
    private Document document;

    Redo(Document document) {
        this.document = document;
    }

    public void action() {
        document.redo();
    }
}

class TextEditor {
    private Stack<Command> commands;
    Command command;

    TextEditor() {
        commands = new Stack<>();
    }

    void setCommand(Command command) {
        this.command = command;
    }

    void action() {
        command.action();
        commands.add(command);
    }
    void print(){
        System.out.println("Stack: ");
        for (Command c : commands){
            System.out.println(c);
        }
    }
}

interface Document {
    void type();

    void delete();

    void copy();

    void paste();

    void undo();

    void redo();
}

class TextDocument implements Document {

    @Override
    public void type() {
        System.out.println("Typing in Text Document.");
    }

    @Override
    public void delete() {
        System.out.println("Deleting in Text Document.");
    }

    @Override
    public void copy() {
        System.out.println("Copying in Text Document.");
    }

    @Override
    public void paste() {
        System.out.println("Pasting in Text Document.");
    }

    @Override
    public void undo() {
        System.out.println("Undoing in Text Document.");
    }

    @Override
    public void redo() {
        System.out.println("Redoing in Text Document.");
    }

}

class Main {
    public static void main(String[] args) {
        Document document = new TextDocument();
        Command[] commands = {
                new Undo(document),
                new Redo(document),
                new Paste(document),
                new Copy(document),
                new Delete(document),
                new Type(document),
        };

        TextEditor editor = new TextEditor();
        System.out.println();
        for (Command c : commands){
            editor.setCommand(c);
            editor.action();
        }
        editor.print();
    }
}
