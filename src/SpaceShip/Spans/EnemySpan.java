package SpaceShip.Spans;

import SpaceShip.main.*;
import SpaceShip.Entidades.*;

public class EnemySpan {
	
	public int targetTime = 60*2;
	public int curTime = 0;
	
	public void update(){
		curTime ++;
		if(curTime == targetTime){
			curTime = 0;
			int yy = 0;
			int xx = Entidades.rand.nextInt(Game.Width - 50);
			Enemy enemy = new Enemy(xx,yy,50,62,Game.enemies.getSprite(0, 0, 50, 62));
			Game.entidades.add(enemy);
		}
	}
	

}
