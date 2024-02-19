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
        userLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        createNewLeagueButton = new JButton("Create new League");
        userLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        leaguesTable = new JTable(new DefaultTableModel(new Object[]{"League", "Status"}, 0));
        //leaguesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        leaguesTable.setFillsViewportHeight(true);
        alertsTable = new JTable(new DefaultTableModel(new Object[]{"Time", "Alert Message"}, 0));
        //alertsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        alertsTable.setFillsViewportHeight(true);
    }

    private void addComponents() 
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

        JScrollPane leaguesScrollPane = new JScrollPane(leaguesTable);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 3;
        constraints.weightx = 3.0;
        constraints.weighty = 3.0;
        window.getContentPane().add(leaguesScrollPane, constraints);
    
        JScrollPane alertsScrollPane = new JScrollPane(alertsTable);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        window.getContentPane().add(alertsScrollPane, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weighty = 0.25;
        window.getContentPane().add(joinButton, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 5;
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