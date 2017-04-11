package edu.virginia.lab1test;

/**
 * Created by ouchouyang on 2017/03/30.
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.Position;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.MusicPlayer;


public class FortePrototype extends Game {
    static int gravity = 3;
    boolean falling = false;
    static int gameWidth = 1040;
    static int gameHeight = 920;
    boolean collision = false;
    boolean plat_top = false;
    private ArrayList<Sprite> particle = new ArrayList<Sprite>();

    //   Sprite follow = new Sprite("follow","music_note.png");
    Sprite player = new Sprite("Player", "Protagnist.png");
    /**
     Sprite floor_1 = new Sprite("Floor", "Brick.png");
     Sprite floor_2 = new Sprite("Floor", "Brick.png");
     Sprite floor_3 = new Sprite("Floor", "Brick.png");
     Sprite floor_4 = new Sprite("Floor", "Brick.png");
     Sprite floor_5 = new Sprite("Floor", "Brick.png");
     Sprite floor_6 = new Sprite("Floor", "Brick.png");
     Sprite floor_7 = new Sprite("Floor", "Brick.png");
     Sprite floor_8 = new Sprite("Floor", "Brick.png");
     */
    Sprite C = new Sprite("C","C.png");
    Sprite D = new Sprite("D","D.png");
    Sprite E = new Sprite("E","E.png");
    Sprite F = new Sprite("F","F.png");
    Cevent cevent = new Cevent(Cevent.Cevent,C);
    Devent devent = new Devent(Devent.Devent,D);
    Eevent eevent = new Eevent(Eevent.Eevent,E);
    Fevent fevent = new Fevent(Fevent.Fevent,F);

    // private ArrayList<Sprite> objects = {
    //       floor_1, floor_2, floor_3, floor_4, floor_5, floor_6, floor_7, floor_8};

    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    // Sprite[] enemies = {C,D,E,F};
    private ArrayList<Sprite> enemies = new ArrayList<Sprite>();
    // Sprite[] moving = {floor_1, floor_2, floor_3, floor_4, floor_5, floor_6, floor_7, floor_8,C,D,E,F};
    private ArrayList<Sprite> moving = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving2 = new ArrayList<Sprite>();

    MusicPlayer sfx = new MusicPlayer();
    MusicPlayer pC = new MusicPlayer();
    MusicPlayer pG = new MusicPlayer();
    MusicPlayer pA = new MusicPlayer();
    MusicPlayer pD = new MusicPlayer();

    boolean c = false;
    boolean g = false;
    boolean a = false;
    boolean d = false;

    private GameClock mainClock  = new GameClock();

    QuestManager QuestManager = new QuestManager(C,D,E,F,sfx,mainClock);
    public FortePrototype(){
        super("Forte Prototype", gameWidth, gameHeight);
        player.setPosition(500, gameHeight - 150);
        C.setPosition(260, gameHeight - 175);
        D.setPosition(700, gameHeight - 175);
        E.setPosition(780, gameHeight - 175);
        F.setPosition(910, gameHeight - 175);

        C.addEventListener(QuestManager, Cevent.Cevent);
        D.addEventListener(QuestManager, Devent.Devent);
        E.addEventListener(QuestManager, Eevent.Eevent);
        F.addEventListener(QuestManager, Fevent.Fevent);


        //  ArrayList<Integer> CxArray = Arrays.asList(500,2000,4000);
 
        int[] CxArray = { 1500,1980};
        int[] CyArray = { gameHeight-175,410};
        
        int[] DxArray = { 1600,2100};
        int[] DyArray = { gameHeight - 175,gameHeight - 175};
        
        int[] ExArray = { 1700,2200};
        int[] EyArray = { gameHeight - 175,gameHeight - 175};
        
        int[] FxArray = { 1800,2300};
        int[] FyArray = { gameHeight - 175,gameHeight - 175};

        C.setxArray(CxArray);
        C.setyArray(CyArray);
        D.setxArray(DxArray);
        D.setyArray(DyArray);
        E.setxArray(ExArray);
        E.setyArray(EyArray);
        F.setxArray(FxArray);
        F.setyArray(FyArray);

        /**
         * background and floor
         */
        setFloor(100);
        setBackground(10);

        enemies.add(C);
        enemies.add(D);
        enemies.add(E);
        enemies.add(F);
        moving.add(player);
        mainClock.resetGameClock();

        /**
         * platform positions
         */
        setHorizontalPlatform(500,gameHeight-300,3);
        setHorizontalPlatform(2000,gameHeight-500,4);
        setHorizontalPlatform(3000,gameHeight-400,7);
        setVerticalPlatform(1000,3);
        setVerticalPlatform(1500,2);
        setDiagonalPlatform(2500,3, 1);

        //  moving.addAll(enemies);

    }


    public void setBackground(int number) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite("Background","background.png");
            temp.setPosition(temp.getUnscaledWidth()*i,0);
            moving2.add(temp);
        }
    }
    public void setHorizontalPlatform(int x, int y, int number){
        for (int i =0; i <number; i++){
            Sprite temp = new Sprite("Platform", "Brick.png");
            temp.setPosition(x+130*i,y);
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setVerticalPlatform(int x, int number){
        for (int i =0; i <number; i++){
            Sprite temp = new Sprite("Platform", "Brick.png");
            temp.setPosition(x,gameHeight-175-130*(i));
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setDiagonalPlatform(int x,int number, int isRight){
        for (int i =0; i <number; i++){
            Sprite temp = new Sprite("Platform", "Brick.png");
            temp.setPosition(x + isRight*i*temp.getUnscaledWidth(),gameHeight-175-130*(i));
            objects.add(temp);
            moving.add(temp);
        }

    }

    public void setFloor(int number){
        for (int i =0; i<number; i++) {
            Sprite temp = new Sprite("Floor", "Brick.png");
            temp.setPosition(i*130,gameHeight-100);
            objects.add(temp);
            moving.add(temp);
        }
    }


    public void timing(int start, int finish, Sprite temp){

        //  BufferedImage image = new BufferedImage(2,2,1);
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() > start && mainClock.getElapsedTime() < start + 500){
            temp.setImage("Flash.png");
            temp.setStart(start);
            temp.setFinish(finish);
            //bol = false;
        }

        if (mainClock.getElapsedTime() > finish && mainClock.getElapsedTime() <finish + 500){
            temp.setImage(temp.getPrev());
        }

    }

    public void timingMode(int start, int finish, Sprite temp){

        //  BufferedImage image = new BufferedImage(2,2,1);
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() % 500 > start && (mainClock.getElapsedTime() % 500) < start + 100){
            temp.setImage("Flash.png");
            temp.setStart(start);
            temp.setFinish(finish);
            //bol = false;
        }

        if (mainClock.getElapsedTime() % 500 > finish && (mainClock.getElapsedTime() % 500) <finish + 100){
            temp.setImage(temp.getPrev());
        }

    }

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);
        player.update(pressedKeys);
        player.rePosition(player.getVelX(), player.getVelY());
        if (falling) player.setVelY(player.getVelY() + (int) gravity);

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
            if (s.isVisible()) {
                if (player.nearby(s)) {
                    if (player.collidesWith(s, player.getVelX(), player.getVelY())) {
                        collision = true;
                        if (s.getId() == "Floor") {
                            player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                            player.setVelY(0);
                            falling = false;
                            break;
                        }

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

                    } else {
                        collision = false;
                    }
                }
            }
        }

        for (Sprite e : enemies) {
            if (e.isVisible()) {
                if (player.nearby(e)) {
                    if (player.collidesWith(e, player.getVelX(), player.getVelY())) {

                        if (e.getId() == "C") {
                            C.dispatchEvent(cevent);
                        } else if (e.getId() == "D") {
                            D.dispatchEvent(devent);

                        } else if (e.getId() == "E") {
                            E.dispatchEvent(eevent);

                        } else if (e.getId() == "F") {
                            F.dispatchEvent(fevent);
                        } else {
                            ;
                        }
                    }

                }
            }
        }

        timingMode(0,100,C);
        timingMode(0,100,D);
        timingMode(0,100,E);
        timingMode(0,100,F);


        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();

        for (Sprite p: particle){
            p.rePosition(p.getVelX(), p.getVelY());
            p.setVelY(p.getVelY() + (int) gravity);
            for (Sprite e: enemies) {
                if (e.isVisible()) {
                    if (e.collidesWith(p, e.getVelX(), e.getVelY())) {
                        toRemove.add(p);
                        falling = true;
                       // player.setPosition(p.getPosition());
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
            }
        }
        particle.removeAll(toRemove);



        for (Sprite p : moving) {
            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
        }
        for (Sprite p : moving2) {
            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
        }
        for (Sprite p: enemies)  {
            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
        }
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setVelX(15);

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


    }

    public void keyReleased(KeyEvent e) {
        player.setVelX(0);
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        for (Sprite p:moving2){
            if(p.isVisible()){
                p.draw(g);
            }
        }
        for (Sprite p: objects){
            if(p.isVisible()){
                p.draw(g);
            }
        }

        if(C != null) C.draw(g);
        if(D != null) D.draw(g);
        if(E != null) E.draw(g);
        if(F != null) F.draw(g);

        if(player != null) player.draw(g);
        for (Sprite temp: particle){
            if(temp.isVisible()){
                temp.draw(g);
            }
        }

    }

    public void setpC(MusicPlayer pC) {
        this.pC = pC;
    }

    public void setpG(MusicPlayer pG) {
        this.pG = pG;
    }

    public void setpA(MusicPlayer pA) {
        this.pA = pA;
    }

    public void setpD(MusicPlayer pD) {
        this.pD = pD;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public static void main(String[] args) {
        FortePrototype game = new FortePrototype();
        MusicPlayer bgm = new MusicPlayer();
        MusicPlayer C = new MusicPlayer();
        MusicPlayer G = new MusicPlayer();
        MusicPlayer A = new MusicPlayer();
        MusicPlayer D = new MusicPlayer();
        game.setpC(C);
        game.setpG(G);
        game.setpA(A);
        game.setpD(D);
        game.start();

        GameClock clock = new GameClock();
        GameClock clock2 = new GameClock();
        //  mainClock.resetGameClock();
        double songLength = 11573;
        // bgm.playSong("game_bgm.wav", -10);
        C.playSong("piano_c5.wav", -10);
        G.playSong("piano_g4.wav", -10);
        A.playSong("piano_a4.wav", -10);
        D.playSong("piano_d4.wav", -10);
        while (true) {
            if (clock.getElapsedTime() > songLength) {
                clock.resetGameClock();
                //  bgm.playSong("game_bgm.wav", -10);
            }
            if(clock2.getElapsedTime() > 2000){
                C.playSong("piano_c5.wav", -10);
                G.playSong("piano_g4.wav", -10);
                A.playSong("piano_a4.wav", -10);
                D.playSong("piano_d4.wav", -10);
                clock2.resetGameClock();
            }
        }


    }
}
