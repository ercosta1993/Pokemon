package model;

public abstract class Card {
    
    private TypeCard type;

    public Card(TypeCard type) {
        this.type = type;
    }

    public TypeCard getType() {
        return type;
    }
}
