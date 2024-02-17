package jrrt.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPageState extends PageState 
{
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_HEIGHT = 208;

    private static final int TOGGLE_BUTTON_SIZE = 12;

    public LoginPageState(PageHandler handler) 
    {
        super(handler, WINDOW_WIDTH, WINDOW_HEIGHT);

        JPanel login_panel = createLoginPanel();
        JPanel join_button_panel = createJoinButtonPanel();

        window.add(login_panel, BorderLayout.CENTER);
        window.add(join_button_panel, BorderLayout.SOUTH);

        refresh();
    }

    private JPanel createLoginPanel() 
    {
        JPanel login_panel = new JPanel();
        login_panel.setLayout(new BoxLayout(login_panel, BoxLayout.Y_AXIS));

        JPanel username_line = createUsernameLine();
        JPanel password_line = createPasswordLine();

        login_panel.add(username_line);
        login_panel.add(Box.createVerticalStrut(VERTICAL_LINE_SPACING));
        login_panel.add(password_line);
        
        login_panel.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        return login_panel;
    }

    private JPanel createUsernameLine() 
    {
        JPanel username_line = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel username_label = createLabel("Username: ");
        username_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        username_line.add(username_label, constraints);

        JTextField username_field = createTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        username_line.add(username_field, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        username_line.add(Box.createHorizontalStrut(TOGGLE_BUTTON_SIZE), constraints);

        return username_line;
    }

    private JPanel createPasswordLine() 
    {
        JPanel password_line = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel password_label = createLabel("Password: ");
        password_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        password_line.add(password_label, constraints);

        JPasswordField password_field = createPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        password_line.add(password_field, constraints);

        JToggleButton toggle_button = createToggleButton(password_field);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        password_line.add(toggle_button, constraints);

        return password_line;
    }

    private JPasswordField createPasswordField() 
    {
        JPasswordField password_field = new JPasswordField();
        password_field.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        password_field.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        return password_field;
    }

    private JToggleButton createToggleButton(JPasswordField password_field) 
    {
        JToggleButton toggle_button = new JToggleButton("👁");
        toggle_button.setFont(new Font(FONT_NAME, Font.PLAIN, 8));
        toggle_button.setPreferredSize(new Dimension(TOGGLE_BUTTON_SIZE, TOGGLE_BUTTON_SIZE));
        toggle_button.setMaximumSize(new Dimension(TOGGLE_BUTTON_SIZE, TOGGLE_BUTTON_SIZE));
        toggle_button.addActionListener((ActionEvent e) -> 
        {
            if (toggle_button.isSelected()) password_field.setEchoChar((char) 0);
            else password_field.setEchoChar('•');
        });
        return toggle_button;
    }

    private JPanel createJoinButtonPanel() 
    {
        JPanel join_button_panel = new JPanel();

        JButton join_button = new JButton("Join");
        join_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        join_button.addActionListener((ActionEvent e) -> 
        {
            System.out.println("Join button pressed");
            handler.setPage(new MainMenuPageState(handler));
        });

        join_button_panel.add(join_button);
        join_button_panel.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        return join_button_panel;
    }
}