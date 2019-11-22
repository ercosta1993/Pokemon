package control;

import java.util.ArrayList;
import javax.swing.JLabel;
import model.Card;
import model.Player;
import model.Pokemon;
import model.TypeCard;

public interface ControlGame {
    
    /**
     * Retorna a carta selecionada
     * @param cards - cartas do jogador
     * @param index - indes selecionado
     * @return - carta do jogador
     */
    public Card getCard(ArrayList<Card> cards, int index);
    
    // retorna o diretório das imagens (atualizar caso o projeto mude de lugar)
    public String getPath();
    
    /**
     * Seleciona a carta
     * @param labels - jlabels do jogador
     * @param cards - cartas do jogador
     * @param index - index selecionado
     */
    public void selectCard(ArrayList<JLabel> labels, ArrayList<Card> cards, int index);
    
    /**
     * Descarta a carta selecionada
     * @param labels - jlabels do jogador
     * @param cards - cartas do jogador
     * @param index - index selecionado
     * @param card - carta que o jogador irá descartar
     */
    public void discard(ArrayList<JLabel> labels, ArrayList<Card> cards, int index, Card card);
    
    /**
     * @param label - label que irá ser descartada
     */
    public void resetCard(JLabel label);
    
    /**
     * Repõe de 1 a 1 o número de cartas do jogador
     * @param cards - cartas do jogador
     * @param labels - jlabels do jogador
     * @param gamer - jogador
     * @return 
     */
    public boolean supply(ArrayList<Card> cards, ArrayList<JLabel> labels, Player gamer);
    
    /**
     * Lança o Pokemon para a luta
     * @param cards - cartas do jogador
     * @param labels - jlabels do jogador
     * @param index - index de seleção
     * @param label - label responsável por guardar a carta do pokemon atual do jogador
     */
    public void usePokemon(ArrayList<Card> cards, ArrayList<JLabel> labels, int index, JLabel label);
    
    /**
     * Substitui o Pokemon
     * @param cards - cartas do jogador
     * @param labels - jlabels do jogador
     * @param pokemon - pokemon atual do jogador
     * @param index - index selecionado
     * @param label - label de seleção
     */
    public void changePokemon(ArrayList<Card> cards, ArrayList<JLabel> labels, Pokemon pokemon, int index, JLabel label);
    
    /**
     * Muda a imagem de um jlabel
     * @param card - card de seleção
     * @param label - label de seleção
     */
    public void searchImage(Card card, JLabel label);
    
    /**
     *
     * @param card - card de seleção
     * @return uma String com informações referentes ao card selecionado
     */
    public String descricao(Card card);
    
    /**
     * Ativa as características das cartas do tipo suporte
     * @param cards
     * @param labels
     * @param pokemon
     * @param index
     * @return 
     */
    public boolean support(ArrayList<Card> cards, ArrayList<JLabel> labels, Pokemon pokemon, int index);
    
    /**
     *
     * @param pokemon
     * @return
     */
    public boolean verifyAttack(Pokemon pokemon);
    
    /**
     * Ataca o pokemon do oponente
     * @param pokemon1
     * @param pokemon2
     * @param player
     * @return 
     */
    public int attackPokemon(Pokemon pokemon1, Pokemon pokemon2, Player player);
    
    /**
     * Ataca o oponente
     * @param pokemon
     * @param player
     * @return 
     */
    public boolean attackOpponent(Pokemon pokemon, Player player);
    
    /**
     * Verifica o tipo da carta em questão
     * @param card
     * @return 
     */
    public TypeCard verifyTypeCard(Card card);
    
    /**
     * Verifica se o jogo chagou ao final (oponente sem pontos)
     * @param gamer
     * @return 
     */
    public boolean verifyEndGame(Player gamer);
    
    
}
