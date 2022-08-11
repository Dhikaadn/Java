abstract class Animal {
    public abstract void checkup();
    public abstract void breath();
}

public class Dog extends Animal {
    public void checkup() {
        System.out.println("Dog checkup");
    }
    public void breath(){
        System.out.println("Dog breathing.");
    }
}

public class Cat extends Animal implements LivingThing{
    public void checkup(){
        System.out.println("Cat checkup");
    }

    public void breath(){
        System.out.println("Cat breathing.");
    }

    public void walk();
    public void eat(){ }
}

public class Bird extends Animal{
    public void breath(){
        System.out.println("Bird breathing.");
    }

    public void checkup() {
        System.out.println("Bird checkup");
    }
}

public interface LivingThing{
    public void walk();
    public void eat(){ }
}

public class Human implements LivingThing{
    public void breath(){
        System.out.println("Human breathing.");
    }

    public void walk();
    public void eat(){ }
}