package model;

import java.util.Random;

public class Coach extends Card {

    private int power;

    public Coach(TypeCard type) {
        super(type);
        this.power = new Random().nextInt(100) + 1;
    }

    public void aumentarForca(Pokemon pokemon) {
        pokemon.setPower(power + pokemon.getPower());
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append(power);
        return msg.toString();
    }
}
