package frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JFrame;
import model.GameState;

public class MainMenuFrame extends JFrame{
    public MainMenuFrame(GameState gameState){
        super("主菜单");
        setSize(800, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    
        JButton startButton = new JButton("开始游戏");
        JButton loadButton = new JButton("读取游戏");
        JButton exitButton = new JButton("退出游戏");
        JButton soundButton = new JButton("音效：开");
        JButton ruleButton = new JButton("游戏规则");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 0, 20));
        JLabel titleLabel = new JLabel("斗兽棋");
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);
        mainPanel.add(startButton);
        mainPanel.add(loadButton);
        mainPanel.add(soundButton);
        mainPanel.add(ruleButton);
        mainPanel.add(exitButton);
        mainPanel.setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        mainPanel.setOpaque(false);
        mainPanel.setBackground(new Color(255, 255, 255));
        add(mainPanel, BorderLayout.CENTER);
        

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ChessGameFrame gameFrame = new ChessGameFrame(1100, 810, gameState);
                gameFrame.setVisible(true);
            }
        });

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

        ruleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filepath = "DOUSHOUQI\\resource\\rule.txt";
                File file = new File(filepath);
                RuleFrame ruleFrame = null;
                try {
                    ruleFrame = new RuleFrame(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ruleFrame.setVisible(true);
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
    }

}
