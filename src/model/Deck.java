package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.PLANT, "Bulbassaur", 80, 30, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.FIRE, "Charizard", 100, 45, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.FIRE, "Charmander", 80, 30, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Cubone", 50, 25, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Diglet", 80, 40, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.WATER, "Dratini", 70, 35, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.RAIN, "Eletrabuzz", 95, 45, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.PSYCHIC, "Gastle", 100, 45, 2));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.PSYCHIC, "Gengar", 110, 55, 2));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Geodute", 80, 25, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.PSYCHIC, "Hauter", 90, 55, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.PSYCHIC, "Jynx", 100, 75, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.WATER, "Lapras", 200, 40, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Meowth", 80, 20, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Onyx", 150, 35, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Pidgey", 100, 30, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.RAIN, "Pikachu", 100, 50, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Pinsir", 120, 35, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.FIRE, "Ponyta", 90, 50, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.WATER, "Psyduck", 90, 30, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Sperow", 100, 30, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.WATER, "Squirtle", 100, 35, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Togepi", 75, 20, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.NORMAL, "Vulpix", 100, 40, 1));
        deck.add(new Pokemon(TypeCard.POKEMON, TypeElement.RAIN, "Zaptos", 120, 45, 1));

        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PLANT));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PLANT));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.FIRE));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.FIRE));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.FIRE));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.FIRE));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.WATER));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.WATER));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.WATER));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.WATER));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.WATER));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.RAIN));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.RAIN));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.RAIN));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.RAIN));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PSYCHIC));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PSYCHIC));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PSYCHIC));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PSYCHIC));
        deck.add(new Energy(TypeCard.ENERGY, TypeElement.PSYCHIC));
        
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));
        deck.add(new Coach(TypeCard.COACH));

        Collections.shuffle(deck);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public void remove(int index) {
        deck.remove(index);
    }

    public int size() {
        return deck.size();
    }

    public Card get(int index) {
        try {
            return deck.get(index);
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<Card> get() {
        return deck;
    }

    public ArrayList<Card> handCards() {
        ArrayList<Card> newDeck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            newDeck.add(deck.get(i));
            deck.remove(i);
        }
        return newDeck;
    }

    public ArrayList<Card> initHand() {
        ArrayList<Card> hand = new ArrayList<>();
        for (Card card : deck) {
            hand.add(card);
            deck.remove(card);
        }
        return hand;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (Card card : deck) {
            msg.append(card).append("\n");
        }
        return msg.toString();
    }
}
