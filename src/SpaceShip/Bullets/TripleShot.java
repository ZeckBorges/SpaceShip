package SpaceShip.Bullets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import SpaceShip.Entidades.*;
import SpaceShip.main.*;

public class TripleShot extends Entidades {
	
	public TripleShot(int x, int y, int width, int heigh, BufferedImage image) {
		super(x, y, width, heigh, image);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		y -= 6;
		if(y < 0){
			Game.entidades.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(this.getX(), this.getY(), width, height);
	}


}
