package SpaceShip.Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Random;
import java.awt.Color;

//import GameProject.Entidades.Entidades;

public class Entidades {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	public int depth;
	
	public static Random rand =  new Random();
	
	private int Maskx, Masky, Maskw, Maskh;
	
	public Entidades(int x, int y, int width, int heigh, BufferedImage image){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = heigh;
		this.image = image;
		
		this.Maskx = 0;
		this.Masky = 0;
		this.Maskw = width;
		this.Maskh = heigh;
		
	}
	
	public static Comparator<Entidades> nodeSorter = new Comparator<Entidades>() {
			
			@Override
			public int compare(Entidades n0,Entidades n1) {
				if(n1.depth < n0.depth)
					return +1;
				if(n1.depth > n0.depth)
					return -1;
				return 0;
			}
			
		};
		
		public void setMask(int Maskx, int Masky, int Maskw, int Maskh){
			this.Maskx = Maskx;
			this.Masky = Masky;
			this.Maskw = Maskw;
			this.Maskh = Maskh;
			
		}
		
		public void setX(int newX){
			this.x = newX;
		}
		
		public void setY(int newY){
			this.y = newY;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getWidth(){
			return width;
		}
		
		public int getHeigh(){
			return height;
		}
		
		public void update(){
			
		}
		
		public static boolean isColidding(Entidades e1, Entidades e2){
			Rectangle e1Mask = new Rectangle(e1.getX() + e1.Maskx, e1.getY() + e1.Masky, e1.Maskw, e1.Maskh);
			Rectangle e2Mask = new Rectangle(e2.getX() + e2.Maskx, e2.getY() + e2.Masky, e2.Maskw, e2.Maskh);
			
			return e1Mask.intersects(e2Mask);
		}
		
		public void render(Graphics graficos){
			
			graficos.drawImage(image, this.getX(), this.getY(), null);
			
			//graficos.setColor(Color.red);
			//graficos.fillRect(this.getX()+Maskx - Camera.x, this.getY()+Masky - Camera.y, Maskw, Maskh);
		}
		
		

}
