package SpaceShip.Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import SpaceShip.main.*;

public class Menu {
	
	public String[] options = {"Start","Ranked","Exit"};
	public int currentoption = 0;
	public int maxoption = options.length - 1;
	public boolean menu;
	public boolean up, down, enter;
	
	
	public void update(){
		if(up){
			up = false;
			currentoption--;
			if(currentoption < 0){
				currentoption = maxoption;
			}
		}
		if(down){
			down = false;
			currentoption++;
			if(currentoption > maxoption){
				currentoption = 0;
			}
		}
		if(enter){
			enter = false;
			if(options[currentoption] == "Start"){
				Game.GameState = "Fase1";
			}
			if(options[currentoption] == "Ranked"){
				Game.GameState = "Ranked";
			}
			if(options[currentoption] == "Exit"){
				System.exit(0);
			}
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.Width*Game.Scale, Game.Height*Game.Scale);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
		g.drawString("Space Ship", (Game.Width*Game.Scale)/2 - 100, (Game.Height*Game.Scale)/2 - 200);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 24));
		g.drawString("Start", (Game.Width*Game.Scale)/2 - 22, (Game.Height*Game.Scale)/2 - 60);
		g.drawString("Ranked", (Game.Width*Game.Scale)/2 - 22, (Game.Height*Game.Scale)/2 - 30);
		g.drawString("Exit", (Game.Width*Game.Scale)/2 - 22, (Game.Height*Game.Scale)/2);
		
		if(options[currentoption] == "Start"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 39, (Game.Height*Game.Scale)/2 - 60);
			//g.drawString("<", (Game.Width*Game.Scale)/2 + 35, (Game.Height*Game.Scale)/2 - 50);
			g.drawString("History Mode with 3 levels.", 10, 580);
		}else if(options[currentoption] == "Ranked"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 39, (Game.Height*Game.Scale)/2 - 30);
			//g.drawString("<", (Game.Width*Game.Scale)/2 + 35, (Game.Height*Game.Scale)/2 - 30);
			g.drawString("Get the better rank that if you can!", 10, 580);
		}else if(options[currentoption] == "Exit"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 39, (Game.Height*Game.Scale)/2);
			//g.drawString("<", (Game.Width*Game.Scale)/2 + 25, (Game.Height*Game.Scale)/2);
			g.drawString("See you latter!", 10, 580);
		}
		
	}

}