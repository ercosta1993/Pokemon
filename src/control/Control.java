package control;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Card;
import model.Coach;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.TypeCard;
import model.TypeElement;

public class Control implements ControlGame {

    @Override
    public Card getCard(ArrayList<Card> cards, int index) {
        return cards.get(index);
    }

    // caminho comum a todas as imagens usadas no jogo
    @Override
    public String getPath() {
        return "D:\\PokemonTradingGame\\src\\images\\";
    }

    @Override
    public void selectCard(ArrayList<JLabel> labels, ArrayList<Card> cards, int index) {
        try {
            labels.get(index).setBorder(BorderFactory.createLineBorder(Color.GREEN));
            for (int i = 0; i < labels.size(); i++) {
                if (labels.get(i) != labels.get(index)) {
                    labels.get(i).setBorder(BorderFactory.createEmptyBorder());
                }
            }
            System.out.println(cards.get(index));
            verifyTypeCard(cards.get(index));
        } catch (Exception e) {
            System.out.println("-");
        }

    }

    // metodo que descarta o card selecionado
    @Override
    public void discard(ArrayList<JLabel> labels, ArrayList<Card> cards, int index, Card card) {
        labels.get(index).setBorder(BorderFactory.createEmptyBorder());
        cards.remove(card);
        resetCard(labels.get(cards.size()));
    }

    // reseta o card selecionado
    @Override
    public void resetCard(JLabel label) {
        ImageIcon img = new ImageIcon(getPath() + "game_pokecard.PNG");
        label.setIcon(img);
        label.setEnabled(false);
    }

    // abastece a mão do jogador que tem menos de 5 cards
    @Override
    public boolean supply(ArrayList<Card> cards, ArrayList<JLabel> labels, Player gamer) {
        if (cards.size() < 5) {
            cards.add(gamer.getDeck().get(gamer.getDeck().size() - 1));
            gamer.getDeck().remove(gamer.getDeck().size() - 1);
            return true;
        } else {
            return false;
        }
    }

    // manda o pokemon para a batalha
    @Override
    public void usePokemon(ArrayList<Card> cards, ArrayList<JLabel> labels, int index, JLabel label) {

        searchImage(cards.get(index), label);
        discard(labels, cards, index, cards.get(index));
    }

    // substitui o pokemon
    @Override
    public void changePokemon(ArrayList<Card> cards, ArrayList<JLabel> labels, Pokemon pokemon, int index, JLabel label) {
        searchImage(cards.get(index), labels.get(index));
        searchImage(pokemon, label);
    }

    // procura e atualiza a imagem do card
    @Override
    public void searchImage(Card card, JLabel label) {
        String nameFile = "";

        switch (card.getType()) {
            case POKEMON:
                Pokemon pokemon = (Pokemon) card;
                switch (pokemon.getName()) {
                    case "Bulbassaur":
                        nameFile = "pokemon_bulbassaur.jpg";
                        break;
                    case "Charizard":
                        nameFile = "pokemon_charizard.jpg";
                        break;
                    case "Charmander":
                        nameFile = "pokemon_charmander.jpg";
                        break;
                    case "Cubone":
                        nameFile = "pokemon_cubone.jpg";
                        break;
                    case "Diglet":
                        nameFile = "pokemon_diglet.jpg";
                        break;
                    case "Dratini":
                        nameFile = "pokemon_dratini.jpg";
                        break;
                    case "Eletrabuzz":
                        nameFile = "pokemon_eletrabuzz.jpg";
                        break;
                    case "Gastle":
                        nameFile = "pokemon_gastle.jpg";
                        break;
                    case "Gengar":
                        nameFile = "pokemon_gengar.jpg";
                        break;
                    case "Geodute":
                        nameFile = "pokemon_geodute.jpg";
                        break;
                    case "Hauter":
                        nameFile = "pokemon_hauter.jpg";
                        break;
                    case "Jynx":
                        nameFile = "pokemon_jynx.jpg";
                        break;
                    case "Lapras":
                        nameFile = "pokemon_lapras.jpg";
                        break;
                    case "Meowth":
                        nameFile = "pokemon_meowth.jpg";
                        break;
                    case "Onyx":
                        nameFile = "pokemon_onyx.jpg";
                        break;
                    case "Pidgey":
                        nameFile = "pokemon_pidgey.jpg";
                        break;
                    case "Pikachu":
                        nameFile = "pokemon_pikachu.jpg";
                        break;
                    case "Pinsir":
                        nameFile = "pokemon_pinsir.jpg";
                        break;
                    case "Ponyta":
                        nameFile = "pokemon_ponyta.jpg";
                        break;
                    case "Psyduck":
                        nameFile = "pokemon_psyduck.jpg";
                        break;
                    case "Sperow":
                        nameFile = "pokemon_sperow.jpg";
                        break;
                    case "Squirtle":
                        nameFile = "pokemon_squirtle.jpg";
                        break;
                    case "Togepi":
                        nameFile = "pokemon_togepi.jpg";
                        break;
                    case "Vulpix":
                        nameFile = "pokemon_vulpix.jpg";
                        break;
                    case "Zaptos":
                        nameFile = "pokemon_zaptos.jpg";
                        break;
                }
                break;
            case ENERGY:
                Energy energy = (Energy) card;
                switch (energy.getElement()) {
                    case WATER:
                        nameFile = "energia_agua.jpg";
                        break;
                    case FIRE:
                        nameFile = "energia_fogo.jpg";
                        break;
                    case PLANT:
                        nameFile = "energia_planta.jpg";
                        break;
                    case PSYCHIC:
                        nameFile = "energia_psiquica.jpg";
                        break;
                    case RAIN:
                        nameFile = "energia_raio.jpg";
                        break;
                }
                break;
            case COACH:
                nameFile = "treinador.jpg";
                break;
            default:
                nameFile = "game_pokecard.PNG";
                break;
        }
        ImageIcon img = new ImageIcon(getPath() + nameFile);
        label.setIcon(img);
    }

