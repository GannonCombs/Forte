/**
 * Created by GannonCombs on 4/10/2017.
 */

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.JMC;


public class Music implements JMC{
    // declare instance variables
    Part flute, trumpet, clarinet;
    Phrase phrase1, phrase2, phrase3;
    Score score;
    int[] pitchArray;
    double[] rhythmArray;

    public static void main(String[] args){

        Note note = new Note();
        note.setPitch(55); //higher number = higher pitch.
        note.setPitch(JMC.C4); //this is G sharp in the 3rd octave.
        note.setPitch(JMC.PP); // volume of note
        note.setLength(3); //number is seconds
        note.setDuration(1); // 1 = quarter note, 2 = half note, etc.
        note.setDuration(JMC.EIGHTH_NOTE); //alternative
        Play.midi(note);

        Music song = new Music();
        //song.makeMusicData();
        //song.structureData();
        //song.playAndSave();
    }
}
