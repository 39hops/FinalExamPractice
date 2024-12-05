package template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class Network {
    String username;
    String password;

    Network() {
    }

    boolean post(String msg) {
        if (login(username, password)) {
            boolean result = send(msg.getBytes());
            logout();
            return result;
        }
        return false;
    }

    abstract boolean login(String username, String password);

    abstract boolean send(byte[] data);

    abstract void logout();
}

class Facebook extends Network {
    Facebook(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    boolean login(String username, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + this.username);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Facebook");
        return true;
    }

    @Override
    boolean send(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logout() {
        System.out.println("User: '" + username + "' was logged out from Facebook");
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.println(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Twitter extends Network {
    Twitter(String username, String password) {
        this.password = password;
        this.username = username;
    }

    @Override
    boolean login(String username, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + username);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Twitter");
        return true;
    }

    @Override
    boolean send(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logout() {
        System.out.println("User: '" + username + "' was logged out from Twitter");
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("Username: ");
        String userName = reader.readLine();
        System.out.print("Password: ");
        String password = reader.readLine();

        System.out.print("Input message: ");
        String message = reader.readLine();
        System.out.print("\n1. Facebook\n2. Twitter\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
                network = new Facebook(userName, password);
                break;
            case 2:
                network = new Twitter(userName, password);
                break;
            default:
                System.out.println("Option is trash. Shutting down...");
        }
        network.post(message);
    }
}