package templateCoffee;

abstract class CoffeeMaker {
    CoffeeMaker() {
    }

    final void prepare() {
        boil();
        grind();
        brew();
        serve();
    }

    abstract void boil();

    abstract void grind();

    abstract void brew();

    abstract void serve();
}

class EspressoMaker extends CoffeeMaker {
    void boil() {
        System.out.println("boil espresso");
    }

    void grind() {
        System.out.println("Grind espresso");
    }

    void brew() {
        System.out.println("High pressure extraction???");
    }

    void serve() {
        System.out.println("Serving espresso.");
    }
}

class FrenchPressMaker extends CoffeeMaker {
    void boil() {
        System.out.println("Boiling water for French.");
    }

    void brew() {
        System.out.println("Brewing French.");
    }

    void serve() {
        System.out.println("Serving French");
    }

    void grind() {
        System.out.println("Grinding french.");
    }
}

class DripCoffeeMaker extends CoffeeMaker {

    void boil() {
        System.out.println("Boil drip");
    }

    void brew() {
        System.out.println("Brewing drip");
    }

    void serve() {
        System.out.println("Serving drip.");
    }

    void grind() {
        System.out.println("Grind drip");
    }
}

class Main {
    public static void main(String[] args) {
        CoffeeMaker french = new FrenchPressMaker();
        CoffeeMaker espresso = new EspressoMaker();
        espresso.prepare();
        System.out.println();
        french.prepare();
    }
}
