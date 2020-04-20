package posProjectForTuring;

import posProjectForTuring.ui.MainLogin;
import static posProjectForTuring.ui.MainLogin.mainLogin;

public class Main {
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                mainLogin = new MainLogin();
                mainLogin.setVisible(true);
            }
        });
    }
}
