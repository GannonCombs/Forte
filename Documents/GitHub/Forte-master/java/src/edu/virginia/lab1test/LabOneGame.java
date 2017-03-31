package	 edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.Position;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.MusicPlayer;


//Gannon Combs was here.

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game{
	Sprite sheet = new Sprite("Spritesheet", "Spritesheet.png");
	BufferedImage[] anim = {sheet.getSprite(0,0), sheet.getSprite(1,0), sheet.getSprite(0,1), sheet.getSprite(1,1)};
	AnimatedSprite mario = new AnimatedSprite("Mario", anim, 12, 0, 1);
	Sprite coin = new Sprite("Coin", "Coin.png");
	Sprite plat_1 = new Sprite("Platform", "Brick.png");
	Sprite plat_2 = new Sprite("Platform", "Brick.png");
	Sprite plat_3 = new Sprite("Platform", "Brick.png");
	Sprite floor_1 = new Sprite("Floor", "Brick.png");
	Sprite floor_2 = new Sprite("Floor", "Brick.png");
	Sprite floor_3 = new Sprite("Floor", "Brick.png");
	Sprite floor_4 = new Sprite("Floor", "Brick.png");
	Sprite floor_5 = new Sprite("Floor", "Brick.png");
	Sprite floor_6 = new Sprite("Floor", "Brick.png");
	Sprite floor_7 = new Sprite("Floor", "Brick.png");
	Sprite floor_8 = new Sprite("Floor", "Brick.png");
	Sprite[] platforms = {plat_1, plat_2, plat_3};
	Sprite[] floors = {floor_1, floor_2, floor_3, floor_4, floor_5, floor_6, floor_7, floor_8};
	Sprite[] objects = {plat_1, plat_2, plat_3,
			floor_1, floor_2, floor_3, floor_4, floor_5, floor_6, floor_7, floor_8};
	QuestManager QuestManager = new QuestManager();
	CoinPickedUp CoinQuest = new CoinPickedUp(CoinPickedUp.message);
	MusicPlayer sfx = new MusicPlayer();
	boolean falling = false;
	boolean collision = false;
	boolean plat_top = false;
	static int gravity = 3;
	static int gameWidth = 1040;
	static int gameHeight = 920;
	boolean coinAnim = false;
	boolean spawn = false;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", gameWidth, gameHeight);
		mario.setPosition(1, gameHeight - 240);
		mario.setAlpha(0);
		coin.setPosition(790, 150);
		plat_1.setPosition(260, 692);
		plat_2.setPosition(505, 532);
		plat_3.setPosition(760, 402);
		floor_1.setPosition(0, gameHeight - 100);
		floor_2.setPosition(130, gameHeight - 100);
		floor_3.setPosition(260, gameHeight - 100);
		floor_4.setPosition(390, gameHeight - 100);
		floor_5.setPosition(520, gameHeight - 100);
		floor_6.setPosition(650, gameHeight - 100);
		floor_7.setPosition(780, gameHeight - 100);
		floor_8.setPosition(910, gameHeight - 100);
		coin.setScaleXY(0.25);
		coin.addEventListener(QuestManager, CoinPickedUp.message);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		
		if (mario != null) {
			
			if (!spawn) {
				if (mario.getAlpha() < 0.97) mario.setAlpha((float) (mario.getAlpha() + 0.03));
				else spawn = true;
			}
			
			Position p = mario.getPosition();
			
			// Checking map bounds
			if (p.getX() <= 0) mario.setPosition(0, p.getY());
			if (p.getX() >= gameWidth - mario.getScaledWidth()) mario.setPosition(gameWidth - mario.getScaledWidth() - 1, p.getY());
			if (p.getY() <= 0) mario.setPosition(p.getX(), 0);
			if (p.getY() >= gameHeight - mario.getScaledHeight() - 1) {
				mario.setPosition(p.getX(), gameHeight - mario.getScaledHeight());
				falling = false;
			}

			for (Sprite s : objects) {
				if (mario.nearby(s)) {
					if (mario.collidesWith(s, mario.getVelX(), mario.getVelY())) {
						collision = true;
						if (s.getId() == "Floor") {
							mario.setPosition(mario.getPosX(), s.getPosY() - mario.getScaledHeight() - 1);
							mario.setVelY(0);
							falling = false;
							break;
						}
						if (s.getId() == "Platform") {
							// Landing on top
							if (mario.getPosY() + mario.getScaledHeight() - mario.getVelY() <= s.getPosY()) {
								falling = false;
								plat_top = true;
								mario.setPosition(mario.getPosX(), s.getPosY() - mario.getScaledHeight() - 1);
								break;
							}
							else {
								// Left side
								if (mario.getPosX() + mario.getScaledWidth() >= s.getPosX() 
										&& mario.getPosX() + mario.getScaledWidth() < s.getPosX() + s.getScaledWidth() / 2) {
									mario.setPosition(s.getPosX() - mario.getScaledWidth() - 1, mario.getPosY());
									falling = true;
									plat_top = false;
									break;
								}
								// Right side
								if (mario.getPosX() <= s.getPosX() + s.getScaledWidth() && mario.getPosX() > s.getPosX() + s.getScaledWidth() * 0.5) {
									mario.setPosition(s.getPosX() + s.getScaledWidth() + 1, mario.getPosY());
									falling = true;
									plat_top = false;
									break;
								}
								// Below
								if (mario.getPosY() >= s.getPosY() + s.getScaledHeight() && mario.getPosY() > s.getPosY()) {
									mario.setPosition(mario.getPosX(), s.getPosY() + s.getScaledHeight() + 1);
									falling = true;
									plat_top = false;
									break;
								}
							}
						}
					}
					else {
						collision = false;
					}
				}
			}

			if (falling) mario.setVelY(mario.getVelY() + (int) gravity);
			if (!collision) mario.rePosition(mario.getVelX(), mario.getVelY());
			else if (collision && plat_top) mario.rePosition(mario.getVelX(), 0);
			
			if (coin != null) {
				Position q = coin.getPosition();
				if(p.getX() >= q.getX() + 50 - mario.getScaledWidth() && p.getX() <= q.getX() + 50 + coin.getScaledWidth()) {
					if(p.getY() >= q.getY() - mario.getScaledHeight() && p.getY() <= q.getY() + coin.getScaledHeight()) {
						if(!coinAnim) sfx.playSong("Coin.wav", -5);
						coin.setVelX(-15);
						coin.setVelY(5);
						coinAnim = true;
//						coin.setVisible(false);
//						coin.setPosition(1080,720);
//						CoinQuest.setCompleted(true);
//						coin.dispatchEvent(CoinQuest);
					}
				}
			}	
			
			if (coin.getPosX() > gameWidth / 2 - 100) {
				coin.rePosition(coin.getVelX(), coin.getVelY());
				if(coinAnim) coin.setScaleXY(coin.getScaleX() + 0.02);
			}
			else {
				if(coinAnim && coin.getAlpha() > 0.01) coin.setAlpha((float) (coin.getAlpha() - 0.02));
			}
			mario.update();
			coin.update(pressedKeys);
			mario.update(pressedKeys);
		}
	}

	public void keyPressed(KeyEvent e) {		
		if(spawn) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (!falling) {
					mario.setVelY(-37);
					sfx.playSong("Jump.wav", -2);
				}
				falling = true;
				mario.animate();
	//			System.out.println(mario.getPosY());
			}
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				mario.setVelX(-8);
				mario.setStartInd(2);
				mario.setEndInd(3);
				mario.animate();
	//			System.out.println(mario.getVelX());
			}
			
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				mario.setVelX(8);
				mario.setStartInd(0);
				mario.setEndInd(1);
				mario.animate();
	//			System.out.println(mario.getVelX());
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		mario.stop();
		mario.reset();
		mario.setVelX(0);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		if(mario != null) mario.draw(g);
		if(coin != null) coin.draw(g);
		if(plat_1 != null) plat_1.draw(g);
		if(plat_2 != null) plat_2.draw(g);
		if(plat_3 != null) plat_3.draw(g);
		if(floor_1 != null) floor_1.draw(g);
		if(floor_2 != null) floor_2.draw(g);
		if(floor_3 != null) floor_3.draw(g);
		if(floor_4 != null) floor_4.draw(g);
		if(floor_5 != null) floor_5.draw(g);
		if(floor_6 != null) floor_6.draw(g);
		if(floor_7 != null) floor_7.draw(g);
		if(floor_8 != null) floor_8.draw(g);
	}
	
	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		MusicPlayer bgm = new MusicPlayer();
		bgm.playSong("Mario Theme.wav", -10);
		game.start();
	}
}
