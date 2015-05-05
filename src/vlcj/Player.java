package vlcj;

import app.FileAdderView;
import app.FileAdderView1;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * The is the Player which vlcj wrapper for vlc media library 
 */

public class Player extends javax.swing.JPanel {
    
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private String mrl;
    private long mark_start;
    private long mark_end;
    private FileAdderView view;
    /**
     * Creates new form Player
     */
    public Player() {
        initComponents();
        loadScreen();
    }
    
     public Player(String path, FileAdderView v){
        view = v;
        mrl = path;
        initComponents();
        loadScreen();
    }
      
    public void SetFile(String file){       //sets the file to be played
        mrl = file;
    } 
    
    public void play(){             //plays the file given by MRL
        this.mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
        new Thread(new Player.SeekerThread(this)).start();
    }
    public void play(String name){             //plays the file given by MRL
        mrl = name;
        this.mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
        new Thread(new Player.SeekerThread(this)).start();
    }
        
    
    public void pause(){        //pause the file
        this.mediaPlayerComponent.getMediaPlayer().pause();
    }
      
    public int getSeekValue(){          //get current playing position
        return (int)this.mediaPlayerComponent.getMediaPlayer().getPosition();
    }
     
    public float getCurrentTime(){
        return this.mediaPlayerComponent.getMediaPlayer().getTime();
    }
    
    public float getLength(){       //gives length of file
        return (float)this.mediaPlayerComponent.getMediaPlayer().getLength();
    }
    
    public void setPosition(float position){        //used to seek the media file
        this.mediaPlayerComponent.getMediaPlayer().setPosition(position);
    }
    
    public void play_pause(boolean state){      //used to play and pause
        this.mediaPlayerComponent.getMediaPlayer().setPause(state);
    } 
            
    public void pre_init(){    
        //loading native vlc libraries
        
        
        NativeLibrary.addSearchPath(
        RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC" 
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        
        //when deploying use this
        /*try {
            String path = Player.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath.replace("SEProject.jar", "")+"libvlc";
            System.out.println(decodedPath);
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), decodedPath );        
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void loadScreen(){       //loading player to the panel
        this.pre_init();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        player_panel.removeAll();
        player_panel.add(mediaPlayerComponent);
        player_panel.setVisible(true);
        player_panel.updateUI();
        this.seek_bar.setPlayer(this);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        play_pause_button = new javax.swing.JButton();
        player_panel = new javax.swing.JPanel();
        mark_start_button = new javax.swing.JButton();
        mark_end_button = new javax.swing.JButton();
        seek_bar = new vlcj.SeekBar();
        load_button = new javax.swing.JButton();

        play_pause_button.setText("Pause");
        play_pause_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_pause_buttonActionPerformed(evt);
            }
        });

        player_panel.setLayout(new java.awt.BorderLayout());

        mark_start_button.setText("[");
        mark_start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mark_start_buttonActionPerformed(evt);
            }
        });

        mark_end_button.setText("]");
        mark_end_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mark_end_buttonActionPerformed(evt);
            }
        });

        load_button.setText("Load");
        load_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(play_pause_button)
                .addGap(29, 29, 29)
                .addComponent(mark_start_button, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(mark_end_button, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(load_button)
                .addContainerGap(73, Short.MAX_VALUE))
            .addComponent(player_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(seek_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(player_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seek_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(load_button)
                    .addComponent(play_pause_button)
                    .addComponent(mark_start_button)
                    .addComponent(mark_end_button))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void seek_bar_update(){
        this.seek_bar.updateSeekBar((long) this.getCurrentTime(), this.getLength());
    }

    public static class SeekerThread implements Runnable{
        Player player;
        public SeekerThread(Player p){
            player = p;
        }
        //int i=0;
        public void run(){
            while (player.seek_bar.getValue() < player.seek_bar.getMaximum()){ 
                //i++;
                player.seek_bar_update();
                //System.out.println("done "+i);
                try{Thread.sleep(50);} //Sleep 50 milliseconds
                catch (InterruptedException err){}
            }
        }
    }
    
    private void play_pause_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_pause_buttonActionPerformed
        if(this.mediaPlayerComponent.getMediaPlayer().isPlaying()){
            this.mediaPlayerComponent.getMediaPlayer().setPause(true);
            this.play_pause_button.setText("Play");
        }else{
            this.mediaPlayerComponent.getMediaPlayer().setPause(false);
            this.play_pause_button.setText("Pause");
        }
    }//GEN-LAST:event_play_pause_buttonActionPerformed

    private void mark_start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mark_start_buttonActionPerformed
        //current playback time in milliseconds
        mark_start = this.mediaPlayerComponent.getMediaPlayer().getTime();
        view.getModel().setStart_time(mark_start);
        try {
            view.updateClipMarkIn(mark_start);
        } catch (ParseException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mark_start_buttonActionPerformed

    private void mark_end_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mark_end_buttonActionPerformed
        //current playback time in milliseconds
        mark_end = this.mediaPlayerComponent.getMediaPlayer().getTime();
        view.getModel().setEnd_time(mark_end);
        try {
            view.updateClipMarkOut(mark_end);
        } catch (ParseException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mark_end_buttonActionPerformed

    private void load_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_buttonActionPerformed
        this.loadScreen();
    }//GEN-LAST:event_load_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton load_button;
    private javax.swing.JButton mark_end_button;
    private javax.swing.JButton mark_start_button;
    private javax.swing.JButton play_pause_button;
    private javax.swing.JPanel player_panel;
    private vlcj.SeekBar seek_bar;
    // End of variables declaration//GEN-END:variables
}
