/**
 * Created by GannonCombs on 4/10/2017.
 */

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.music.tools.Mod;
import jm.util.Play;
import jm.JMC;
import jm.util.Write;


public class Music implements JMC{
    // declare instance variables
    Part flute, bowed_glass, clarinet;
    Phrase phrase1, phrase2, phrase3;
    Score score;
    int[] pitchArray;
    double[] rhythmArray;

    public static void main(String[] args){

        //Note note = new Note();
        //note.setPitch(55); //higher number = higher pitch.
        //note.setPitch(JMC.C4); //this is G sharp in the 3rd octave.
        //note.setPitch(JMC.PP); // volume of note
       // note.setLength(3); //number is seconds
        //note.setDuration(1); // 1 = quarter note, 2 = half note, etc.
        //note.setDuration(JMC.EIGHTH_NOTE); //alternative

        //Play.midi(note);

        Music song = new Music();
        song.makeMusicData();
        song.structureData();
        song.playAndSave();
    }

    public Music() {
        //bowed_glass = new Part(bowed_glass, 0);
        bowed_glass = new Part(BOWED_GLASS, 1);
        //clarinet = new Part(CLARINET, 2);
        // create empty phrases
        phrase1 = new Phrase(0.0);
        //phrase2 = new Phrase(0.0);
        //phrase3 = new Phrase(0.0);
        //Create the data objects we want to use
        score = new Score("D4_quarter_bowed_glass", 120);
        //Lets write the music in a convenient way.
        pitchArray = new int[] {D4};
        rhythmArray = new double[] {QN};
    }

    public void structureData() {
        //add phrases to the parts
        //bowed_glass.addPhrase(phrase1);
        //bowed_glass.addPhrase(phrase2);
        //clarinet.addPhrase(phrase3);
        bowed_glass.addPhrase(phrase1);

        //add parts to the score
        //score.addPart(bowed_glass);
        score.addPart(bowed_glass);
        //score.addPart(clarinet);
    }

    public void makeMusicData() {
        //add the notes to a phrase
        phrase1.addNoteList(pitchArray, rhythmArray);
        Mod.transpose(phrase1, 12);
    }

    private void playAndSave() {
        //Now we do a SMF write
        Write.midi(score, "D4_quarter_bowed_glass.wav");
        //Now play it back

        Play.midi(score);
        //Play.midi(score);

    }

}
