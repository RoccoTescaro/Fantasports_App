package jrrt.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Menu {

    private final JFrame frame;
    private final JPanel content_panel; // Use a content pane for layout and background

    public Menu() 
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //
        content_panel = new JPanel();

        content_panel.setLayout(new BorderLayout()); // Use BorderLayout for flexibility

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
        content_panel.add(label, BorderLayout.PAGE_START);
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
