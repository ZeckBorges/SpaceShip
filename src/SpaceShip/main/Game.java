package SpaceShip.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import SpaceShip.Entidades.*;
import SpaceShip.Spans.*;
import SpaceShip.Assets.*;
import SpaceShip.Interface.*;


public class Game extends Canvas implements Runnable, KeyListener {

 
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
    public static final int Width = 600;
    public static final int Height = 600;
    public static final int Scale = 1;
    private boolean isRunning;
    private Thread thread;
    
    private BufferedImage image;
    
    public static String GameState = "Menu";
    
    //Background fase 1
    public BufferedImage background;
    public BufferedImage background2;
    public int backY = 0;
    public int backY2 = -600;
    public int backspeed = 1;
    
    //Background fase 2
    public BufferedImage fase2;
    public BufferedImage fase2_2;
    public int level2 = 0;
    public int level2_2 = -600;
    
  //Background fase 3
    public BufferedImage fase3;
    public BufferedImage fase3_3;
    public int level3 = 0;
    public int level3_3 = -600;
    
    public static int score = 0;
    public static double life = 0;
    public UI ui;
    
    public static List<Entidades> entidades;
    public static Player player;
    public static Assets sprites;
    public static Assets enemies;
    public static Assets explosion;
    public Menu menu;
    
    public EnemySpan span;
    
    public Game() {
    	addKeyListener(this);
        setPreferredSize(new Dimension(Width * Scale, Height * Scale));
        initFrame();
        image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        entidades = new ArrayList<Entidades>();
        sprites = new Assets("/ship254.png");
        enemies = new Assets("/ship612.png");
        explosion = new Assets("/explosion-6.png");
        player = new Player(Width/2, Height/2, 70,60, sprites.getSprite(0, 0, 40, 70));
        entidades.add(player);
        span = new EnemySpan();
        
        //Background fase 1
        try {
			background = ImageIO.read(getClass().getResource("/Nebula Blue.png"));
			background2 = ImageIO.read(getClass().getResource("/Nebula Blue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //Background fase 2
        try {
			fase2 = ImageIO.read(getClass().getResource("/Nebula Aqua-Pink.png"));
			fase2_2 = ImageIO.read(getClass().getResource("/Nebula Aqua-Pink.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
      //Background fase 3
        try {
			fase3 = ImageIO.read(getClass().getResource("/Nebula Red.png"));
			fase3_3 = ImageIO.read(getClass().getResource("/Nebula Red.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        ui = new UI();
        
        menu = new Menu();
        
        
    }
    
    public void initFrame(){
         //frame.setIconImage();
        frame = new JFrame("Space Ship"); //título
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    
     public synchronized void stop(){
         isRunning = false;
         try {
			thread.join();
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
     }

    public void update() {
    	if(GameState == "Menu"){
    		menu.update();
    	}
    	
    	//Controlando fases.
    	if(GameState == "Fase1" && score == 10){
    		GameState = "Fase2";
    		score = 0;
    	}
    	
    	if(GameState == "Fase2" && score == 12){
    		GameState = "Fase3";
    		score = 0;
    	}
    	
    	if(GameState == "Ranked"){
    		span.update();
    		ui.update();
        	for (int i = 0; i < entidades.size(); i++){
        		Entidades e = entidades.get(i);
        		e.update();
        	}
        	
        	//Background paralax
        	backY += backspeed;
        	if(backY >= Height){
        		backY = -600;
        	}
        	
        	backY2 += backspeed;
        	if(backY2 >= Height){
        		backY2 = -600;
        	}
    	}
    	
    	if(GameState == "Fase1"){
    		span.update();
    		ui.update();
        	for (int i = 0; i < entidades.size(); i++){
        		Entidades e = entidades.get(i);
        		e.update();
        	}
        	
        	//Background paralax
        	backY += backspeed;
        	if(backY >= Height){
        		backY = -600;
        	}
        	
        	backY2 += backspeed;
        	if(backY2 >= Height){
        		backY2 = -600;
        	}
    	}
    	
    	if(GameState == "Fase2"){
    		span.update();
    		ui.update();
        	for (int i = 0; i < entidades.size(); i++){
        		Entidades e = entidades.get(i);
        		e.update();
        	}
        	
        	//Background paralax
        	level2 += backspeed;
        	if(level2 >= Height){
        		level2 = -600;
        	}
        	
        	level2_2 += backspeed;
        	if(level2_2 >= Height){
        		level2_2 = -600;
        	}
    	}
    	
    	if(GameState == "Fase3"){
    		span.update();
    		ui.update();
        	for (int i = 0; i < entidades.size(); i++){
        		Entidades e = entidades.get(i);
        		e.update();
        	}
        	
        	//Background paralax
        	level3 += backspeed;
        	if(level3 >= Height){
        		level3 = -600;
        	}
        	
        	level3_3 += backspeed;
        	if(level3_3 >= Height){
        		level3_3 = -600;
        	}
    	}
    	
    	
    }

    public void render() {
         BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics graficos = image.getGraphics();
        graficos.setColor(new Color(0,0,20));
        graficos.fillRect(0, 0, Width*Scale, Height*Scale);
        
        if(GameState == "Fase1"){
        	graficos.drawImage(background, 0, backY, null);
            graficos.drawImage(background, 0, backY2, null);
        }
        if(GameState == "Fase2"){
        	graficos.drawImage(fase2, 0, level2, null);
            graficos.drawImage(fase2_2, 0, level2_2, null);
        }
        if(GameState == "Fase3"){
        	graficos.drawImage(fase3, 0, level3, null);
            graficos.drawImage(fase3_3, 0, level3_3, null);
        }
        if(GameState == "Ranked"){
        	graficos.drawImage(background, 0, backY, null);
            graficos.drawImage(background, 0, backY2, null);
        }
        
        //renderização do jogo
        //mapas.render(graficos);
        Collections.sort(entidades, Entidades.nodeSorter);
        for (int i = 0; i < entidades.size(); i++){
        	Entidades e = entidades.get(i);
        	e.render(graficos);
        }
        graficos.dispose();
        graficos = bs.getDrawGraphics();
        graficos.drawImage(image,0,0,Width*Scale, Height*Scale,null);
        ui.render(graficos);
        
        if(GameState == "Menu"){
        	menu.render(graficos);
        }
        
        bs.show();
    }

    @Override
    public void run() {
        long lastime = System.nanoTime();
        double update = 60.0;
        double nanosegundo = 1000000000 / update;
        double delta = 0;
        int frames = 0;
        double time = System.currentTimeMillis();
        requestFocus();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastime) / nanosegundo;
            lastime = now;
            if (delta >= 1) {
                update();
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - time >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                time += 1000;
                
            }
        }
        stop();
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = true;
			
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.up = true;
			if(GameState == "Menu"){
				menu.up = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = true;
			if(GameState == "Menu"){
				menu.down = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			player.shoot = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(GameState == "Menu"){
				menu.enter = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;	
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

