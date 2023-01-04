package sdf;

public class Porche extends Car {

    private String turbo = "off";
    private int accelerate = 0;

    public Porche() {
        super.setColour("red");
    }

    public Porche(String colour, String registration) {
        // colour is protected so all child classes can access it
        this.colour = colour;
        //super.setColour(colour);
        setRegistration(registration);
    }

    public String getTurbo() { return turbo; }

    // I can nolonger change the color of the car 
    // once it has been instantitated
    @Override
    public void setColour(String c) { }

    public void accelerate() {
        this.accelerate++;
        if (this.accelerate > 4) {
            this.turbo = "on";
        }
    }

    public void decelerate() {
        this.accelerate--;
        if (this.accelerate <= 4) {
            this.turbo = "off";
        }
    }


}
