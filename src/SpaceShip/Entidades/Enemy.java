package SpaceShip.Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import SpaceShip.main.*;
import SpaceShip.Spans.*;
import SpaceShip.Bullets.*;
import SpaceShip.Effects.*;

public class Enemy extends Entidades {
	
	public int life = 3;
	
	public int targetTime = 60;
	public int curTime = 0;
	public boolean shoot;

	public Enemy(int x, int y, int width, int heigh, BufferedImage image) {
		super(x, y, width, heigh, image);
		
	}
	
	public void update(){
		y += 2;
		if(y > Game.Height){
			Game.entidades.remove(this);
			if(Game.score > 0){
				Game.score--;
			}
			return;
		}
		
		for(int i = 0; i < Game.entidades.size(); i++){
			Entidades e = Game.entidades.get(i);
			if(e instanceof Bullet){
				if(Entidades.isColidding(this, e)){
					Game.entidades.remove(e);
					life--;
					if(life == 0){
						Explosion explosion = new Explosion(x,y,60,48,null);
						Game.entidades.add(explosion);
						Game.entidades.remove(this);
						Game.score++;
						return;
					}
					break;
				}
			}
		}
		
		//Renderizando tiro inimigo
		curTime++;
		if(curTime == targetTime){
			curTime = 0;
			int xx = this.getX() + 25;
			int yy = this.getY();
			EnemyBullet bullet = new EnemyBullet(xx, yy, 4,10, null);
			Game.entidades.add(bullet);
		}
		
	}
	/*
	public void render(Graphics g){
		
		//g.setColor(Color.red);
		//g.fillRect(x, y, width, height);

	}*/

}
