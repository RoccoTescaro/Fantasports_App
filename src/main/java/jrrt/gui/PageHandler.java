package jrrt.gui;

public class PageHandler 
{
    private PageState currentPage;

    public PageHandler() 
    {
        System.out.println("PageHandler created");
        currentPage = new LoginPageState(this);
    }

    public void setPage(PageState page) 
    {
        currentPage.quit();
        if (page != null) currentPage = page;
    }
}