    // seta o campo descrição de acordo com o tipo do card 
    @Override
    public String descricao(Card card) {
        StringBuilder descricao = new StringBuilder();
        String nl = "<html><br></html>";
        if (card != null) {
            switch (card.getType()) {
                case POKEMON:
                    Pokemon pokemon = (Pokemon) card;
                    if (pokemon.getElement() != TypeElement.NORMAL) {
                        descricao.append("<html>Este pokemon precisa de uma carta do<br>tipo ").append(pokemon.getElement()).append(" para poder atacar.</html>");
                    } else {
                        descricao.append("<html>Este pokemon pode usar qualquer carta<br>de energia</html>");
                    }
                    break;
                case ENERGY:
                    Energy energy = (Energy) card;
                    descricao.append("<html>Dá energia para os pokemon do tipo<br>").append(energy.getElement()).append(".</html>");
                    break;
                case COACH:
                    Coach coach = (Coach) card;
                    descricao.append("Aumenta a força do pokemon em + ").append(coach.getPower());
                    break;
            }
        }else{
            descricao.append("-");
        }
        return descricao.toString();
    }

    // referente as cartas de suporte (energia e treinador)
    @Override
    public boolean support(ArrayList<Card> cards, ArrayList<JLabel> labels, Pokemon pokemon, int index) {
        try {
            if (pokemon != null) {
                switch (cards.get(index).getType()) {
                    case ENERGY:
                        Energy energy = (Energy) cards.get(index);
                        if (pokemon.getElement() != TypeElement.NORMAL) {
                            if (energy.getElement() == pokemon.getElement()) {
                                if (pokemon.getCurrentEnergy() < pokemon.getRequiredEnergy()) {
                                    pokemon.setCurrentEnergy(pokemon.getCurrentEnergy() + 1);
                                    JOptionPane.showMessageDialog(null, "Energia adicionada ao pokemon " + pokemon.getName());
                                    discard(labels, cards, index, cards.get(index));
                                    return true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Pokemon com energia máxima!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Elementos carta / pokemon diferentes!");
                            }
                        } else {
                            if (pokemon.getCurrentEnergy() < pokemon.getRequiredEnergy()) {
                                pokemon.setCurrentEnergy(pokemon.getCurrentEnergy() + 1);
                                JOptionPane.showMessageDialog(null, "Energia adicionada ao pokemon " + pokemon.getName());
                                discard(labels, cards, index, cards.get(index));
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Pokemon com energia máxima!");
                            }
                        }
                    case COACH:
                        Coach coach = (Coach) cards.get(index);
                        coach.aumentarForca(pokemon);
                        JOptionPane.showMessageDialog(null, "Força aumentada para " + pokemon.getPower());
                        discard(labels, cards, index, cards.get(index));
                        return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem um pokemon em batalha!");
            }
        } catch (Exception e) {

        }
        return false;
    }

    // valida o ataque de um pokemon
    @Override
    public boolean verifyAttack(Pokemon pokemon) {
        return pokemon.getCurrentEnergy() == pokemon.getRequiredEnergy();
    }

    // metodo que faz um pokemon atacar o outro
    @Override
    public int attackPokemon(Pokemon pokemon1, Pokemon pokemon2, Player player) {
        try {
            if (pokemon2 != null) {
                pokemon2.setHealth(pokemon2.getHealth() - pokemon1.getPower());
                return 0;
            } else {
                attackOpponent(pokemon1, player);
                return 1;
            }
        } catch (Exception e) {

        }
        return -1;
    }

    // metodo que faz o pokemon atacar os pontos de vida do oponente
    @Override
    public boolean attackOpponent(Pokemon pokemon, Player player) {
        player.setPoints(player.getPoints() - pokemon.getPower());
        return true;
    }

    // verifica o tipo do card
    @Override
    public TypeCard verifyTypeCard(Card card) {
        return card.getType();
    }

    // valida o fim do jogo
    @Override
    public boolean verifyEndGame(Player gamer) {
        return gamer.getPoints() <= 0;
    }
}
