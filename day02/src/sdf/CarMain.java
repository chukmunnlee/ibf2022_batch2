package sdf;

public class CarMain {

    // entry point
    public static void main(String[] args) {
        // Instantiate an instance of car
        Car c = new Car();
        c.setColour("grey");
        c.setRegistration("sdf1234B");
        c.start();
        System.out.printf("Is the car started? %b\n", c.isStarted());

        c.getOutOfMyWay();

        Car d = new Car();
        //d.setColour("red");
        System.out.printf("Colour of my car? %s\n", d.getColour());

        Car e = new Car("black", "s1234c");
        System.out.printf("Colour of my car? %s\n", e.getColour());
        System.out.printf("Registration of my car? %s\n", e.getRegistration());

        Porche p = new Porche();
        System.out.printf("Porche Colour of my car? %s\n", p.getColour());
        p.setColour("pink");
        System.out.printf("Porche Colour of my car? %s\n", p.getColour());
    }
    
}
