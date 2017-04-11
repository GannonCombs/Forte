package edu.virginia.lab1test;

/**
 * Created by ouchouyang on 2017/03/30.
 */

//Test comment

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

//whatever
public class FortePrototype extends Game {
    static int gravity = 3;
    boolean falling = false;
    static int gameWidth = 1040;
    static int gameHeight = 920;
    boolean collision = false;
    boolean plat_top = false;
    private ArrayList<Sprite> particle = new ArrayList<Sprite>();

    Sprite follow = new Sprite("follow","music_note.png");
    Sprite player = new Sprite("Player", "Protagnist.png");
    Sprite floor_1 = new Sprite("Floor", "Brick.png");
    Sprite floor_2 = new Sprite("Floor", "Brick.png");
    Sprite floor_3 = new Sprite("Floor", "Brick.png");
    Sprite floor_4 = new Sprite("Floor", "Brick.png");
    Sprite floor_5 = new Sprite("Floor", "Brick.png");
    Sprite floor_6 = new Sprite("Floor", "Brick.png");
    Sprite floor_7 = new Sprite("Floor", "Brick.png");
    Sprite floor_8 = new Sprite("Floor", "Brick.png");
    Sprite C = new Sprite("C","C.png");
    Sprite D = new Sprite("D","D.png");
    Sprite E = new Sprite("E","E.png");
    Sprite F = new Sprite("F","F.png");
    Cevent cevent = new Cevent(Cevent.Cevent,C);
    Devent devent = new Devent(Devent.Devent,D);
    Eevent eevent = new Eevent(Eevent.Eevent,E);
    Fevent fevent = new Fevent(Fevent.Fevent,F);


    Sprite[] objects = {
            floor_1, floor_2, floor_3, floor_4, floor_5, floor_6, floor_7, floor_8};
    Sprite[] enemies = {C,D,E,F};

    MusicPlayer sfx = new MusicPlayer();
    QuestManager QuestManager = new QuestManager(C,D,E,F,sfx);

