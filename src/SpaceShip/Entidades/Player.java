package SpaceShip.Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import SpaceShip.main.*;
import SpaceShip.Bullets.*;
import SpaceShip.Effects.*;


public class Player extends Entidades{
	
	
	public boolean right, left, up, down,shoot;
	public int speed = 5;
	
	public int targetTime = 10;
	public int curTime = 0;
	
	public Player(int x, int y, int width, int high, BufferedImage image) {
		super(x, y, width, high, image);
		
	}
	
	public void update(){
		
		if(right){
			if(x+50 < Game.Width){
				x += speed;
			}
			
		}
		if(left){
			if(x > 10){
				x -= speed;
			}
			
		}
		if(up){
			if(y > 100){
				y -= speed;
			}
			
		}
		if(down){
			if(y+65 < Game.Height){
				y += speed;
			}
			
		}
		
		if(shoot){
			curTime++;
			if(curTime == targetTime){
				curTime = 0;
				shoot = false;
				int xx = this.getX() + 18;
				int yy = this.getY();
				Bullet bullet = new Bullet(xx, yy, 4,10, null);
				Game.entidades.add(bullet);
			}
			
		}
		
		//Dano por tiro
		for(int i = 0; i < Game.entidades.size(); i++){
			Entidades e = Game.entidades.get(i);
			if(e instanceof EnemyBullet){
				if(Entidades.isColidding(this, e)){
					Game.entidades.remove(e);
					Game.life+=5;
					if(Game.life == 100){
						Explosion explosion = new Explosion(x,y,60,48,null);
						Game.entidades.add(explosion);
						Game.entidades.remove(this);
						return;
					}
					break;
				}
			}	
		}
		
		
		//Dano por Colisão
		for(int i = 0; i < Game.entidades.size(); i++){
			Entidades e = Game.entidades.get(i);
			if(e instanceof Enemy){
				if(Entidades.isColidding(this, e)){
					Game.entidades.remove(e);
					Explosion explosion = new Explosion(x,y,60,48,null);
					Game.entidades.add(explosion);
					Game.life+=50;
					if(Game.life >= 100){
						Game.life = 100;
						//Explosion explosion = new Explosion(x,y,60,48,null);
						//Game.entidades.add(explosion);
						Game.entidades.remove(this);
						return;
						
					}
					break;
				}
			}	
		}
		
	}
	
	/*
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect(50, 50, 100, 100);
		
		
	}*/

}
