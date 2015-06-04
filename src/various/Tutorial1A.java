/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package various;

/**
 *
 * @author Rajind
 */
    import uk.co.caprica.vlcj.binding.LibVlc;
    import uk.co.caprica.vlcj.runtime.RuntimeUtil;

    import com.sun.jna.Native;

    public class Tutorial1A {

        public static void main(String[] args) {
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        }
    }
