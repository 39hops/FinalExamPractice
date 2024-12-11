package strategyPayment;

import java.util.Scanner;

interface PaymentStrategy {
    void pay(int amount);
}

class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void payment(int amount) {
        strategy.pay(amount);
    }
}

class PayPalStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Paypal.");
    }
}

class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using a credit card.");
    }
}

class CryptoScamStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + "coins using ScamCoin!");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        PaymentContext context;

        System.out.print("1. PayPal\n2. Credit Card\n3. Crypto\nChoose an option: ");
        int choice = keyboard.nextInt();
        System.out.print("Enter amount to pay: ");
        int amount = keyboard.nextInt();
        switch (choice) {
            case 1 -> {
                context = new PaymentContext(new PayPalStrategy());
                context.payment(amount);
            }
            case 2 -> {
                context = new PaymentContext(new CreditCardStrategy());
                context.payment(amount);
            }
            case 3 -> {
                context = new PaymentContext(new CryptoScamStrategy());
                context.payment(amount);
            }
            default -> {
                System.out.println("Dude just enter a number listed...");
            }
        }
        keyboard.close();
    }
}