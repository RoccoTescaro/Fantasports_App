package jrrt.gui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;

public abstract class PageState 
{
    protected JFrame window;

    protected static final int BORDER_PADDING = 16;
    protected static final int BUTTON_WIDTH = 128;
    protected static final int BUTTON_HEIGHT = 32;
    protected static final int FONT_SIZE = 20;
    protected static final String FONT_NAME = "Calibri";
    protected static final int TEXT_FIELD_WIDTH = 128;
    protected static final int TEXT_FIELD_HEIGHT = 28;
    protected static final int VERTICAL_LINE_SPACING = 12;
    protected static final int HORIZONTAL_LINE_SPACING = 8;

    protected final PageHandler handler;

    public PageState(PageHandler handler) 
    {
        System.out.println("PageState created");
        this.handler = handler;
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
    }

    public PageState(PageHandler handler, int width, int height) 
    {
        this(handler);
        window.setSize(width, height);
    }

    public void refresh() 
    {
        window.setVisible(false);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    public void quit() 
    {
        window.setVisible(false);
        window.dispose();
    }

    protected JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        return label;
    }

    protected JTextField createTextField() 
    {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        textField.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        return textField;
    }
}