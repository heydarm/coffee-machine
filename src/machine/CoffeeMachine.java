package machine;

import java.util.Scanner;

// Coffee types enumeration
enum Coffee {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    int water;
    int milk;
    int beans;
    int cost;

    Coffee(int water, int milk, int beans, int cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }
}

// Coffee Machine class
public class CoffeeMachine {
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    // Constructor
    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
    }

    // main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine machine = new CoffeeMachine();
        boolean end;

        do {
            end = machine.actionsHandler(scanner);
        } while (!end);

        scanner.close();
    }

    // Actions Handler
    private boolean actionsHandler(Scanner scanner) {
        System.out.print("Write action (buy, fill, take, remaining, exit):\n");
        String action = scanner.next().trim();
        System.out.println();

        switch (action) {
            case "buy":
                buy(scanner);
                return false;
            case "fill":
                fill(scanner);
                return false;
            case "take":
                take();
                return false;
            case "remaining":
                displaySupplies();
                return false;
            case "exit":
                return true;
            default:
                System.out.print("There is no such action. Please try again.\n");
                return false;
        }
    }

    //==================== Display Supplies method ====================
    private void displaySupplies() {
        System.out.print("The coffee machine has:\n");
        System.out.printf("%d of water\n", this.water);
        System.out.printf("%d of milk\n", this.milk);
        System.out.printf("%d of coffee beans\n", this.beans);
        System.out.printf("%d of disposable cups\n", this.cups);
        System.out.printf("%d of money\n\n", this.money);
    }

    //==================== Buy method ====================
    private void buy(Scanner scanner) {
        while (true) {
            System.out.print("What do you want to buy? " +
                    "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
            String typeOfCoffee = scanner.next().trim();

            switch (typeOfCoffee) {
                case "1":
                    buyHandler(Coffee.ESPRESSO);
                    return;
                case "2":
                    buyHandler(Coffee.LATTE);
                    return;
                case "3":
                    buyHandler(Coffee.CAPPUCCINO);
                    return;
                case "back":
                    return;
                default:
                    System.out.print("There is no such type of coffee\n");
            }
        }
    }

    //==================== Buy Handler method ====================
    private void buyHandler(Coffee coffee) {
        if (this.water < coffee.water) {
            System.out.print("Sorry, not enough water!\n\n");
        } else if (this.milk < coffee.milk) {
            System.out.print("Sorry, not enough milk!\n\n");
        } else if (this.beans < coffee.beans) {
            System.out.print("Sorry, not enough coffee beans!\n\n");
        } else if (this.cups <= 0) {
            System.out.print("Sorry, not enough disposable cups!\n\n");
        } else {
            System.out.print("I have enough resources, making you a coffee!\n\n");
            this.water -= coffee.water;
            this.milk -= coffee.milk;
            this.beans -= coffee.beans;
            this.cups--;
            this.money += coffee.cost;
        }
    }

    //==================== Fill method ====================
    private void fill(Scanner scanner) {
        System.out.print("Write how many ml of water do you want to add:\n");
        this.water += scanner.nextInt();

        System.out.print("Write how many ml of milk do you want to add:\n");
        this.milk += scanner.nextInt();

        System.out.print("Write how many grams of coffee beans do you want to add:\n");
        this.beans += scanner.nextInt();

        System.out.print("Write how many disposable cups of coffee do you want to add:\n");
        this.cups += scanner.nextInt();
        System.out.print("\n");
    }

    //==================== Take method ====================
    private void take() {
        System.out.printf("I gave you %d\n\n", this.money);
        this.money = 0;
    }
}