package jrrt.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class MainMenuPageState extends PageState 
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    private JLabel userLabel;
    private JLabel emailLabel;
    private JLabel alertsLabel;
    private JLabel yourLeaguesLabel;
    private JButton joinButton;
    private JButton createNewLeagueButton;
    private JTable leaguesTable;
    private JTable alertsTable;
    private JTextField joinTextField;

    public MainMenuPageState(PageHandler handler) 
    {
        super(handler, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setResizable(true);
        initializeComponents();
        addComponents();
        refresh();
    }

    private void initializeComponents() 
    {
        userLabel = new JLabel("User:"); 
        userLabel.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        emailLabel = new JLabel("Email:");
        emailLabel.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        alertsLabel = new JLabel("Alerts:");
        alertsLabel.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        yourLeaguesLabel = new JLabel("Your Leagues:");
        yourLeaguesLabel.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        joinButton = new JButton("join");
        joinButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        createNewLeagueButton = new JButton("Create new League");
        createNewLeagueButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        leaguesTable = new JTable(new DefaultTableModel(new Object[]{"League Name", "Status"}, 0));
        leaguesTable.setPreferredScrollableViewportSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        leaguesTable.setFillsViewportHeight(true);
        alertsTable = new JTable(new DefaultTableModel(new Object[]{"Alert Message", "Time"}, 0));
        alertsTable.setPreferredScrollableViewportSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        alertsTable.setFillsViewportHeight(true);

        joinTextField = new JTextField("Enter league name...");
        joinTextField.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        joinTextField.setForeground(Color.GRAY);
    
    }

    private void addComponents() 
    {
        window.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        userPanel.add(userLabel);
        userPanel.add(emailLabel);
        userPanel.add(Box.createVerticalGlue());
        userPanel.add(yourLeaguesLabel);

        constraints.gridx = 0;
        constraints.gridy = 0;    
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 2.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        window.getContentPane().add(userPanel, constraints);

        JPanel alertsPanel = new JPanel();
        alertsPanel.setLayout(new BoxLayout(alertsPanel, BoxLayout.Y_AXIS));

        alertsPanel.add(Box.createVerticalGlue());
        alertsPanel.add(alertsLabel);

        constraints.gridx = 1;
        constraints.gridy = 0;
        window.getContentPane().add(alertsPanel, constraints);

        JScrollPane leaguesScrollPane = new JScrollPane(leaguesTable);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 3;
        constraints.weighty = 3.0;
        window.getContentPane().add(leaguesScrollPane, constraints);
        
        JScrollPane alertsScrollPane = new JScrollPane(alertsTable);
        constraints.gridx = 1;
        constraints.gridy = 1;
        window.getContentPane().add(alertsScrollPane, constraints);
    
        JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GridBagConstraints joinConstraints = new GridBagConstraints();

        joinPanel.add(joinTextField);
        joinPanel.add(joinButton);
    
        // Add the joinPanel to the main layout
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.weighty = 0.25;
        window.getContentPane().add(joinPanel, constraints);
    
        constraints.gridx = 1;
        window.getContentPane().add(createNewLeagueButton, constraints);

    }

    public void updateLeaguesTable(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) leaguesTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : data) {
            model.addRow(row); // Add each row from the new data
        }
    }

    public void updateAlertsTable(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) alertsTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : data) {
            model.addRow(row); // Add each row from the new data
        }
    }
}   