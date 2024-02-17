package jrrt.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuPageState extends PageState 
{
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    public MainMenuPageState(PageHandler handler) 
    {
        super(handler, WINDOW_WIDTH, WINDOW_HEIGHT);

        JPanel menu_panel = createMenuPanel();

        window.add(menu_panel, BorderLayout.CENTER);

        refresh();
    }

    private JPanel createMenuPanel() 
    {
        JPanel menu_panel = new JPanel();
        menu_panel.setLayout(new BoxLayout(menu_panel, BoxLayout.Y_AXIS));

        JButton play_button = createPlayButton();
        JButton settings_button = createSettingsButton();
        JButton quit_button = createQuitButton();

        menu_panel.add(play_button);
        menu_panel.add(Box.createVerticalStrut(VERTICAL_LINE_SPACING));
        menu_panel.add(settings_button);
        menu_panel.add(Box.createVerticalStrut(VERTICAL_LINE_SPACING));
        menu_panel.add(quit_button);

        menu_panel.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        return menu_panel;
    }

    private JButton createPlayButton() 
    {
        JButton play_button = new JButton("Play");
        play_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        play_button.addActionListener((ActionEvent e) -> 
        {
            System.out.println("Play button pressed");
        });

        return play_button;
    }

    private JButton createSettingsButton() 
    {
        JButton settings_button = new JButton("Settings");
        settings_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        settings_button.addActionListener((ActionEvent e) -> 
        {
            System.out.println("Settings button pressed");
        });

        return settings_button;
    }

    private JButton createQuitButton() 
    {
        JButton quit_button = new JButton("Quit");
        quit_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        quit_button.addActionListener((ActionEvent e) -> 
        {
            System.out.println("Quit button pressed");
        });

        return quit_button;
    }
}