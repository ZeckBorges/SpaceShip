package SpaceShip.Effects;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import SpaceShip.Entidades.*;
import SpaceShip.main.*;

public class Explosion extends Entidades{
	
	private int frames = 0;
	private int targetFrames = 5;
	private int maxanimation = 7;
	private int curAnimation = 0;
	private BufferedImage[] explosion;

	public Explosion(int x, int y, int width, int heigh, BufferedImage image) {
		super(x, y, width, heigh, image);
		
		explosion = new BufferedImage[8];
		
	}
	
	public void update(){
		for(int i = 0; i < 8; i++){
			explosion[i] = Game.explosion.getSprite(0 + (i*64), 0, 64, 64);
		}
		
		frames++;
		if(frames == targetFrames){
			frames = 0;
			curAnimation++;
			if(curAnimation > maxanimation){
				Game.entidades.remove(this);
				return;
			}
		}
	}
	
	public void render(Graphics g){
		
		g.drawImage(explosion[curAnimation], this.getX(), this.getY(), null);
		g.drawImage(explosion[curAnimation], this.getX(), this.getY()-20, null);
		g.drawImage(explosion[curAnimation], this.getX()-30, this.getY(), null);
		g.drawImage(explosion[curAnimation], this.getX()-30, this.getY()-20, null);
	}
	
	

}
