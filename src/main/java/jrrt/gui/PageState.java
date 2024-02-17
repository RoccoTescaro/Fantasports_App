package jrrt.gui;

import javax.swing.*;

public abstract class PageState 
{
    protected final JFrame frame;

    protected final PageHandler handler;

    public PageState(PageHandler handler) 
    {
        this.handler = handler;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public PageState(PageHandler handler, int width, int height) 
    {
        this(handler);
        frame.setSize(width, height);
    }

    public void refresh() 
    {
        frame.setVisible(false);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}