package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

import audio.Sound;
import model.GameState;

public class MainMenuFrame extends JFrame{
    public MainMenuFrame(GameState gameState){
        super("主菜单");
        setSize(1100, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    
        JButton startButton = new JButton("开始游戏");
        JButton loadButton = new JButton("读取游戏");
        JButton exitButton = new JButton("退出游戏");
        JButton soundButton = new JButton("音效：开");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ChessGameFrame gameFrame = new ChessGameFrame(1100, 810, gameState);
                gameFrame.setVisible(true);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel titleLabel = new JLabel("斗兽棋");
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
        titleLabel.setForeground(new Color(220, 20, 60));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(loadButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(exitButton);
        add(mainPanel);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ChessGameFrame gameFrame = new ChessGameFrame(1100, 810, gameState);
                gameFrame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //FIXME: soundButton not working
        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (soundButton.getText().equals("音效：开")) {
                    soundButton.setText("音效：关");
                    //Thread.interrupted();
                } else {
                    soundButton.setText("音效：开");
                    //Sound.background();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(soundButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel);
        Sound.background();
        add(mainPanel);


    }

}
