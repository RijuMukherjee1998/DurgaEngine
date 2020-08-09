package MainGame;

import durga.engine.io.Window;

public class Main implements Runnable{
	public Thread game;
	private int WIDTH=1280;
	private int HEIGHT=720;
	private String TITLE="Durga Engine";
	public void start() {
		game=new Thread(this,"game");
		game.start();
	}
	
	
	
	public void run() {
		Window window =new Window(WIDTH,HEIGHT,TITLE);
		window.create();
	}
	
	public void update() {
		System.out.println("Updating game...");
	}
	
	public void render() {
		System.out.println("Rendering Game...");
	}
	
	public static void main(String[] args) {
		new Main().start();
	}

}
