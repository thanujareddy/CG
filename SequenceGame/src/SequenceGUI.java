import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class SequenceGUI extends javax.swing.JFrame {

    /**
     * Creates new form SequenceGUI
     */
    public static boolean help_button = false;
    public static boolean won;
    boolean done = false;
    ArrayList<Card> p1 =new ArrayList<Card>();
    ArrayList<Card> p2 =new ArrayList<Card>();
    ArrayList<Card> p3 =new ArrayList<Card>();
    ArrayList<Card> p4 =new ArrayList<Card>();
    Card[][] board = new Card[10][10];
    JLabel[][] boardLabel = new JLabel[10][10];
    String[] names;
    int count = 27;
    int noOfPlayers=0;
    int turn = 1;
    Deck d= new Deck();
    public SequenceGUI() {
        initComponents();
        MainMenu m = new MainMenu();
        names = m.getNames();
        for(int i=0;i<4 && names[i]!=null;i++){
            noOfPlayers++;
        }
        for(int i=0;i<4;i++){
            System.out.println(names[i]);
        }
        for(int i=0;i<4;i++){
            if(names[i]==null || (names[i].equals(" "))){
                switch(i){
                    case 0: names[0] = "John";jLabel31.setText("John");break;
                    case 1: names[1] = "Tom";jLabel137.setText("Tom");break;
                    case 2: names[2] = "Matt";jLabel136.setText("Matt");break;
                    case 3: names[3] = "Tony";jLabel138.setText("Tony");break;
                }
            }
        }
        for(int i=0;i<4;i++){
            System.out.println(names[i]);
        }
        switch(noOfPlayers){
            case 4: jLabel138.setText(names[3]);
                    jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/human.png")));
            case 3: jLabel136.setText(names[2]);
                    jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/human.png")));
            case 2: jLabel137.setText(names[1]);
                    jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/human.png")));
            case 1: jLabel31.setText(names[0]);
                    jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/human.png")));
                    break;   
        }
        switch(noOfPlayers + 1){
            case 1: jLabel31.setText("John");
                    jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png")));
                    //names[0] = "John";
            case 2: jLabel137.setText("Matt");
                    jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png")));
                    //names[2] = "Matt";
            case 3: jLabel136.setText("Tom");
                    jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png")));
                    //names[1] = "Tom";
            case 4: jLabel138.setText("Tony");
                    jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png")));
                    //names[3] = "Tony";
                    break;   
        }
        
        jLabel140.setText(names[0] + " turn!");
        d.shuffle();
        distributeCards();
        createBoard();
        addListeners(this);
        playerTurn(1);
        
    }
    public void playerTurn(int turn){
        
        if(!jLabel33.getIcon().toString().equals(jLabel32.getIcon().toString()) && turn == 2){
            jLabel140.setText(names[turn-1] + " turn.");
            placeChip(1, p3.get(0));
            count++;
            if(count == 52){
                count = 0;
            }
            //jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(count).getImage())));
            p3.set(0, d.deck.get(count));
            turn = 3;
        }
        if(!jLabel35.getIcon().toString().equals(jLabel32.getIcon().toString()) && turn == 3){
            jLabel140.setText(names[turn-1] + " turn.");
            placeChip(2, p2.get(0));
            count++;
            if(count == 52){
                count = 0;
            }
            p2.set(0, d.deck.get(count));
            turn =4;
        }
        if(!jLabel34.getIcon().toString().equals(jLabel32.getIcon().toString()) && turn == 4){
            jLabel140.setText(names[turn-1] + " turn.");
            placeChip(3, p4.get(0));
            count++;
            if(count == 52){
                count = 0;
            }
            //jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(count).getImage())));
            p4.set(0, d.deck.get(count));
            turn = 1;
        }
        jLabel140.setText(names[turn-1] + " turn.");
    }
    public void createBoard(){
        int val =0;
        for(int i=0;i<4;i++){
            for(int j=0;j<i+1;j++){
                board[i + 6][4-j]= new Card(0,val);
                board[3-i][4-j] = new Card(1, val);
                
                board[i + 6][j + 5]= new Card(1,val);
                board[3 - i][j + 5] = new Card(0, val);
                
                board[j + 5][3-i]= new Card(2,val);
                board[4-j][3 - i] = new Card(3, val);
                
                board[j + 5][i + 6]= new Card(3,val);
                board[4 - j][i + 6] = new Card(2, val);
                val++;
            }
        }
        board[1][1] = board[8][8] = new Card(3, 12);
        board[2][2] = board[7][7] = new Card(3, 11);
        board[3][3] = board[6][6] = new Card(1, 11);
        board[4][4] = board[5][5] = new Card(1, 12);
        board[1][8] = board[8][1] = new Card(2, 12);
        board[2][7] = board[7][2] = new Card(2, 11);
        board[3][6] = board[6][3] = new Card(0, 11);
        board[4][5] = board[5][4] = new Card(0, 12);
        for(int i=0, h = 0;i<10;i++, h+=30){
            for(int j=0,w=0;j<10;j++, w+=40){
                if(!(i==0 && j==0) && !(i==9 && j==9) && !(i==9 && j==0) && !(i==0 && j==9)){
                    boardLabel[i][j]= new JLabel();
                    boardLabel[i][j].setBounds(w, h, 40, 30);
                    jLabel1.add(boardLabel[i][j]);
                }
            }
        }
    }
    public void distributeCards(){
        switch(noOfPlayers){
            case 4: jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(21).getImage())));
                    jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(22).getImage())));
                    jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(23).getImage())));
                    jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(24).getImage())));
                    jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(25).getImage())));
                    jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(26).getImage())));
                    jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(27).getImage())));
            case 3: jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(14).getImage())));
                    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(15).getImage())));
                    jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(16).getImage())));
                    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(17).getImage())));
                    jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(18).getImage())));
                    jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(19).getImage())));
                    jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(20).getImage())));
            case 2: jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(7).getImage())));
                    jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(8).getImage())));
                    jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(9).getImage())));
                    jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(10).getImage())));
                    jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(11).getImage())));
                    jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(12).getImage())));
                    jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(13).getImage())));
            case 1: jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(0).getImage())));
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(1).getImage())));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(2).getImage())));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(3).getImage())));
                    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(4).getImage())));
                    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(5).getImage())));
                    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(6).getImage())));
                    break;
        }
        
        p1.add(d.deck.get(0));
        p1.add(d.deck.get(1));
        p1.add(d.deck.get(2));
        p1.add(d.deck.get(3));
        p1.add(d.deck.get(4));
        p1.add(d.deck.get(5));
        p1.add(d.deck.get(6));
        p2.add(d.deck.get(7));
        p2.add(d.deck.get(8));
        p2.add(d.deck.get(9));
        p2.add(d.deck.get(10));
        p2.add(d.deck.get(11));
        p2.add(d.deck.get(12));
        p2.add(d.deck.get(13));
        p3.add(d.deck.get(14));
        p3.add(d.deck.get(15));
        p3.add(d.deck.get(16));
        p3.add(d.deck.get(17));
        p3.add(d.deck.get(18));
        p3.add(d.deck.get(19));
        p3.add(d.deck.get(20));
        p4.add(d.deck.get(21));
        p4.add(d.deck.get(22));
        p4.add(d.deck.get(23));
        p4.add(d.deck.get(24));
        p4.add(d.deck.get(25));
        p4.add(d.deck.get(26));
        p4.add(d.deck.get(27));
    }
    
        public boolean placeChip(int player, Card c){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if((board[i][j]!=null) && ((board[i][j].toString()).equals(c.toString()))){
                    if((boardLabel[i][j].getIcon()==null)){
                       if(player == 0 || player == 2)
                            boardLabel[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip.png")));
                       else
                           boardLabel[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip_blue.png")));
                       checkSequence();
                       return true;
                    }               
                }               
            }
        }
        return false;
    }
    
    public void checkSequence(){
        int seq1 = 1, seq2 = 1, seq3 = 1;
        int seq1Count = 0, seq2Count=0;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(board[i][j]!=null){
                    if((boardLabel[i][j].getIcon()!=null)){
                        seq1 = 1;seq2 = 1;seq3 =1;
                        for(int k = 0; k < 4; k++){
                            if((j+k <8) && (boardLabel[i][j + k].getIcon()!=null && boardLabel[i][j+k+1].getIcon()!=null)){
                                if(boardLabel[i][j+k].getIcon().toString().equals(boardLabel[i][j+k+1].getIcon().toString()))
                                    seq1++;
                                if(seq1==5){
                                    if((boardLabel[i][j].getIcon().toString().equals(jLabel36.getIcon().toString())))
                                        seq1Count++;
                                    else
                                        seq2Count++;
                                }
                            } if((i+k <8) && (boardLabel[i + k][j].getIcon()!=null && boardLabel[i+k+1][j].getIcon()!=null)){
                                if(boardLabel[i+k][j].getIcon().toString().equals(boardLabel[i+k+1][j].getIcon().toString()))
                                    seq2++;
                                if(seq2==5){
                                    if((boardLabel[i][j].getIcon().toString().equals(jLabel36.getIcon().toString())))
                                        seq1Count++;
                                    else
                                        seq2Count++;
                                }
                            }if((j+k <8) && (i+k < 8) && (boardLabel[i + k][j + k].getIcon()!=null && boardLabel[i + k + 1][j+k+1].getIcon()!=null)){
                                if(boardLabel[i+k][j+k].getIcon().toString().equals(boardLabel[i+k+1][j+k+1].getIcon().toString()))
                                    seq3++;
                                if(seq3==5){
                                    seq1Count++;if((boardLabel[i][j].getIcon().toString().equals(jLabel36.getIcon().toString())))
                                        seq1Count++;
                                    else
                                        seq2Count++;
                                }
                            }
                        }
                    }        
                }               
            }
        }
        if(seq1Count == 2 && !done){
            done = true;
            this.setVisible(false);
            won = true;
            WinnerPage obj = new WinnerPage();
                obj.setVisible(true);
                
        }
        else if(seq2Count == 2 && !done){
            done = true;
            this.setVisible(false);
            won = false;
            WinnerPage obj = new WinnerPage();
                obj.setVisible(true);
        }
    }
    
    public boolean getWin(){
        return won;
    }
    
    public void addListeners(Container comp)
    {
        Component[] components = comp.getComponents();
        for (Component component : components)
        {
            if (component instanceof Container)
            {
                Component[] child = ((Container)component).getComponents();
                if (child != null && child.length > 0)
                {
                    addListeners((Container)component);
                }
            }
            component.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent evt)
                {
                    boolean placed = false;
                    JLabel a = new JLabel();
                    ArrayList<Card> b = new ArrayList<Card>();
                    int index = 0;
                    if(evt.getSource() == jLabel3){
                        placed = placeChip(0, p1.get(0));
                        a = jLabel3;
                        b = p1;
                        index = 0;
                        turn = 2;
                    }else if(evt.getSource() == jLabel4){
                        placed = placeChip(0, p1.get(1));
                        a = jLabel4;
                        b = p1;
                        index = 1;
                        turn = 2;
                    }else if(evt.getSource() == jLabel5){
                        placed = placeChip(0, p1.get(2));
                        a = jLabel5;
                        b = p1;
                        index = 2;
                        turn = 2;
                    }else if(evt.getSource() == jLabel6){
                        placed = placeChip(0, p1.get(3));
                        a = jLabel6;
                        b = p1;
                        index = 3;
                        turn = 2;
                    }else if(evt.getSource() == jLabel7){
                        placed = placeChip(0, p1.get(4));
                        a = jLabel7;
                        b = p1;
                        index = 4;
                        turn = 2;
                    }else if(evt.getSource() == jLabel8){
                        placed = placeChip(0, p1.get(5));
                        a = jLabel8;
                        b = p1;
                        index = 5;
                        turn = 2;
                    }else if(evt.getSource() == jLabel9){
                        placed = placeChip(0, p1.get(6));
                        a = jLabel9;
                        b = p1;
                        index = 6;
                        turn = 2;
                    }else if(evt.getSource() == jLabel10){
                        placed = placeChip(1, p3.get(0));
                        a = jLabel10;
                        b = p3;
                        index = 0;
                        turn = 3;
                    }else if(evt.getSource() == jLabel11){
                        placed = placeChip(1, p3.get(1));
                        a = jLabel11;
                        b = p3;
                        index = 1;
                        turn = 3;
                    }
                    else if(evt.getSource() == jLabel12){
                        placed = placeChip(1, p3.get(2));
                        a = jLabel12;
                        b = p3;
                        index = 2;
                        turn = 3;
                    }
                    else if(evt.getSource() == jLabel13){
                        placed = placeChip(1, p3.get(3));
                        a = jLabel13;
                        b = p3;
                        index = 3;
                        turn = 3;
                    }
                    else if(evt.getSource() == jLabel14){
                        placed = placeChip(1, p3.get(4));
                        a = jLabel14;
                        b = p3;
                        index = 4;
                        turn = 3;
                    }else if(evt.getSource() == jLabel15){
                        placed = placeChip(1, p3.get(5));
                        a = jLabel15;
                        b = p3;
                        index = 5;
                        turn = 3;
                    }else if(evt.getSource() == jLabel16){
                        placed = placeChip(1, p3.get(6));
                        a = jLabel16;
                        b = p3;
                        index = 6;
                        turn = 3;
                    }else if(evt.getSource() == jLabel17){
                        placed = placeChip(3, p4.get(0));
                        a = jLabel17;
                        b = p4;
                        index = 0;
                        turn = 1;
                    }
                    else if(evt.getSource() == jLabel18){
                        placed = placeChip(3, p4.get(1));
                        a = jLabel18;
                        b = p4;
                        index = 1;
                        turn = 1;
                    }
                    else if(evt.getSource() == jLabel19){
                        placed = placeChip(3, p4.get(2));
                        a = jLabel19;
                        b = p4;
                        index = 2;
                        turn = 1;
                    }else if(evt.getSource() == jLabel20){
                        placed = placeChip(3, p4.get(3));
                        a = jLabel20;
                        b = p4;
                        index = 3;
                        turn = 1;
                    }else if(evt.getSource() == jLabel21){
                        placed = placeChip(3, p4.get(4));
                        a = jLabel21;
                        b = p4;
                        index = 4;
                        turn = 1;
                    }else if(evt.getSource() == jLabel22){
                        placed = placeChip(3, p4.get(5));
                        a = jLabel22;
                        b = p4;
                        index = 5;
                        turn = 1;
                    }else if(evt.getSource() == jLabel23){
                        placed = placeChip(3, p4.get(6));
                        a = jLabel23;
                        b = p4;
                        index = 6;
                        turn = 1;
                    }else if(evt.getSource() == jLabel24){
                        placed = placeChip(2, p2.get(0));
                        a = jLabel24;
                        b = p2;
                        index = 0;
                        turn = 4;
                    }else if(evt.getSource() == jLabel25){
                        placed = placeChip(2, p2.get(1));
                        a = jLabel25;
                        b = p2;
                        index = 1;
                        turn = 4;
                    }else if(evt.getSource() == jLabel26){
                        placed = placeChip(2, p2.get(2));
                        a = jLabel26;
                        b = p2;
                        index = 2;
                        turn = 4;
                    }else if(evt.getSource() == jLabel27){
                        placed = placeChip(2, p2.get(3));
                        a = jLabel27;
                        b = p2;
                        index = 3;
                        turn = 4;
                    }else if(evt.getSource() == jLabel28){
                        placed = placeChip(2, p2.get(4));
                        a = jLabel28;
                        b = p2;
                        index = 4;
                        turn = 4;
                    }else if(evt.getSource() == jLabel29){
                        placed = placeChip(2, p2.get(5));
                        a = jLabel29;
                        b = p2;
                        index = 5;
                        turn = 4;
                    }else if(evt.getSource() == jLabel30){
                        placed = placeChip(2, p2.get(6));
                        a = jLabel30;
                        b = p2;
                        index = 6;
                        turn = 4;
                    }else{
                        placed = false;
                    }
                    if(placed){
                        count++;
                        if(count == 52){
                            count = 0;
                        }
                        a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + d.deck.get(count).getImage())));
                        b.set(index, d.deck.get(count));
                    }else{
                        System.out.println("Select another card");
                    } 
                
                    playerTurn(turn);
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6Diamonds.png"))); // NOI18N
        jLayeredPane1.add(jLabel9);
        jLabel9.setBounds(400, 490, 70, 90);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/9Clubs.png"))); // NOI18N
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(370, 490, 70, 90);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/8Diamonds.png"))); // NOI18N
        jLayeredPane1.add(jLabel7);
        jLabel7.setBounds(340, 490, 70, 90);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/9Spades.png"))); // NOI18N
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(310, 490, 70, 90);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/JackClubs.png"))); // NOI18N
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(280, 490, 70, 90);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AceDiamonds.png"))); // NOI18N
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(250, 490, 70, 90);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KingHearts.png"))); // NOI18N
        jLabel3.setName(""); // NOI18N
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(220, 490, 70, 90);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel16);
        jLabel16.setBounds(540, 320, 70, 90);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel15);
        jLabel15.setBounds(540, 300, 70, 90);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel14);
        jLabel14.setBounds(540, 280, 70, 90);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel13);
        jLabel13.setBounds(540, 260, 70, 90);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel12);
        jLabel12.setBounds(540, 240, 70, 90);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel11);
        jLabel11.setBounds(540, 220, 70, 90);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(540, 200, 70, 90);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel23);
        jLabel23.setBounds(10, 320, 70, 90);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel22);
        jLabel22.setBounds(10, 300, 70, 90);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel21);
        jLabel21.setBounds(10, 280, 70, 90);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel20);
        jLabel20.setBounds(10, 260, 70, 90);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel19);
        jLabel19.setBounds(10, 240, 70, 90);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel18);
        jLabel18.setBounds(10, 220, 70, 90);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel17);
        jLabel17.setBounds(10, 200, 70, 90);

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel30);
        jLabel30.setBounds(390, 10, 70, 90);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b2fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel29);
        jLabel29.setBounds(360, 10, 70, 90);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel28);
        jLabel28.setBounds(330, 10, 70, 90);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b2fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel27);
        jLabel27.setBounds(300, 10, 70, 90);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b2fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel26);
        jLabel26.setBounds(270, 10, 70, 90);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel25);
        jLabel25.setBounds(240, 10, 70, 90);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1fv.png"))); // NOI18N
        jLayeredPane1.add(jLabel24);
        jLabel24.setBounds(210, 10, 70, 90);

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jLabel31);
        jLabel31.setBounds(110, 540, 90, 20);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/human.png"))); // NOI18N
        jLayeredPane1.add(jLabel32);
        jLabel32.setBounds(120, 500, 40, 40);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png"))); // NOI18N
        jLayeredPane1.add(jLabel33);
        jLabel33.setBounds(550, 110, 50, 60);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png"))); // NOI18N
        jLayeredPane1.add(jLabel34);
        jLabel34.setBounds(20, 110, 60, 60);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ai.png"))); // NOI18N
        jLayeredPane1.add(jLabel35);
        jLabel35.setBounds(140, 10, 60, 60);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip.png"))); // NOI18N
        jLayeredPane1.add(jLabel36);
        jLabel36.setBounds(170, 500, 30, 40);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip_blue.png"))); // NOI18N
        jLayeredPane1.add(jLabel37);
        jLabel37.setBounds(520, 130, 22, 30);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip.png"))); // NOI18N
        jLayeredPane1.add(jLabel38);
        jLabel38.setBounds(110, 20, 22, 30);

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip_blue.png"))); // NOI18N
        jLayeredPane1.add(jLabel39);
        jLabel39.setBounds(90, 124, 22, 30);

        jLabel139.setText("jLabel139");
        jLayeredPane1.add(jLabel139);
        jLabel139.setBounds(220, 476, 55, 90);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help-icon-3.png"))); // NOI18N
        jButton1.setToolTipText("Instructions");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton1);
        jButton1.setBounds(530, 0, 40, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close-2-icon.png"))); // NOI18N
        jButton2.setToolTipText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2);
        jButton2.setBounds(580, 0, 40, 41);
        jLayeredPane1.add(jLabel40);
        jLabel40.setBounds(160, 140, 40, 30);
        jLayeredPane1.add(jLabel41);
        jLabel41.setBounds(200, 144, 40, 30);
        jLayeredPane1.add(jLabel42);
        jLabel42.setBounds(240, 140, 30, 30);
        jLayeredPane1.add(jLabel43);
        jLabel43.setBounds(280, 140, 30, 30);
        jLayeredPane1.add(jLabel44);
        jLabel44.setBounds(310, 140, 40, 30);
        jLayeredPane1.add(jLabel45);
        jLabel45.setBounds(350, 140, 30, 30);
        jLayeredPane1.add(jLabel46);
        jLabel46.setBounds(390, 140, 30, 30);
        jLayeredPane1.add(jLabel47);
        jLabel47.setBounds(420, 140, 40, 30);
        jLayeredPane1.add(jLabel48);
        jLabel48.setBounds(120, 170, 40, 30);
        jLayeredPane1.add(jLabel49);
        jLabel49.setBounds(160, 170, 40, 30);
        jLayeredPane1.add(jLabel50);
        jLabel50.setBounds(200, 170, 40, 30);
        jLayeredPane1.add(jLabel51);
        jLabel51.setBounds(240, 170, 30, 30);
        jLayeredPane1.add(jLabel52);
        jLabel52.setBounds(280, 170, 30, 30);
        jLayeredPane1.add(jLabel53);
        jLabel53.setBounds(310, 170, 40, 30);
        jLayeredPane1.add(jLabel54);
        jLabel54.setBounds(350, 170, 30, 30);
        jLayeredPane1.add(jLabel55);
        jLabel55.setBounds(390, 170, 30, 30);
        jLayeredPane1.add(jLabel56);
        jLabel56.setBounds(420, 170, 40, 30);
        jLayeredPane1.add(jLabel57);
        jLabel57.setBounds(460, 170, 40, 30);
        jLayeredPane1.add(jLabel58);
        jLabel58.setBounds(120, 200, 40, 30);
        jLayeredPane1.add(jLabel59);
        jLabel59.setBounds(160, 200, 40, 30);
        jLayeredPane1.add(jLabel60);
        jLabel60.setBounds(200, 200, 40, 30);
        jLayeredPane1.add(jLabel61);
        jLabel61.setBounds(240, 200, 30, 30);
        jLayeredPane1.add(jLabel62);
        jLabel62.setBounds(280, 200, 30, 30);
        jLayeredPane1.add(jLabel63);
        jLabel63.setBounds(310, 200, 40, 30);
        jLayeredPane1.add(jLabel64);
        jLabel64.setBounds(350, 200, 30, 30);
        jLayeredPane1.add(jLabel65);
        jLabel65.setBounds(390, 200, 30, 30);
        jLayeredPane1.add(jLabel66);
        jLabel66.setBounds(420, 200, 40, 30);
        jLayeredPane1.add(jLabel67);
        jLabel67.setBounds(460, 200, 40, 30);
        jLayeredPane1.add(jLabel68);
        jLabel68.setBounds(120, 230, 40, 30);
        jLayeredPane1.add(jLabel69);
        jLabel69.setBounds(160, 230, 40, 30);
        jLayeredPane1.add(jLabel70);
        jLabel70.setBounds(200, 230, 40, 30);
        jLayeredPane1.add(jLabel71);
        jLabel71.setBounds(240, 230, 30, 30);
        jLayeredPane1.add(jLabel72);
        jLabel72.setBounds(280, 230, 30, 30);
        jLayeredPane1.add(jLabel73);
        jLabel73.setBounds(310, 230, 40, 30);
        jLayeredPane1.add(jLabel74);
        jLabel74.setBounds(350, 230, 30, 30);
        jLayeredPane1.add(jLabel75);
        jLabel75.setBounds(390, 230, 30, 30);
        jLayeredPane1.add(jLabel76);
        jLabel76.setBounds(420, 230, 40, 30);
        jLayeredPane1.add(jLabel77);
        jLabel77.setBounds(460, 230, 40, 30);
        jLayeredPane1.add(jLabel78);
        jLabel78.setBounds(120, 260, 40, 30);
        jLayeredPane1.add(jLabel79);
        jLabel79.setBounds(160, 260, 40, 30);
        jLayeredPane1.add(jLabel80);
        jLabel80.setBounds(200, 260, 40, 30);
        jLayeredPane1.add(jLabel81);
        jLabel81.setBounds(240, 260, 30, 30);
        jLayeredPane1.add(jLabel82);
        jLabel82.setBounds(280, 260, 30, 30);
        jLayeredPane1.add(jLabel83);
        jLabel83.setBounds(310, 260, 40, 30);
        jLayeredPane1.add(jLabel84);
        jLabel84.setBounds(350, 260, 30, 30);
        jLayeredPane1.add(jLabel85);
        jLabel85.setBounds(390, 260, 30, 30);
        jLayeredPane1.add(jLabel86);
        jLabel86.setBounds(420, 260, 40, 30);
        jLayeredPane1.add(jLabel87);
        jLabel87.setBounds(460, 260, 40, 30);
        jLayeredPane1.add(jLabel88);
        jLabel88.setBounds(120, 290, 40, 30);
        jLayeredPane1.add(jLabel89);
        jLabel89.setBounds(160, 290, 40, 30);
        jLayeredPane1.add(jLabel90);
        jLabel90.setBounds(200, 294, 40, 20);
        jLayeredPane1.add(jLabel91);
        jLabel91.setBounds(240, 290, 30, 30);
        jLayeredPane1.add(jLabel92);
        jLabel92.setBounds(280, 294, 30, 20);
        jLayeredPane1.add(jLabel93);
        jLabel93.setBounds(320, 290, 30, 30);
        jLayeredPane1.add(jLabel94);
        jLabel94.setBounds(350, 290, 30, 30);
        jLayeredPane1.add(jLabel95);
        jLabel95.setBounds(390, 290, 30, 30);
        jLayeredPane1.add(jLabel96);
        jLabel96.setBounds(430, 290, 30, 30);
        jLayeredPane1.add(jLabel97);
        jLabel97.setBounds(460, 290, 40, 30);
        jLayeredPane1.add(jLabel98);
        jLabel98.setBounds(120, 320, 40, 30);
        jLayeredPane1.add(jLabel99);
        jLabel99.setBounds(160, 320, 40, 30);
        jLayeredPane1.add(jLabel100);
        jLabel100.setBounds(200, 320, 40, 30);
        jLayeredPane1.add(jLabel101);
        jLabel101.setBounds(240, 320, 30, 30);
        jLayeredPane1.add(jLabel102);
        jLabel102.setBounds(280, 320, 30, 30);
        jLayeredPane1.add(jLabel103);
        jLabel103.setBounds(320, 320, 30, 30);
        jLayeredPane1.add(jLabel104);
        jLabel104.setBounds(350, 320, 40, 30);
        jLayeredPane1.add(jLabel105);
        jLabel105.setBounds(390, 320, 30, 30);
        jLayeredPane1.add(jLabel106);
        jLabel106.setBounds(430, 320, 30, 30);
        jLayeredPane1.add(jLabel107);
        jLabel107.setBounds(460, 320, 40, 30);
        jLayeredPane1.add(jLabel108);
        jLabel108.setBounds(120, 350, 40, 30);
        jLayeredPane1.add(jLabel109);
        jLabel109.setBounds(160, 350, 40, 30);
        jLayeredPane1.add(jLabel110);
        jLabel110.setBounds(200, 350, 40, 30);
        jLayeredPane1.add(jLabel111);
        jLabel111.setBounds(240, 350, 30, 30);
        jLayeredPane1.add(jLabel112);
        jLabel112.setBounds(280, 350, 30, 30);
        jLayeredPane1.add(jLabel113);
        jLabel113.setBounds(320, 350, 30, 30);
        jLayeredPane1.add(jLabel114);
        jLabel114.setBounds(350, 350, 40, 30);
        jLayeredPane1.add(jLabel115);
        jLabel115.setBounds(390, 350, 30, 30);
        jLayeredPane1.add(jLabel116);
        jLabel116.setBounds(420, 350, 40, 30);
        jLayeredPane1.add(jLabel117);
        jLabel117.setBounds(460, 350, 40, 30);
        jLayeredPane1.add(jLabel118);
        jLabel118.setBounds(120, 390, 40, 30);
        jLayeredPane1.add(jLabel119);
        jLabel119.setBounds(160, 390, 40, 30);
        jLayeredPane1.add(jLabel120);
        jLabel120.setBounds(200, 390, 40, 30);
        jLayeredPane1.add(jLabel121);
        jLabel121.setBounds(240, 390, 30, 30);
        jLayeredPane1.add(jLabel122);
        jLabel122.setBounds(280, 390, 30, 30);
        jLayeredPane1.add(jLabel123);
        jLabel123.setBounds(320, 390, 30, 30);
        jLayeredPane1.add(jLabel124);
        jLabel124.setBounds(356, 390, 30, 30);
        jLayeredPane1.add(jLabel125);
        jLabel125.setBounds(390, 390, 30, 30);
        jLayeredPane1.add(jLabel126);
        jLabel126.setBounds(420, 390, 40, 30);
        jLayeredPane1.add(jLabel127);
        jLabel127.setBounds(460, 390, 40, 30);
        jLayeredPane1.add(jLabel128);
        jLabel128.setBounds(160, 420, 40, 30);
        jLayeredPane1.add(jLabel129);
        jLabel129.setBounds(200, 420, 40, 30);
        jLayeredPane1.add(jLabel130);
        jLabel130.setBounds(240, 420, 30, 30);
        jLayeredPane1.add(jLabel131);
        jLabel131.setBounds(280, 420, 30, 30);
        jLayeredPane1.add(jLabel132);
        jLabel132.setBounds(320, 420, 30, 30);
        jLayeredPane1.add(jLabel133);
        jLabel133.setBounds(350, 420, 40, 30);
        jLayeredPane1.add(jLabel134);
        jLabel134.setBounds(390, 420, 30, 30);
        jLayeredPane1.add(jLabel135);
        jLabel135.setBounds(430, 420, 30, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewBoard.png"))); // NOI18N
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(120, 140, 390, 316);

        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jLabel136);
        jLabel136.setBounds(540, 180, 70, 20);

        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jLabel137);
        jLabel137.setBounds(115, 76, 80, 20);

        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jLabel138);
        jLabel138.setBounds(20, 180, 80, 20);

        jLabel140.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jLabel140);
        jLabel140.setBounds(500, 490, 100, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.jpg"))); // NOI18N
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(4, -6, 620, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        help_button = true;
        this.setVisible(false);
        InstructionsPage obj = new InstructionsPage();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
public void setHelpButton(boolean a){
        help_button = a;
    }
    public boolean getHelpButton(){
        return help_button;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MainMenu obj = new MainMenu();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SequenceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SequenceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SequenceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SequenceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SequenceGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
