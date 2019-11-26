   package posProjectForTuring.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Dao {
    private final String connectionString = "jdbc:mysql://localhost:3306/pos_project_for_turing";
    private final String connectionPassword = "#cRIaeC&Q&i6kt!$O6Cp";
    public Connection conn = null;
    
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Dao dao ;
    public static Dao getDao(){
        if(dao == null){
            dao = new Dao();
        }
        return dao;
    }
    
    private Dao(){
        try {
            this.conn = (Connection) DriverManager.getConnection(this.connectionString, "root", this.connectionPassword);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
