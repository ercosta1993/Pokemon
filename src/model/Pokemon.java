
package model;


    public class Pokemon extends Card{
    
    private TypeElement element;
    private String name;
    private int health;
    private int power;
    private int currentEnergy;
    private int requiredEnergy;

    public Pokemon(TypeCard type, TypeElement element, String name, int health, int power, int requiredEnergy) {
        super(type);
        this.element = element;
        this.name = name;
        this.health = health;
        this.power = power;
        this.currentEnergy = 0;
        this.requiredEnergy = requiredEnergy;
    }

    public TypeElement getElement() {
        return element;
    }

    public void setElement(TypeElement element) {
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getRequiredEnergy() {
        return requiredEnergy;
    }

    public void setRequiredEnergy(int requiredEnergy) {
        this.requiredEnergy = requiredEnergy;
    }

    
    
    @Override
    public String toString(){
        StringBuilder msg = new StringBuilder();
        msg.append(name);
        return msg.toString();
    }
}
