package edu.virginia.lab1test;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.lab1test.CoinPickedUp;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.util.MusicPlayer;

import java.util.ArrayList;

public class QuestManager implements IEventListener {
	private Sprite C;
	private Sprite D;
	private Sprite E;
	private Sprite F;
	private MusicPlayer musicPlayer;
	private ArrayList<Sprite> list = new ArrayList<Sprite>();
	public QuestManager(Sprite C, Sprite D, Sprite E, Sprite F,  MusicPlayer musicPlayer){
		this.C = C;
		this.D = D;
		this.E = E;
		this.F = F;
		this.musicPlayer = musicPlayer;
		list.add(C);
		list.add(D);
		list.add(E);
		list.add(F);
	}

	@Override
	public void handleEvent(Event event) {
		DisplayObject source = (DisplayObject) event.getSource();
		if (source == C) {
			if (C.isVisible()) {
				C.setVisible(false);
				//musicPlayer.playSong("Chord_seven.wav",-20);
				musicPlayer.playSong("C.wav",-20);

				int random = (int) (Math.random() * ((910) + 1));
				/**
				for (Sprite s: list) {

                    while (random< s.getPosY() || random > (s.getPosY() + s.getUnscaledWidth()) ){

                    }
                }
                 */
                C.setPosition(random, 920 - 175);
                C.setVisible(true);

            }
		}
		else if(source == D){
			if (D.isVisible()) {
				D.setVisible(false);
				//musicPlayer.playSong("Am6.wav",-20);
				musicPlayer.playSong("D.wav",-20);

				int random =  (int)(Math.random() * ((910) + 1));
                D.setPosition(random, 920 - 175);
                D.setVisible(true);
            }
		}
        else if(source == E){
            if (E.isVisible()) {
                E.setVisible(false);
              //  musicPlayer.playSong("Chord_four.wav",-20);
				musicPlayer.playSong("E.wav",-20);

				int random =  (int)(Math.random() * ((910) + 1));
                E.setPosition(random, 920 - 175);
                E.setVisible(true);
            }
        }
        else if(source == F){
            if (F.isVisible()) {
                F.setVisible(false);
               // musicPlayer.playSong("Ab9.wav",-20);
				musicPlayer.playSong("F.wav",-20);

				int random =  (int)(Math.random() * ((910) + 1));
                F.setPosition(random, 920 - 175);
                F.setVisible(true);
            }
        }
		else {
            ;
		}
	}
}
