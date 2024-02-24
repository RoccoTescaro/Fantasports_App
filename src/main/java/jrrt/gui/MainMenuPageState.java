package jrrt.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*public class MainMenuPageState extends PageState 
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 700;

    private JLabel user_label;
    private JLabel email_label;
    private JLabel alerts_label;
    private JLabel your_leagues_label;
    private JButton join_button;
    private JButton create_new_league_button;
    private JTable leagues_table;
    private JTable alerts_table;
    private JTextField join_text_field;

    public MainMenuPageState(PageHandler handler) 
    {
        super(handler, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setResizable(true);
        initialize_components();
        add_components();
        refresh();
    }

    private void initialize_components() 
    {
        user_label = new JLabel("User:"); 
        user_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));

        email_label = new JLabel("Email:");
        email_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));

        alerts_label = new JLabel("Alerts:");
        alerts_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));

        your_leagues_label = new JLabel("Your Leagues:");
        your_leagues_label.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));

        join_button = new JButton("join");
        join_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        create_new_league_button = new JButton("Create League");
        create_new_league_button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        leagues_table = new JTable(new DefaultTableModel(new Object[]{"League Name", "Status"}, 0));
        leagues_table.setPreferredScrollableViewportSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        leagues_table.setFillsViewportHeight(true);

        alerts_table = new JTable(new DefaultTableModel(new Object[]{"Alert Message", "Time"}, 0));
        alerts_table.setPreferredScrollableViewportSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        alerts_table.setFillsViewportHeight(true);

        join_text_field = new JTextField("Enter league name...");
        join_text_field.setPreferredSize(new Dimension(200, TEXT_FIELD_HEIGHT));
        join_text_field.setForeground(Color.GRAY);
        join_text_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (join_text_field.getText().equals("Enter league name...")) {
                    join_text_field.setText("");
                    join_text_field.setForeground(Color.BLACK);
                }
            }
    
            @Override
            public void focusLost(FocusEvent e) {
                if (join_text_field.getText().isEmpty()) {
                    join_text_field.setForeground(Color.GRAY);
                    join_text_field.setText("Enter league name...");
                }
             }
        });

        leaguesTable = new JTable(new DefaultTableModel(new Object[]{"League", "Status"}, 0));
        //leaguesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leaguesTable.setFillsViewportHeight(true);
        alertsTable = new JTable(new DefaultTableModel(new Object[]{"Time", "Alert Message"}, 0));
        //alertsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        alertsTable.setFillsViewportHeight(true);
    }

    private void add_components() 
    {
        window.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel innerPanel = new JPanel()
        {
            @Override
            public Dimension getPreferredSize() {
                return getMinimumSize();
            }
        };
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.add(userLabel);
        innerPanel.add(emailLabel);
        innerPanel.add(yourLeaguesLabel);
    
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(innerPanel, BorderLayout.SOUTH);

        constraints.gridx = 0;
        constraints.gridy = 0;    
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 3.0;
        constraints.weighty = 0.05;
        constraints.fill = GridBagConstraints.BOTH;
        window.getContentPane().add(outerPanel, constraints);
    
        innerPanel = new JPanel()
        {
            @Override
            public Dimension getPreferredSize() {
                return getMinimumSize();
            }
        };
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.add(alertsLabel);
        outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(innerPanel, BorderLayout.SOUTH);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        window.getContentPane().add(outerPanel, constraints);

        JScrollPane leagues_scroll_pane = new JScrollPane(leagues_table);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 3;
        constraints.weightx = 3.0;
        constraints.weighty = 3.0;
        window.getContentPane().add(leagues_scroll_pane, constraints);
        
        JScrollPane alerts_scroll_pane = new JScrollPane(alerts_table);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        window.getContentPane().add(alertsScrollPane, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weighty = 0.25;
        window.getContentPane().add(join_panel, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 5;
        window.getContentPane().add(createNewLeagueButton, constraints);
    }

        JPanel create_league_sub_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        create_league_sub_panel.add(create_new_league_button);
        create_league_panel.add(create_league_sub_panel);
        create_league_panel.add(Box.createVerticalGlue());

        constraints.gridx = 1;
        constraints.gridy = 4;
        window.getContentPane().add(create_league_panel, constraints);

    }
    
    public void updateLeaguesTable(Object[][] data) 
    {
        DefaultTableModel model = (DefaultTableModel) leagues_table.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : data) {
            model.addRow(row); // Add each row from the new data
        }
    }

    public void updateAlertsTable(Object[][] data) 
    {
        DefaultTableModel model = (DefaultTableModel) alerts_table.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : data) {
            model.addRow(row); // Add each row from the new data
        }
    }
}   
*/