package view;

import control.Control;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Card;
import model.Coach;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.TypeCard;

public class Game extends javax.swing.JFrame {

    private Clip clip;

    private Player player1;
    private Player player2;

    private ArrayList<Card> cardsPlayer1;
    private ArrayList<Card> cardsPlayer2;

    private ArrayList<JLabel> labelsPlayer1;
    private ArrayList<JLabel> labelsPlayer2;

    private Pokemon currentPokemonPlayer1 = null;
    private Pokemon currentPokemonPlayer2 = null;

    private Control control;

    private static int vez = 0;
    private static int cont = 0;
    private static int index = 0;

    public Game(Player player1, Player player2) {
        initComponents();

        this.player1 = player1;
        this.player2 = player2;

        cardsPlayer1 = player1.getDeck().handCards();
        cardsPlayer2 = player2.getDeck().handCards();

        labelsPlayer1 = new ArrayList<>();
        labelsPlayer2 = new ArrayList<>();

        control = new Control();

        initSettings();
        initLayout();
        updateLabels();

        System.out.println(cardsPlayer1);
        System.out.println(cardsPlayer2);

        play();
    }

    // inicializa o som da tela Game
    private void play() {
        try {
            String resource = "music_battle.wav";
            InputStream input = getClass().getResourceAsStream(resource);
            clip = AudioSystem.getClip();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(input);
            clip.open(audioInput);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {

        }
    }

    // para o som
    private void stop(){
        clip.stop();
    }
    
    // inicializa o layout do jogo
    private void initLayout() {
        player1_nome.setText(player1.getName());
        player1_pontos.setText(String.valueOf(player1.getPoints()));
        player2_nome.setText(player2.getName());
        player2_pontos.setText(String.valueOf(player2.getPoints()));

        control.resetCard(player1_deck_);
        control.resetCard(player2_deck_);
        control.resetCard(player1_lb_atual_);
        control.resetCard(player2_lb_atual_);
        control.resetCard(player1_lb_cp_);
        control.resetCard(player2_lb_cp_);

        player1_btn_atacar_.setEnabled(false);
        player1_btn_usar_.setEnabled(false);
        player1_btn_suporte_.setEnabled(false);
        player1_btn_descartar_.setEnabled(false);

        player1_btn_atacar_.setEnabled(false);
        player1_btn_usar_.setEnabled(false);
        player1_btn_suporte_.setEnabled(false);
        player1_btn_descartar_.setEnabled(false);

        ImageIcon play = new ImageIcon(control.getPath() + "play.png");
        lb_play_.setIcon(play);
        ImageIcon stop = new ImageIcon(control.getPath() + "stop.png");
        lb_stop_.setIcon(stop);
        lb_play_.setVisible(true);
        lb_stop_.setVisible(true);

        layoutTurnPlayer1();
    }

    // layout do jogador 1
    private void layoutTurnPlayer1() {
        cont = 0;
        try {
            desabityPlayer2();

            player1_deck_.setEnabled(cardsPlayer1.size() != 5);

            player1_lb_cp_.setEnabled(currentPokemonPlayer1 != null);
            player2_lb_cp_.setEnabled(currentPokemonPlayer2 != null);

            player1_lb_0_.setEnabled(cardsPlayer1.get(0) != null);
            player1_lb_1_.setEnabled(cardsPlayer1.get(1) != null);
            player1_lb_2_.setEnabled(cardsPlayer1.get(2) != null);
            player1_lb_3_.setEnabled(cardsPlayer1.get(3) != null);
            player1_lb_4_.setEnabled(cardsPlayer1.get(4) != null);

            player1_btn_atacar_.setEnabled(false);
            player1_btn_usar_.setEnabled(false);
            player1_btn_suporte_.setEnabled(false);
            player1_btn_descartar_.setEnabled(false);
        } catch (Exception e) {
            updateLabels();
        }
        updateLabels();
    }

    // layout do jogador 2
    private void layoutTurnPlayer2() {
        cont = 0;
        try {
            desabityPlayer1();

            player2_deck_.setEnabled(cardsPlayer2.size() != 5);

            player1_lb_cp_.setEnabled(currentPokemonPlayer1 != null);
            player2_lb_cp_.setEnabled(currentPokemonPlayer2 != null);

            player2_lb_0_.setEnabled(cardsPlayer2.get(0) != null);
            player2_lb_1_.setEnabled(cardsPlayer2.get(1) != null);
            player2_lb_2_.setEnabled(cardsPlayer2.get(2) != null);
            player2_lb_3_.setEnabled(cardsPlayer2.get(3) != null);
            player2_lb_4_.setEnabled(cardsPlayer2.get(4) != null);

            player2_btn_atacar_.setEnabled(false);
            player2_btn_usar_.setEnabled(false);
            player2_btn_suporte_.setEnabled(false);
            player2_btn_descartar_.setEnabled(false);
        } catch (Exception e) {
            updateLabels();
        }
        updateLabels();
    }

    // desabilita os botões do jogador 1
    private void desabityPlayer1() {
        player1_deck_.setEnabled(false);

        player1_lb_0_.setEnabled(false);
        player1_lb_1_.setEnabled(false);
        player1_lb_2_.setEnabled(false);
        player1_lb_3_.setEnabled(false);
        player1_lb_4_.setEnabled(false);

        player1_btn_atacar_.setEnabled(false);
        player1_btn_usar_.setEnabled(false);
        player1_btn_suporte_.setEnabled(false);
        player1_btn_descartar_.setEnabled(false);
    }

    // desabilita os botões do jogador 2
    private void desabityPlayer2() {
        player2_deck_.setEnabled(false);

        player2_lb_0_.setEnabled(false);
        player2_lb_1_.setEnabled(false);
        player2_lb_2_.setEnabled(false);
        player2_lb_3_.setEnabled(false);
        player2_lb_4_.setEnabled(false);

        player2_btn_atacar_.setEnabled(false);
        player2_btn_usar_.setEnabled(false);
        player2_btn_suporte_.setEnabled(false);
        player2_btn_descartar_.setEnabled(false);
    }

    // inicializa as configurações do jogo
    private void initSettings() {
        for (int i = 0; i < 5; i++) {
            labelsPlayer1.add(player1_lb_0_);
            labelsPlayer1.add(player1_lb_1_);
            labelsPlayer1.add(player1_lb_2_);
            labelsPlayer1.add(player1_lb_3_);
            labelsPlayer1.add(player1_lb_4_);

            labelsPlayer2.add(player2_lb_0_);
            labelsPlayer2.add(player2_lb_1_);
            labelsPlayer2.add(player2_lb_2_);
            labelsPlayer2.add(player2_lb_3_);
            labelsPlayer2.add(player2_lb_4_);
        }
        ImageIcon img = new ImageIcon(control.getPath() + "refresh.png");
        lb_refresh_turn_.setIcon(img);
    }

    // atualiza os labels a cada turno
    private void updateLabels() {
        for (int i = 0; i < cardsPlayer1.size(); i++) {
            control.searchImage(cardsPlayer1.get(i), labelsPlayer1.get(i));
        }
        for (int i = 0; i < cardsPlayer2.size(); i++) {
            control.searchImage(cardsPlayer2.get(i), labelsPlayer2.get(i));
        }

    }

    // controle do botões pelo tipo de card selecionado
    private void managerButtons(int player, TypeCard type) {
        try {
            if (player == 1) {
                switch (type) {
                    case POKEMON:
                        if (currentPokemonPlayer1 == null) {
                            player1_btn_usar_.setText("Usar");
                            player1_btn_usar_.setEnabled(true);
                            player1_btn_suporte_.setEnabled(false);
                        } else {
                            player1_btn_usar_.setEnabled(true);
                            player1_btn_suporte_.setEnabled(false);
                        }
                        break;
                    default:
                        player1_btn_usar_.setEnabled(false);
                        player1_btn_suporte_.setEnabled(true);
                        break;
                }
                player1_btn_atacar_.setEnabled(false);
                player1_btn_descartar_.setEnabled(true);
            } else {
                switch (type) {
                    case POKEMON:
                        if (currentPokemonPlayer2 == null) {
                            player2_btn_usar_.setText("Usar");
                            player2_btn_usar_.setEnabled(true);
                            player2_btn_suporte_.setEnabled(false);
                        } else {
                            player2_btn_usar_.setEnabled(true);
                            player2_btn_suporte_.setEnabled(false);
                        }
                        break;
                    default:
                        player2_btn_usar_.setEnabled(false);
                        player2_btn_suporte_.setEnabled(true);
                        break;
                }
                player2_btn_atacar_.setEnabled(false);
                player2_btn_descartar_.setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println("-");
        }
    }

    // motodo para substituir o pokemon atual por um reserva
    private Pokemon changePokemon(ArrayList<Card> cards, ArrayList<JLabel> labels, Pokemon pokemon, JLabel label) {
        Pokemon aux = (Pokemon) cards.get(index);
        cards.set(index, pokemon);
        pokemon = aux;
        System.out.println("cards.get(i): " + cards.get(index) + " - pokemon atual: " + pokemon);
        System.out.println("mão: " + cards.get(index));
        control.searchImage(cards.get(index), labels.get(index));
        control.searchImage(aux, label);
        return aux;
    }

    // detalhes do pokemon selecionado do jogador 1
    private void detailsPlayer1(Card card, JLabel label) {
        try {
            if (card != null) {
                control.searchImage(card, label);
                switch (control.verifyTypeCard(card)) {
                    case POKEMON:
                        Pokemon pokemon = (Pokemon) card;
                        player1_lb_atual_nome.setText(pokemon.getName());
                        player1_lb_atual_elemento.setText(String.valueOf(pokemon.getElement()));
                        player1_lb_atual_forca.setText(String.valueOf(pokemon.getPower()));
                        player1_lb_atual_hp.setText(String.valueOf(pokemon.getHealth()));
                        player1_lb_atual_energ_atual.setText(String.valueOf(pokemon.getCurrentEnergy()));
                        player1_lb_atual_energ_req.setText(String.valueOf(pokemon.getRequiredEnergy()));
                        break;
                    case ENERGY:
                        Energy energy = (Energy) card;
                        player1_lb_atual_nome.setText(String.valueOf(energy.getType()));
                        player1_lb_atual_elemento.setText(String.valueOf(energy.getElement()));
                        player1_lb_atual_forca.setText("-");
                        player1_lb_atual_hp.setText("-");
                        player1_lb_atual_energ_atual.setText("-");
                        player1_lb_atual_energ_req.setText("-");
                        break;
                    case COACH:
                        Coach coach = (Coach) card;
                        player1_lb_atual_nome.setText(String.valueOf(coach.getType()));
                        player1_lb_atual_elemento.setText("-");
                        player1_lb_atual_forca.setText(String.valueOf(coach.getPower()));
                        player1_lb_atual_hp.setText("-");
                        player1_lb_atual_energ_atual.setText("-");
                        player1_lb_atual_energ_req.setText("-");
                        break;
                    default:
                        player1_lb_atual_nome.setText("-");
                        player1_lb_atual_elemento.setText("-");
                        player1_lb_atual_forca.setText("-");
                        player1_lb_atual_hp.setText("-");
                        player1_lb_atual_energ_atual.setText("-");
                        player1_lb_atual_energ_req.setText("-");
                        break;
                }
                label.setEnabled(card != null);
                player1_lb_atual_descricao.setText(control.descricao(card));
            } else {
                player1_lb_atual_nome.setText("-");
                player1_lb_atual_elemento.setText("-");
                player1_lb_atual_forca.setText("-");
                player1_lb_atual_hp.setText("-");
                player1_lb_atual_energ_atual.setText("-");
                player1_lb_atual_energ_req.setText("-");
                control.resetCard(player1_lb_atual_);
            }
        } catch (Exception e) {
            System.out.println("-");
        }
    }

    // detalhes do pokemon selecionado do jogador 2
    private void detailsPlayer2(Card card, JLabel label) {
        try {
            if (card != null) {
                control.searchImage(card, label);
                switch (control.verifyTypeCard(card)) {
                    case POKEMON:
                        Pokemon pokemon = (Pokemon) card;
                        player2_lb_atual_nome.setText(pokemon.getName());
                        player2_lb_atual_elemento.setText(String.valueOf(pokemon.getElement()));
                        player2_lb_atual_forca.setText(String.valueOf(pokemon.getPower()));
                        player2_lb_atual_hp.setText(String.valueOf(pokemon.getHealth()));
                        player2_lb_atual_energ_atual.setText(String.valueOf(pokemon.getCurrentEnergy()));
                        player2_lb_atual_energ_req.setText(String.valueOf(pokemon.getRequiredEnergy()));
                        break;
                    case ENERGY:
                        Energy energy = (Energy) card;
                        player2_lb_atual_nome.setText(String.valueOf(energy.getType()));
                        player2_lb_atual_elemento.setText(String.valueOf(energy.getElement()));
                        player2_lb_atual_forca.setText("-");
                        player2_lb_atual_hp.setText("-");
                        player2_lb_atual_energ_atual.setText("-");
                        player2_lb_atual_energ_req.setText("-");
                        break;
                    case COACH:
                        Coach coach = (Coach) card;
                        player2_lb_atual_nome.setText(String.valueOf(coach.getType()));
                        player2_lb_atual_elemento.setText("-");
                        player2_lb_atual_forca.setText(String.valueOf(coach.getPower()));
                        player2_lb_atual_hp.setText("-");
                        player2_lb_atual_energ_atual.setText("-");
                        player2_lb_atual_energ_req.setText("-");
                        break;
                    default:
                        player2_lb_atual_nome.setText("-");
                        player2_lb_atual_elemento.setText("-");
                        player2_lb_atual_forca.setText("-");
                        player2_lb_atual_hp.setText("-");
                        player2_lb_atual_energ_atual.setText("-");
                        player2_lb_atual_energ_req.setText("-");
                        break;
                }
                label.setEnabled(card != null);
                player2_lb_atual_descricao.setText(control.descricao(card));
            } else {
                player2_lb_atual_nome.setText("-");
                player2_lb_atual_elemento.setText("-");
                player2_lb_atual_forca.setText("-");
                player2_lb_atual_hp.setText("-");
                player2_lb_atual_energ_atual.setText("-");
                player2_lb_atual_energ_req.setText("-");
                control.resetCard(player2_lb_atual_);
            }
        } catch (Exception e) {
            System.out.println("-");
        }

    }

    // resetar o card com as configurações iniciais do jogo
    private void defaultCard(int player, JLabel label) {
        control.resetCard(label);
        if (player == 1) {
            player1_lb_atual_nome.setText("-");
            player1_lb_atual_elemento.setText("-");
            player1_lb_atual_forca.setText("-");
            player1_lb_atual_hp.setText("-");
            player1_lb_atual_energ_atual.setText("-");
            player1_lb_atual_energ_req.setText("-");
        } else {
            player2_lb_atual_nome.setText("-");
            player2_lb_atual_elemento.setText("-");
            player2_lb_atual_forca.setText("-");
            player2_lb_atual_hp.setText("-");
            player2_lb_atual_energ_atual.setText("-");
            player2_lb_atual_energ_req.setText("-");
        }
    }

    // ativa o layout do pokemon em batalha
    private void battleMode(int turn) {
        if (turn == 1) {
            if (currentPokemonPlayer1 != null) {
                player1_btn_atacar_.setEnabled(player1_lb_0_.isEnabled());
                player1_btn_usar_.setEnabled(false);
                player1_btn_suporte_.setEnabled(false);
            }
        } else {
            if (currentPokemonPlayer2 != null) {
                player2_btn_atacar_.setEnabled(player2_lb_0_.isEnabled());
                player2_btn_usar_.setEnabled(false);
                player2_btn_suporte_.setEnabled(false);
            }
        }
    }

    // verifica estado do pokemon do oponente
    private boolean verifyHPPokemon(Pokemon pokemon) {
        return pokemon.getHealth() > 0;
    }

    // verifica estado do oponente
    private boolean verifyPlayerPoints(Player player) {
        return player.getPoints() > 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        player1_nome = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        player1_lb_0_ = new javax.swing.JLabel();
        player1_lb_1_ = new javax.swing.JLabel();
        player1_lb_4_ = new javax.swing.JLabel();
        player1_lb_2_ = new javax.swing.JLabel();
        player1_lb_3_ = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        player1_deck_ = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        player1_pontos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        player1_atual_campoN = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        player1_lb_atual_ = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        player1_lb_atual_descricao = new javax.swing.JLabel();
        player1_lb_atual_energ_req = new javax.swing.JLabel();
        player1_lb_atual_energ_atual = new javax.swing.JLabel();
        player1_lb_atual_hp = new javax.swing.JLabel();
        player1_lb_atual_forca = new javax.swing.JLabel();
        player1_lb_atual_elemento = new javax.swing.JLabel();
        player1_lb_atual_nome = new javax.swing.JLabel();
        player1_btn_usar_ = new javax.swing.JButton();
        player1_btn_suporte_ = new javax.swing.JButton();
        player1_btn_atacar_ = new javax.swing.JButton();
        player1_btn_descartar_ = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        player2_pontos = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        player2_atual_campoN = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        player2_lb_atual_ = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        player2_lb_atual_nome = new javax.swing.JLabel();
        player2_lb_atual_elemento = new javax.swing.JLabel();
        player2_lb_atual_hp = new javax.swing.JLabel();
        player2_lb_atual_forca = new javax.swing.JLabel();
        player2_lb_atual_energ_atual = new javax.swing.JLabel();
        player2_lb_atual_energ_req = new javax.swing.JLabel();
        player2_lb_atual_descricao = new javax.swing.JLabel();
        player2_btn_usar_ = new javax.swing.JButton();
        player2_btn_suporte_ = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        player2_lb_0_ = new javax.swing.JLabel();
        player2_lb_1_ = new javax.swing.JLabel();
        player2_lb_4_ = new javax.swing.JLabel();
        player2_lb_2_ = new javax.swing.JLabel();
        player2_lb_3_ = new javax.swing.JLabel();
        player2_btn_atacar_ = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        player2_nome = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        player2_deck_ = new javax.swing.JLabel();
        player2_btn_descartar_ = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        player1_lb_cp_ = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        player2_lb_cp_ = new javax.swing.JLabel();
        lb_refresh_turn_ = new javax.swing.JLabel();
        lb_stop_ = new javax.swing.JLabel();
        lb_play_ = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jogador 1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player1_nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_nome.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(player1_nome)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player1_lb_0_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_0_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_0_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_0_MouseClicked(evt);
            }
        });

