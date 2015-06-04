package various;

/**
 *
 */

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Tutorial1B {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String path = Tutorial1B.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8");
        decodedPath = decodedPath.replace("SEProject.jar", "")+"libvlc";
        System.out.println(decodedPath);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), decodedPath );        
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }
}