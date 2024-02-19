package jrrt.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import jrrt.daosystem.*;
import jrrt.entities.User;

public class LoginPageState extends PageState 
{
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_HEIGHT = 224;
    private static final int TOGGLE_BUTTON_SIZE = 12;
    private static final int STATUS_HEIGHT = 24;

    private Dao<User> user_dao = new UserDao();

    private JTextField username_field;
    private JPasswordField password_field;
    private JLabel status_label;

    public LoginPageState(PageHandler handler) 
    {
        super(handler, WINDOW_WIDTH, WINDOW_HEIGHT);

        JPanel context = new JPanel();
        context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));

        context.add(createLoginPanel());
        context.add(createButtonPanel());
        context.add(createStatusPanel());

        window.add(context, BorderLayout.CENTER);
        refresh();
    }

    private JPanel createLoginPanel() 
    {
        JPanel login_panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        username_field = createTextField();
        login_panel.add(createLinePanel("Username: ", username_field), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        login_panel.add(Box.createVerticalStrut(VERTICAL_LINE_SPACING), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        login_panel.add(createPasswordLine(), constraints);

        login_panel.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, 0, BORDER_PADDING));
        return login_panel;
    }

    private JPanel createPasswordLine() 
    {
        password_field = createPasswordField();
        return createLinePanel("Password: ", password_field, createToggleButton(password_field));
    }

    private JPanel createLinePanel(String label_text, Component... components) 
    {
        JPanel line_panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel label = createLabel(label_text);
        label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        line_panel.add(label, constraints);

        for (int i = 0; i < components.length; i++) 
        {
            constraints.gridx = i + 1;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.EAST;
            line_panel.add(components[i], constraints);
        }

        return line_panel;
    }

    private JPasswordField createPasswordField() 
    {
        password_field = new JPasswordField();
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

    private JPanel createButtonPanel() 
    {
        JPanel button_panel = createPanelWithFixedSize(WINDOW_WIDTH, 64);
        button_panel.add(createButton("Sign up"));
        button_panel.add(createButton("Log in"));
        button_panel.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        return button_panel;
    }

    private JButton createButton(String button_text) 
    {
        JButton button = new JButton(button_text);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener((ActionEvent e) -> 
        {
            System.out.println(button_text + " button pressed");
            
            if (button_text.equals("Sign up")) 
            {
                boolean user_exists = true;

                if (user_exists) 
                    status_label.setText("User already exists.");

            } 
            else if (button_text.equals("Log in")) 
            {
                boolean valid_credentials = true;

                if (valid_credentials)
                    handler.setPage(new MainMenuPageState(handler));
                else
                    status_label.setText("Invalid credentials.");
            }
        });
        return button;
    }

    private JPanel createStatusPanel() 
    {
        JPanel status_panel = createPanelWithFixedSize(WINDOW_WIDTH, STATUS_HEIGHT);
        status_label = new JLabel("");
        status_label.setHorizontalAlignment(SwingConstants.CENTER);
        status_panel.add(status_label);
        return status_panel;
    }

    private JPanel createPanelWithFixedSize(int width, int height) 
    {
        return new JPanel() 
        {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
    }
}