package various;

    import javax.swing.JFrame;
    import javax.swing.SwingUtilities;

    public class Tutorial2A {

        public static void main(final String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Tutorial2A(args);
                }
            });
        }

        private Tutorial2A(String[] args) {
            JFrame frame = new JFrame("vlcj Tutorial");
            frame.setLocation(100, 100);
            frame.setSize(1050, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }