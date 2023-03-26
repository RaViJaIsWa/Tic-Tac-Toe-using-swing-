
package com.myGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class myGame extends JFrame implements ActionListener {
    
    JLabel heading,clockLabel;
    Font font = new Font("",Font.BOLD, 40);
    JPanel mainPanel;
    
    JButton []btns = new JButton[9];
    
    
    
    // Game instance Variables...
    
    int gameChances[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    
    int wps[][] = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6}
    };
    
    int winner = 2;
    
    boolean gameOver = false;
    
    
    myGame(){
        System.out.println("Creating instance of game");
        setTitle("My Tic Tac Toe Game.......");
        setSize(850,850);
        ImageIcon icon = new ImageIcon("src/img/images_1.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    
    private void createGUI(){
        
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        
        // North Heading.....
        
        heading = new JLabel("Tic Tac Toe");
        heading.setIcon(new ImageIcon("src/img/images_1.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        this.add(heading,BorderLayout.NORTH);
        
        //Creating object of JLabel for Clock
        clockLabel = new JLabel("clock");
        clockLabel.setForeground(Color.white);
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);
        
        Thread t = new Thread(){
            @Override
            public void run(){
               try{
                    while(true){
                        String datetime = new Date().toLocaleString();
                        
                        clockLabel.setText(datetime);
                        
                        Thread.sleep(1000);}
               }
               catch(Exception e){
                   e.printStackTrace();
               }
            }
        };
        t.start();
        
        //////////////PANEL SECTION\\\\\\\\\\\\\\\
        mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(3, 3));
        
        for(int i = 1; i<=9; i++){
            JButton btn = new JButton();
            btn.setBackground(Color.decode("#0000FF"));
            
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i - 1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        
        this.add(mainPanel, BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();
        
        String nameStr=currentButton.getName();
        System.out.println(nameStr);
        
        
        int name = Integer.parseInt(nameStr.trim());
        
        
        if(gameOver == true){
            
            JOptionPane.showMessageDialog(this, "Game Already Over.....");
            return;
        }
        
        if(gameChances[name] == 2){
            if(activePlayer == 1){
                currentButton.setIcon(new ImageIcon("src/img/1.png.png"));
                
                gameChances[name] = activePlayer;
                activePlayer=0;
            }else{
                
                currentButton.setIcon(new ImageIcon("src/img/0.png.png"));
                
                gameChances[name] = activePlayer;
                activePlayer=1;
            }
            
            // Find the winner........
            
            for(int []temp:wps){
                if((gameChances[temp[0]] == gameChances[temp[1]])&&(gameChances[temp[1]] == gameChances[temp[2]]) && gameChances[temp[2]]!= 2){
                    winner = gameChances[temp[0]];
                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "Player "+winner+" has won the game....");
                    int i = JOptionPane.showConfirmDialog(this, "do you want to play more ??");
                    if(i == 0){
                        this.setVisible(false);
                        new myGame();
                    }else if(i == 1){
                        System.exit(8798787);
                    }else{
                        
                    }
                    System.out.println(i);
                    break;
                }
            }
            
            // Draw Logic
            
            int c = 0;
            
            for(int x:gameChances){
                if(x==2){
                    c++;
                    break;
                }
            }
            if(c==0 && gameOver==false){
                JOptionPane.showMessageDialog(null,"Its draw....");
                
                
                int i = JOptionPane.showConfirmDialog(this, "play more??");
                if(i == 0){
                    this.setVisible(false);
                    
                    new myGame();
                }else if(i == 1){
                    System.exit(121234);
                }else{
                    
                }
                
                gameOver = true;
            }
        }else{
            JOptionPane.showMessageDialog(this, "position already occupied...");
        }
    }
}
