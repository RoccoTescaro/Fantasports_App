package jrrt.core;

import jrrt.gui.Menu;

public class Application 
{
	public static void main(String[] args) 
	{
        Menu main_menu = new Menu(600, 900);
		main_menu.addLabel("User: user_nickname");
		main_menu.addLabel("Email: user_email");
		main_menu.addButton("Create League", () -> System.out.println("create league"));
		main_menu.addButton("View Leagues", () -> System.out.println("view leagues"));
		main_menu.addButton("Join League", () -> System.out.println("join league"));
	}
}
