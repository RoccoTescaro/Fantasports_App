package jrrt.gui;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private final JFrame frame;
    private final JPanel contentPane; // Use a content pane for layout and background

    public Menu() 
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(); // Create the content pane
        contentPane.setLayout(new BorderLayout()); // Use BorderLayout for flexibility
        frame.setContentPane(contentPane); // Set the content pane

        // Set the background color of the content pane
        contentPane.setBackground(new Color(40, 42, 54));

        // Set frame icon (adjust path if needed)
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
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
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
