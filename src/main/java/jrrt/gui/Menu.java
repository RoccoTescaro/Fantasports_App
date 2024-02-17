package jrrt.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Menu {

    private final JFrame frame;
    private final JPanel content_panel; // Use a content pane for layout and background
    
    private final int spacing = 14;

    private final int small_char_width = 12;

    public Menu() 
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //
        content_panel = new JPanel();
        content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.Y_AXIS));

        Border padding = BorderFactory.createEmptyBorder(16, 48, 16, 48);
        content_panel.setBorder(padding);
        
        content_panel.setBackground(new Color(40, 42, 54));
        
        frame.setContentPane(content_panel);
        //    
    
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
        frame.setIconImage(icon.getImage());
        
        refresh();
    }

    public Menu(int width, int height) 
    {
        this();
        frame.setSize(width, height);
        refresh();
    }

    public void addLabel(String text) 
    {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(248, 248, 242));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);
        content_panel.add(label);
        refresh();
    }

    public void addButton(String text, Runnable action) 
    {
        JButton button = new JButton(text);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(248, 248, 242), 2));
        button.setPreferredSize(new Dimension(text.length()*small_char_width, 48));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setForeground(new Color(248, 248, 242));
        button.setBackground(new Color(68, 71, 90));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.addActionListener(e -> action.run());
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        content_panel.add(Box.createRigidArea(new Dimension(0, spacing)));
        content_panel.add(button);
        refresh();
    }

    private void refresh() 
    {
        frame.setVisible(false);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }
}
