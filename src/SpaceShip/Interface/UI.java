package SpaceShip.Interface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import SpaceShip.main.*;

public class UI {
	
	public int time = 0;
	public int sec = 0;
	public int min = 0;
	
	public void update(){
		time++;
		if(time == 60){
			time = 0;
			sec++;
			if(sec == 60){
				sec = 0;
				min++;
			}
		}
		
	}
	
	public void render(Graphics g){
		//Renderizando o Score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Score: "+ Game.score, 10, 30);
		if(Game.GameState == "Ranked"){
			g.drawString("Time: " + min + ":" + sec + ":" + time, 200, 30);
		}
		
		
		//Renderizando a Vida
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Shield ", Game.Width - 230, 30);
		g.setColor(Color.GREEN);
		g.fillRect(Game.Width - 160, 10, 150, 20);
		
		g.setColor(Color.red);
		g.fillRect(Game.Width - 160, 10,  (int)(Game.life/100 * 150), 20);
		
	}

}
