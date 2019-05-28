import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class keyboard extends JPanel implements KeyListener{
   private boolean cond;
   private boolean[] keysDown = new boolean[500];
   private ArrayList<String> notes = new ArrayList<String>(Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"));
   private  MidiChannel[] channels;
   private int instrument; 
   private  int volume;
   private int octave;
   
   public keyboard(){
      cond = true;
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      octave = 3;
      volume = 100;
      instrument = 1;
   }
   
   public void paint(Graphics g){
      try{
         Synthesizer synth = MidiSystem.getSynthesizer();
         synth.open();
         channels = synth.getChannels();
      }
      catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   public boolean getCond(){return cond;}
   
   public void increaseVol(){volume++;}
   public void decreaseVol(){volume--;}
   public void increaseOctave(){octave++;}
   public void decreaseOctave(){octave--;}
   
   public int id(String note){return notes.indexOf(note.substring(0))+12*octave+12;}
   public int id(String note,int x){return notes.indexOf(note.substring(0))+12*(octave-1)+12;}
   
   @Override
   public void keyPressed(KeyEvent e){
      int keyCode = e.getKeyCode();
      switch (keyCode){
         case KeyEvent.VK_ESCAPE: 
            cond = false;
            break;
         case KeyEvent.VK_UP: 
            increaseOctave();
            break;
         case KeyEvent.VK_DOWN:
            decreaseOctave();
            break;
         case KeyEvent.VK_MINUS:
            decreaseVol();
            break;
         case KeyEvent.VK_EQUALS:
            increaseVol();
            break;
         case KeyEvent.VK_A:
            channels[instrument].noteOn(id("G",0),volume);
            break;     
         case KeyEvent.VK_S:
            channels[instrument].noteOn(id("A",0),volume);
            break;  
         case KeyEvent.VK_D:
            channels[instrument].noteOn(id("B",0),volume);
            break; 
         case KeyEvent.VK_F:
            channels[instrument].noteOn(id("C"),volume);
            break;    
         case KeyEvent.VK_G:
            channels[instrument].noteOn(id("D"),volume);
            break;  
         case KeyEvent.VK_H:
            channels[instrument].noteOn(id("E"),volume);
            break;
         case KeyEvent.VK_J:
            channels[instrument].noteOn(id("F"),volume);
            break;
         case KeyEvent.VK_K:
            channels[instrument].noteOn(id("G"),volume);
            break;
         case KeyEvent.VK_Q:
            channels[instrument].noteOn(id("G#", 0),volume);
            break;
         case KeyEvent.VK_W:
            channels[instrument].noteOn(id("A#", 0),volume);
            break; 
         case KeyEvent.VK_R:
            channels[instrument].noteOn(id("C#"),volume);
            break; 
         case KeyEvent.VK_T:
            channels[instrument].noteOn(id("D#"),volume);
            break;  
       case KeyEvent.VK_U:
            channels[instrument].noteOn(id("F#"),volume);
            break;
    }     
   }
   @Override
   public void keyTyped(KeyEvent e){}
   @Override
   public void keyReleased(KeyEvent c){}
}
