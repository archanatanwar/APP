import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class GameBoard implements Runnable{
	
	private Panel panel;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public GameBoard(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init(){
		panel = new Panel(title, width, height);
		AssetsDepot.Set_Asset();
	}
	
	
	
	private void render(int NoOfPlayers){
		bs = panel.getCanvas().getBufferStrategy();
		if(bs == null){
			panel.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		g.drawImage(AssetsDepot.board, 0, 0, null);
		g.drawImage(AssetsDepot.title1, 0, -205, null);
		g.drawImage(AssetsDepot.title2, 0, 510, null);
		
		//Draw Here!
		if(NoOfPlayers==4){
		
		g.drawImage(AssetsDepot.minion_r, 290, 100, null);
		g.drawImage(AssetsDepot.minion_g, 330, 110, null);
		g.drawImage(AssetsDepot.minion_b, 360, 130, null);
		g.drawImage(AssetsDepot.minion_y, 390, 160, null);
		g.drawImage(AssetsDepot.troubleMarker, 420, 165, null);
		
		g.drawImage(AssetsDepot.minion_r, 370, 370, null);
		g.drawImage(AssetsDepot.minion_g, 400, 400, null);
		g.drawImage(AssetsDepot.minion_b, 420, 430, null);
		g.drawImage(AssetsDepot.minion_y, 380, 460, null);
		g.drawImage(AssetsDepot.troubleMarker, 300, 450, null);
		
		g.drawImage(AssetsDepot.minion_r, 330, 490, null);
		g.drawImage(AssetsDepot.minion_g, 360, 520, null);
		g.drawImage(AssetsDepot.minion_b, 360, 550, null);
		g.drawImage(AssetsDepot.minion_y, 360, 580, null);
		g.drawImage(AssetsDepot.troubleMarker, 330, 600, null);
		
		}
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		while(running){
			
			render(4); // calling this function with different players number
		}
		
		
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