        player1_lb_1_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_1_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_1_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_1_MouseClicked(evt);
            }
        });

        player1_lb_4_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_4_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_4_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_4_MouseClicked(evt);
            }
        });

        player1_lb_2_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_2_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_2_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_2_MouseClicked(evt);
            }
        });

        player1_lb_3_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_3_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_3_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_3_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_lb_0_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1_lb_1_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1_lb_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(player1_lb_3_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1_lb_4_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player1_lb_0_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player1_lb_1_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player1_lb_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player1_lb_3_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player1_lb_4_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player1_deck_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_deck_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_deck_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_deck_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_deck_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_deck_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pontos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player1_pontos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_pontos.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_pontos, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(player1_pontos)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Última carta selecionada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player1_atual_campoN.setText("Nome:");

        jLabel1.setText("Elemento:");

        jLabel11.setText("HP:");

        jLabel12.setText("Força:");

        jLabel13.setText("Energia Atual:");

        jLabel14.setText("Energia Requerida:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player1_lb_atual_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_atual_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_atual_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_atual_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_lb_atual_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_lb_atual_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setText("Descrição:");

        player1_lb_atual_descricao.setText("-");
        player1_lb_atual_descricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        player1_lb_atual_energ_req.setText("-");

        player1_lb_atual_energ_atual.setText("-");

        player1_lb_atual_hp.setText("-");

        player1_lb_atual_forca.setText("-");

        player1_lb_atual_elemento.setText("-");

        player1_lb_atual_nome.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(player1_atual_campoN)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(player1_lb_atual_nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(player1_lb_atual_elemento, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(player1_lb_atual_energ_atual, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(player1_lb_atual_energ_req, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player1_lb_atual_hp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player1_lb_atual_forca, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player1_lb_atual_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player1_atual_campoN)
                            .addComponent(player1_lb_atual_nome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(player1_lb_atual_elemento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(player1_lb_atual_hp)
                            .addComponent(player1_lb_atual_forca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(player1_lb_atual_energ_atual))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(player1_lb_atual_energ_req))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addComponent(player1_lb_atual_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        player1_btn_usar_.setText("Usar");
        player1_btn_usar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_btn_usar_MouseClicked(evt);
            }
        });

        player1_btn_suporte_.setText("Suporte");
        player1_btn_suporte_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_btn_suporte_MouseClicked(evt);
            }
        });

        player1_btn_atacar_.setText("Atacar");
        player1_btn_atacar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_btn_atacar_MouseClicked(evt);
            }
        });

        player1_btn_descartar_.setText("Descartar");
        player1_btn_descartar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_btn_descartar_MouseClicked(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pontos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player2_pontos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_pontos.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_pontos, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(player2_pontos)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Última carta selecionada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player2_atual_campoN.setText("Nome:");

        jLabel19.setText("Elemento:");

        jLabel20.setText("HP:");

        jLabel21.setText("Força:");

        jLabel22.setText("Energia Atual:");

        jLabel23.setText("Energia Requerida:");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player2_lb_atual_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_atual_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_atual_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_atual_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_lb_atual_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_lb_atual_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setText("Descrição:");

        player2_lb_atual_nome.setText("-");

        player2_lb_atual_elemento.setText("-");

        player2_lb_atual_hp.setText("-");

        player2_lb_atual_forca.setText("-");

        player2_lb_atual_energ_atual.setText("-");

        player2_lb_atual_energ_req.setText("-");

        player2_lb_atual_descricao.setText("-");
        player2_lb_atual_descricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(player2_lb_atual_hp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel22))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(player2_lb_atual_energ_atual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(player2_lb_atual_energ_req, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 11, Short.MAX_VALUE)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(player2_lb_atual_forca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(player2_atual_campoN)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(player2_lb_atual_nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(player2_lb_atual_elemento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player2_lb_atual_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player2_atual_campoN)
                            .addComponent(player2_lb_atual_nome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(player2_lb_atual_elemento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(player2_lb_atual_hp)
                            .addComponent(player2_lb_atual_forca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(player2_lb_atual_energ_atual))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(player2_lb_atual_energ_req))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addComponent(player2_lb_atual_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        player2_btn_usar_.setText("Usar");
        player2_btn_usar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_btn_usar_MouseClicked(evt);
            }
        });

        player2_btn_suporte_.setText("Suporte");
        player2_btn_suporte_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_btn_suporte_MouseClicked(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player2_lb_0_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_0_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_0_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_0_MouseClicked(evt);
            }
        });

        player2_lb_1_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_1_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_1_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_1_MouseClicked(evt);
            }
        });

        player2_lb_4_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_4_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_4_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_4_MouseClicked(evt);
            }
        });

        player2_lb_2_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_2_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_2_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_2_MouseClicked(evt);
            }
        });

        player2_lb_3_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_3_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_3_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_3_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_lb_0_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2_lb_1_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2_lb_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(player2_lb_3_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2_lb_4_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player2_lb_0_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player2_lb_1_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player2_lb_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player2_lb_3_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player2_lb_4_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        player2_btn_atacar_.setText("Atacar");
        player2_btn_atacar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_btn_atacar_MouseClicked(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jogador 2", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        player2_nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_nome.setText("-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(player2_nome)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player2_deck_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_deck_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_deck_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_deck_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_deck_, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_deck_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        player2_btn_descartar_.setText("Descartar");
        player2_btn_descartar_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_btn_descartar_MouseClicked(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player1_lb_cp_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1_lb_cp_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player1_lb_cp_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player1_lb_cp_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_lb_cp_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1_lb_cp_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        player2_lb_cp_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2_lb_cp_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        player2_lb_cp_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                player2_lb_cp_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_lb_cp_, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2_lb_cp_, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_refresh_turn_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_refresh_turn_.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lb_refresh_turn_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_refresh_turn_MouseClicked(evt);
            }
        });

        lb_stop_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_stop_MouseClicked(evt);
            }
        });

        lb_play_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_play_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(player2_btn_usar_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player2_btn_suporte_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player2_btn_atacar_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player2_btn_descartar_, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(player1_btn_usar_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player1_btn_suporte_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player1_btn_atacar_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(player1_btn_descartar_, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lb_refresh_turn_, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_play_, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_stop_, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player1_btn_usar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player1_btn_suporte_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player1_btn_atacar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player1_btn_descartar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(112, 112, 112)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(lb_refresh_turn_, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player2_btn_usar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2_btn_suporte_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2_btn_atacar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2_btn_descartar_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_stop_, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_play_, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void player1_btn_usar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_btn_usar_MouseClicked
        if (control.verifyTypeCard(cardsPlayer1.get(index)) == TypeCard.POKEMON) {
            if (player1_btn_usar_.getText().equals("Usar")) {
                currentPokemonPlayer1 = (Pokemon) cardsPlayer1.get(index);
                control.usePokemon(cardsPlayer1, labelsPlayer1, index, player1_lb_cp_);
                layoutTurnPlayer2();
                player1_btn_usar_.setText("Substituir");
            } else {
                currentPokemonPlayer1 = changePokemon(cardsPlayer1, labelsPlayer1, currentPokemonPlayer1, player1_lb_cp_);
                layoutTurnPlayer1();
            }
        }
    }//GEN-LAST:event_player1_btn_usar_MouseClicked

    private void player2_btn_usar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_btn_usar_MouseClicked
        if (control.verifyTypeCard(cardsPlayer2.get(index)) == TypeCard.POKEMON) {
            if (player2_btn_usar_.getText().equals("Usar")) {
                currentPokemonPlayer2 = (Pokemon) cardsPlayer2.get(index);
                control.usePokemon(cardsPlayer2, labelsPlayer2, index, player2_lb_cp_);
                layoutTurnPlayer1();
                player2_btn_usar_.setText("Substituir");
            } else {
                currentPokemonPlayer2 = changePokemon(cardsPlayer2, labelsPlayer2, currentPokemonPlayer2, player2_lb_cp_);
                layoutTurnPlayer2();
            }
        }
    }//GEN-LAST:event_player2_btn_usar_MouseClicked

    private void player1_btn_suporte_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_btn_suporte_MouseClicked
        if (control.support(cardsPlayer1, labelsPlayer1, currentPokemonPlayer1, index)) {
            defaultCard(1, player1_lb_atual_);
            layoutTurnPlayer2();
        }
    }//GEN-LAST:event_player1_btn_suporte_MouseClicked

    private void player2_btn_suporte_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_btn_suporte_MouseClicked
        if (control.support(cardsPlayer2, labelsPlayer2, currentPokemonPlayer2, index)) {
            defaultCard(2, player2_lb_atual_);
            layoutTurnPlayer1();
        }
    }//GEN-LAST:event_player2_btn_suporte_MouseClicked

    private void player1_btn_atacar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_btn_atacar_MouseClicked
        if (control.verifyAttack(currentPokemonPlayer1)) {
            switch (control.attackPokemon(currentPokemonPlayer1, currentPokemonPlayer2, player2)) {
                case 0:
                    JOptionPane.showMessageDialog(null, currentPokemonPlayer1 + " atacou " + currentPokemonPlayer2);
                    if (!verifyHPPokemon(currentPokemonPlayer2)) {
                        JOptionPane.showMessageDialog(null, currentPokemonPlayer2 + " foi derrotado!");
                        currentPokemonPlayer2 = null;
                        control.resetCard(player2_lb_atual_);
                    }
                    layoutTurnPlayer2();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, currentPokemonPlayer1 + " atacou os pontos de vida do jogador 2");
                    player2_pontos.setText(String.valueOf(player2.getPoints()));
                    if (!verifyPlayerPoints(player2)) {
                        JOptionPane.showMessageDialog(null, player2.getName() + " foi derrotado!");
                        Finish frame = new Finish(player1);
                        frame.setVisible(true);
                        this.dispose();
                    }
                    layoutTurnPlayer2();
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pokemon sem energia para atacar");
        }
    }//GEN-LAST:event_player1_btn_atacar_MouseClicked

    private void player2_btn_atacar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_btn_atacar_MouseClicked
        if (control.verifyAttack(currentPokemonPlayer2)) {
            switch (control.attackPokemon(currentPokemonPlayer2, currentPokemonPlayer1, player1)) {
                case 0:
                    JOptionPane.showMessageDialog(null, currentPokemonPlayer2 + " atacou " + currentPokemonPlayer1);
                    if (!verifyHPPokemon(currentPokemonPlayer1)) {
                        JOptionPane.showMessageDialog(null, currentPokemonPlayer1 + " foi derrotado!");
                        currentPokemonPlayer1 = null;
                        control.resetCard(player1_lb_atual_);
                    }
                    layoutTurnPlayer1();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, currentPokemonPlayer2 + " atacou os pontos de vida do jogador 1");
                    player1_pontos.setText(String.valueOf(player1.getPoints()));
                    if (!verifyPlayerPoints(player1)) {
                        JOptionPane.showMessageDialog(rootPane, player1.getName() + " foi derrotado!");
                        Finish frame = new Finish(player2);
                        frame.setVisible(true);
                        this.dispose();
                    }
                    layoutTurnPlayer1();
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pokemon sem energia para atacar");
        }
    }//GEN-LAST:event_player2_btn_atacar_MouseClicked

    private void player1_btn_descartar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_btn_descartar_MouseClicked
        try {
            if (!cardsPlayer1.isEmpty()) {
                control.discard(labelsPlayer1, cardsPlayer1, index, cardsPlayer1.get(index));
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer2(cardsPlayer1.get(index), player1_lb_atual_);
                layoutTurnPlayer1();
            }
        } catch (Exception e) {
            defaultCard(1, player1_lb_atual_);
            System.out.println("-");
        }
    }//GEN-LAST:event_player1_btn_descartar_MouseClicked

    private void player2_btn_descartar_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_btn_descartar_MouseClicked
        try {
            if (!cardsPlayer2.isEmpty()) {
                control.discard(labelsPlayer2, cardsPlayer2, index, cardsPlayer2.get(index));
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                layoutTurnPlayer2();
            }
        } catch (Exception e) {
            defaultCard(2, player2_lb_atual_);
            System.out.println("-");
        }
    }//GEN-LAST:event_player2_btn_descartar_MouseClicked

    private void player1_deck_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_deck_MouseClicked
        if (cont < 1) {
            if (control.supply(cardsPlayer1, labelsPlayer1, player1)) {
                layoutTurnPlayer1();
                cont++;
            } else {
                JOptionPane.showMessageDialog(null, "Mão já preenchida!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você só pode comprar 1 vez por turno!");
        }

    }//GEN-LAST:event_player1_deck_MouseClicked

    private void player2_deck_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_deck_MouseClicked
        if (cont < 1) {
            if (control.supply(cardsPlayer2, labelsPlayer2, player2)) {
                layoutTurnPlayer2();
                cont++;
            } else {
                JOptionPane.showMessageDialog(null, "Mão já preenchida!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você só pode comprar 1 vez por turno!");
        }
    }//GEN-LAST:event_player2_deck_MouseClicked

    private void player1_lb_0_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_0_MouseClicked
        if (player1_lb_0_.isEnabled()) {
            index = 0;
            try {
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer1(cardsPlayer1.get(index), player1_lb_atual_);
                managerButtons(1, cardsPlayer1.get(index).getType());
            } catch (Exception e) {
                defaultCard(1, player1_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player1_lb_0_MouseClicked

    private void player2_lb_0_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_0_MouseClicked
        if (player2_lb_0_.isEnabled()) {
            index = 0;
            try {
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                managerButtons(2, cardsPlayer2.get(index).getType());
            } catch (Exception e) {
                defaultCard(2, player2_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player2_lb_0_MouseClicked

    private void player1_lb_1_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_1_MouseClicked
        if (player1_lb_1_.isEnabled()) {
            index = 1;
            try {
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer1(cardsPlayer1.get(index), player1_lb_atual_);
                managerButtons(1, cardsPlayer1.get(index).getType());
            } catch (Exception e) {
                defaultCard(1, player1_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player1_lb_1_MouseClicked

    private void player2_lb_1_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_1_MouseClicked
        if (player2_lb_1_.isEnabled()) {
            index = 1;
            try {
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                managerButtons(2, cardsPlayer2.get(index).getType());
            } catch (Exception e) {
                defaultCard(2, player2_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player2_lb_1_MouseClicked

    private void player1_lb_2_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_2_MouseClicked
        if (player1_lb_2_.isEnabled()) {
            index = 2;
            try {
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer1(cardsPlayer1.get(index), player1_lb_atual_);
                managerButtons(1, cardsPlayer1.get(index).getType());
            } catch (Exception e) {
                defaultCard(1, player1_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player1_lb_2_MouseClicked

    private void player2_lb_2_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_2_MouseClicked
        if (player2_lb_2_.isEnabled()) {
            index = 2;
            try {
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                managerButtons(2, cardsPlayer2.get(index).getType());
            } catch (Exception e) {
                defaultCard(2, player2_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player2_lb_2_MouseClicked

    private void player1_lb_3_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_3_MouseClicked
        if (player1_lb_3_.isEnabled()) {
            index = 3;
            try {
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer1(cardsPlayer1.get(index), player1_lb_atual_);
                managerButtons(1, cardsPlayer1.get(index).getType());
            } catch (Exception e) {
                defaultCard(1, player1_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player1_lb_3_MouseClicked

    private void player2_lb_3_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_3_MouseClicked
        if (player2_lb_3_.isEnabled()) {
            index = 3;
            try {
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                managerButtons(2, cardsPlayer2.get(index).getType());
            } catch (Exception e) {
                defaultCard(2, player2_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player2_lb_3_MouseClicked

    private void player1_lb_4_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_4_MouseClicked
        if (player1_lb_4_.isEnabled()) {
            index = 4;
            try {
                control.selectCard(labelsPlayer1, cardsPlayer1, index);
                detailsPlayer1(cardsPlayer1.get(index), player1_lb_atual_);
                managerButtons(1, cardsPlayer1.get(index).getType());
            } catch (Exception e) {
                defaultCard(1, player1_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player1_lb_4_MouseClicked

    private void player2_lb_4_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_4_MouseClicked
        if (player2_lb_4_.isEnabled()) {
            index = 4;
            try {
                control.selectCard(labelsPlayer2, cardsPlayer2, index);
                detailsPlayer2(cardsPlayer2.get(index), player2_lb_atual_);
                managerButtons(2, cardsPlayer2.get(index).getType());
            } catch (Exception e) {
                defaultCard(2, player2_lb_atual_);
                System.out.println("-");
            }
        }
    }//GEN-LAST:event_player2_lb_4_MouseClicked

    private void player1_lb_cp_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_cp_MouseClicked
        if (player1_lb_cp_.isEnabled()) {
            if (player1_lb_cp_.isEnabled()) {
                try {
                    System.out.println(currentPokemonPlayer1.getName());
                    detailsPlayer1(currentPokemonPlayer1, player1_lb_atual_);
                    battleMode(1);
                } catch (Exception e) {
                    defaultCard(1, player1_lb_atual_);
                    System.out.println("-");
                }
            }
        }
    }//GEN-LAST:event_player1_lb_cp_MouseClicked

    private void player2_lb_cp_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_cp_MouseClicked
        if (player2_lb_cp_.isEnabled()) {
            if (player2_lb_cp_.isEnabled()) {
                try {
                    System.out.println(currentPokemonPlayer2.getName());
                    detailsPlayer2(currentPokemonPlayer2, player2_lb_atual_);
                    battleMode(2);
                } catch (Exception e) {
                    defaultCard(2, player2_lb_atual_);
                    System.out.println("-");
                }
            }
        }
    }//GEN-LAST:event_player2_lb_cp_MouseClicked

    private void player1_lb_atual_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1_lb_atual_MouseClicked

    }//GEN-LAST:event_player1_lb_atual_MouseClicked

    private void player2_lb_atual_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2_lb_atual_MouseClicked

    }//GEN-LAST:event_player2_lb_atual_MouseClicked

    private void lb_refresh_turn_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_refresh_turn_MouseClicked
        if (lb_refresh_turn_.isEnabled()) {
            if (vez % 2 == 0) {
                layoutTurnPlayer2();
                vez++;
            } else {
                layoutTurnPlayer1();
                vez++;
            }
        }
    }//GEN-LAST:event_lb_refresh_turn_MouseClicked

    private void lb_play_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_play_MouseClicked
        play();
    }//GEN-LAST:event_lb_play_MouseClicked

    private void lb_stop_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_stop_MouseClicked
        stop();
    }//GEN-LAST:event_lb_stop_MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lb_play_;
    private javax.swing.JLabel lb_refresh_turn_;
    private javax.swing.JLabel lb_stop_;
    private javax.swing.JLabel player1_atual_campoN;
    private javax.swing.JButton player1_btn_atacar_;
    private javax.swing.JButton player1_btn_descartar_;
    private javax.swing.JButton player1_btn_suporte_;
    private javax.swing.JButton player1_btn_usar_;
    private javax.swing.JLabel player1_deck_;
    private javax.swing.JLabel player1_lb_0_;
    private javax.swing.JLabel player1_lb_1_;
    private javax.swing.JLabel player1_lb_2_;
    private javax.swing.JLabel player1_lb_3_;
    private javax.swing.JLabel player1_lb_4_;
    private javax.swing.JLabel player1_lb_atual_;
    private javax.swing.JLabel player1_lb_atual_descricao;
    private javax.swing.JLabel player1_lb_atual_elemento;
    private javax.swing.JLabel player1_lb_atual_energ_atual;
    private javax.swing.JLabel player1_lb_atual_energ_req;
    private javax.swing.JLabel player1_lb_atual_forca;
    private javax.swing.JLabel player1_lb_atual_hp;
    private javax.swing.JLabel player1_lb_atual_nome;
    private javax.swing.JLabel player1_lb_cp_;
    private javax.swing.JLabel player1_nome;
    private javax.swing.JLabel player1_pontos;
    private javax.swing.JLabel player2_atual_campoN;
    private javax.swing.JButton player2_btn_atacar_;
    private javax.swing.JButton player2_btn_descartar_;
    private javax.swing.JButton player2_btn_suporte_;
    private javax.swing.JButton player2_btn_usar_;
    private javax.swing.JLabel player2_deck_;
    private javax.swing.JLabel player2_lb_0_;
    private javax.swing.JLabel player2_lb_1_;
    private javax.swing.JLabel player2_lb_2_;
    private javax.swing.JLabel player2_lb_3_;
    private javax.swing.JLabel player2_lb_4_;
    private javax.swing.JLabel player2_lb_atual_;
    private javax.swing.JLabel player2_lb_atual_descricao;
    private javax.swing.JLabel player2_lb_atual_elemento;
    private javax.swing.JLabel player2_lb_atual_energ_atual;
    private javax.swing.JLabel player2_lb_atual_energ_req;
    private javax.swing.JLabel player2_lb_atual_forca;
    private javax.swing.JLabel player2_lb_atual_hp;
    private javax.swing.JLabel player2_lb_atual_nome;
    private javax.swing.JLabel player2_lb_cp_;
    private javax.swing.JLabel player2_nome;
    private javax.swing.JLabel player2_pontos;
    // End of variables declaration//GEN-END:variables
}
