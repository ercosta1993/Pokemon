
package model;


public class Player {
    
    private String name;
    private int points;
    private Deck deck;

    public Player(String name, int points, Deck deck) {
        this.name = name;
        this.points = points;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}