    public FortePrototype(){
        super("Forte Prototype", gameWidth, gameHeight);
        player.setPosition(1, gameHeight - 150);
        floor_1.setPosition(0, gameHeight - 100);
        floor_2.setPosition(130, gameHeight - 100);
        floor_3.setPosition(260, gameHeight - 100);
        floor_4.setPosition(390, gameHeight - 100);
        floor_5.setPosition(520, gameHeight - 100);
        floor_6.setPosition(650, gameHeight - 100);
        floor_7.setPosition(780, gameHeight - 100);
        floor_8.setPosition(910, gameHeight - 100);
        C.setPosition(260, gameHeight - 175);
        D.setPosition(520, gameHeight - 175);
        E.setPosition(780, gameHeight - 175);
        F.setPosition(910, gameHeight - 175);

        C.addEventListener(QuestManager, Cevent.Cevent);
        D.addEventListener(QuestManager, Devent.Devent);
        E.addEventListener(QuestManager, Eevent.Eevent);
        F.addEventListener(QuestManager, Fevent.Fevent);
       // player.addChild(follow);
       // follow.setPosition(100,0);
       // follow.setPivotPoint(player.getPosition());


    }

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);
        player.update(pressedKeys);
        player.rePosition(player.getVelX(), player.getVelY());
        if (falling) player.setVelY(player.getVelY() + (int) gravity);



        follow.setRotation(follow.getRotation()+10);
        Position p1 = player.getPosition();

        // Checking map bounds
        if (p1.getX() <= 0) player.setPosition(0, p1.getY());
        if (p1.getX() >= gameWidth - player.getScaledWidth()) player.setPosition(gameWidth - player.getScaledWidth() - 1, p1.getY());
        if (p1.getY() <= 0) player.setPosition(p1.getX(), 0);
        if (p1.getY() >= gameHeight - player.getScaledHeight() - 1) {
            player.setPosition(p1.getX(), gameHeight - player.getScaledHeight());
            falling = false;
        }

        for (Sprite s : objects) {
            if (player.nearby(s)) {
                if (player.collidesWith(s, player.getVelX(), player.getVelY())) {
                    collision = true;
                    if (s.getId() == "Floor") {
                        player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                        player.setVelY(0);
                        falling = false;
                        break;
                    }
                    /**
                    if (s.getId() == "Platform") {
                        // Landing on top
                        if (player.getPosY() + player.getScaledHeight() - player.getVelY() <= s.getPosY()) {
                            falling = false;
                            plat_top = true;
                            player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                            break;
                        } else {
                            // Left side
                            if (player.getPosX() + player.getScaledWidth() >= s.getPosX()
                                    && player.getPosX() + player.getScaledWidth() < s.getPosX() + s.getScaledWidth() / 2) {
                                player.setPosition(s.getPosX() - player.getScaledWidth() - 1, player.getPosY());
                                falling = true;
                                plat_top = false;
                                break;
                            }
                            // Right side
                            if (player.getPosX() <= s.getPosX() + s.getScaledWidth() && player.getPosX() > s.getPosX() + s.getScaledWidth() * 0.5) {
                                player.setPosition(s.getPosX() + s.getScaledWidth() + 1, player.getPosY());
                                falling = true;
                                plat_top = false;
                                break;
                            }
                            // Below
                            if (player.getPosY() >= s.getPosY() + s.getScaledHeight() && player.getPosY() > s.getPosY()) {
                                player.setPosition(player.getPosX(), s.getPosY() + s.getScaledHeight() + 1);
                                falling = true;
                                plat_top = false;
                                break;
                            }
                        }
                    }
                     */
                } else {
                    collision = false;
                }
            }
        }

        for (Sprite e : enemies) {
            if (player.nearby(e)) {
                if (player.collidesWith(e, player.getVelX(), player.getVelY())) {

                    if (e.getId() == "C"){
                        C.dispatchEvent(cevent);
                    }
                    else if (e.getId() == "D"){
                        D.dispatchEvent(devent);

                    }
                    else if (e.getId() == "E"){
                        E.dispatchEvent(eevent);

                    }
                    else if (e.getId() == "F"){
                        F.dispatchEvent(fevent);
                    }
                    else {
                        ;
                    }
                }

            }
        }

        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();

        for (Sprite p: particle){
            p.rePosition(p.getVelX(), p.getVelY());
            p.setVelY(p.getVelY() + (int) gravity);
            for (Sprite e: enemies){
                if (e.collidesWith(p,e.getVelX(),e.getVelY())) {
                    toRemove.add(p);
                    falling = true;
                    player.setPosition(p.getPosition());
                    //p.setVisible(false);
                    // particle.remove(p);
                    if (e.getId() == "C") {
                        C.dispatchEvent(cevent);
                    }
                    if (e.getId() == "D") {
                        D.dispatchEvent(devent);
                    }

                    if (e.getId() == "E") {
                        E.dispatchEvent(eevent);
                    }
                    if (e.getId() == "F") {
                        F.dispatchEvent(fevent);
                    }


                }


            }
           // particle.remove(p);
        }
        particle.removeAll(toRemove);


    }

    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.setVelY(-37);
                falling = true;
                sfx.playSong("bass1.wav",0);

            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.setVelX(-15);

            }

            if (e.getKeyCode() == KeyEvent.VK_A){
                //player.setVelY(-30);
                //player.setVelX(-40);
                sfx.playSong("bass2.wav",-15);
                Sprite bullet = new Sprite("bullet", "music_note.png");
                bullet.setPosition(player.getPosition());
                bullet.setVelY(-20);
                bullet.setVelX(-30);
                particle.add(bullet);
                falling = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_S){
               // player.setVelY(-30);
               // player.setVelX(40);
                sfx.playSong("bass2.wav",-15);
                Sprite bullet = new Sprite("bullet", "music_note.png");
                bullet.setPosition(player.getPosition());
                bullet.setVelY(-20);
                bullet.setVelX(30);
                particle.add(bullet);
                falling = true;
            }

        if (e.getKeyCode() == KeyEvent.VK_Z){
            player.setVelY(30);
            player.setVelX(-40);

            falling = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_X){
            player.setVelY(30);
            player.setVelX(40);

            falling = true;
        }


            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.setVelX(15);
            }
        if (e.getKeyCode() == KeyEvent.VK_V) {
            sfx.playSong("Chord_seven.wav",-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            sfx.playSong("Am6.wav",-10);

        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            sfx.playSong("Chord_four.wav",-10);

        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            sfx.playSong("Ab9.wav",-10);

        }


    }

    public void keyReleased(KeyEvent e) {
        player.setVelX(0);
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        if(player != null) player.draw(g);
        if(floor_1 != null) floor_1.draw(g);
        if(floor_2 != null) floor_2.draw(g);
        if(floor_3 != null) floor_3.draw(g);
        if(floor_4 != null) floor_4.draw(g);
        if(floor_5 != null) floor_5.draw(g);
        if(floor_6 != null) floor_6.draw(g);
        if(floor_7 != null) floor_7.draw(g);
        if(floor_8 != null) floor_8.draw(g);
        if(C != null) C.draw(g);
        if(D != null) D.draw(g);
        if(E != null) E.draw(g);
        if(F != null) F.draw(g);
       // if(follow != null) follow.draw(g);
        for (Sprite temp: particle){
            if(temp.isVisible()){
                temp.draw(g);
            }
        }

    }

    public static void main(String[] args) {
        FortePrototype game = new FortePrototype();
        MusicPlayer bgm = new MusicPlayer();
        game.start();


        GameClock clock = new GameClock();
        double songLength = 11573;
        bgm.playSong("game_bgm.wav", -10);
        while (true) {
            if (clock.getElapsedTime() > songLength) {
                clock.resetGameClock();
                bgm.playSong("game_bgm.wav", -10);
            }
        }


    }
}
