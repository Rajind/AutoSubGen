/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vlcj;

import app.BackgroundExecutor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JProgressBar;

/**
 *
 * @author Rajind
 */
public class SeekBar extends JProgressBar {
    
    private Player player;
    private int updatedValue = 0; //sharing between different scopes

    /*
     * progress & totalVal must be give in same units
     */
    
    public void updateSeekBar(long progress, float totalVal){
        BackgroundExecutor.get().execute(new UpdatingTask(progress, totalVal)); 
        //Another thread will calculate the relative position
        setValue(updatedValue);
        //repaint();
    }

    /**
     * Task used for updating the seek value in another thread.
     */
    private class UpdatingTask implements Runnable {
        long progress; float totalVal;
        public UpdatingTask(long progress, float totalVal) {
            this.progress = progress;
            this.totalVal = totalVal;
        }

        @Override
        public void run() {
            int seekLength = getMaximum();
            updatedValue = (int) ((progress/(totalVal))*seekLength);          
        }
    }
    
    ///////////////////////////////////////////////////////////

    public void setPlayer(Player player_instance){
        player = player_instance;
    }
    /**
     * New Constructor, sets a mouseListener
     * (extends JProgressBar)
     */
    public SeekBar() {
        super();
        setMaximum(10000); 
        addMouseListener(new MouseListener() {
            @Override
                public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                float val = ((float)e.getX()/getWidth());
                float val_for_seek_bar = (float) (val * getMaximum());
                returnValueToPlayer(val);
                setValue((int)val_for_seek_bar);
                log("SeekBar pressed: " + val + " x: " + e.getX());
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    /**
     * Informs the player about the relative value selected in the seekbar
     * @throws BasicPlayerException
     */
    private void returnValueToPlayer(float val){
        player.setPosition(val);
        player.play_pause(false);
    }

    private void log(String str)
    {
        System.out.println("SeekBar] " +str);
    }
}
