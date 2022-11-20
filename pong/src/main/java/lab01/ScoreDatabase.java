package lab01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ScoreDatabase {
    public ScoreDatabase() {
        
        try(Connection con = getConnection()) {
            initTable(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<Score> load() {
        List<Score> result = new LinkedList<>();
        try(Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT score1, name1, score2, name2, MatchRes FROM score");
            while(rs.next()) {
                int value1 = rs.getInt(1);
                String name1 = rs.getString(2);
                int value2 = rs.getInt(3);
                String name2 = rs.getString(4);
                String MatchRes = rs.getString(5);
                result.add(new Score(value1, value2, name1, name2, MatchRes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result ;
    }

    public void save(Score score) {
        try( Connection con = getConnection()) {
            //getConnection().createStatement().execute("DELETE FROM score");
            PreparedStatement pstm = con.prepareStatement("INSERT INTO score (Score1, Name1, Score2, Name2, MatchRes) VALUES (?, ?, ?, ?, ?)");
                pstm.setInt(1, score.getScore1());
                pstm.setString(2, score.getName1());
                pstm.setInt(3, score.getScore2());
                pstm.setString(4, score.getName2());
                pstm.setString(5, score.getMatchRes());
                pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    private void initTable(Connection con) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.execute("CREATE TABLE Score ("
                    + "   Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                    + "   Score1 INT NOT NULL,"
                    + "   Name1 VARCHAR(255),"
                    + "   Score2 INT NOT NULL,"
                    + "   Name2 VARCHAR(255),"
                    + "   MatchRes VARCHAR(255),"
                    + "   PRIMARY KEY (Id))");
        } catch (SQLException e) {
            if(e.getSQLState().equals("X0Y32")) {
                return;
            }
            e.printStackTrace();
        }
        
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:scoreDB;create=true");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
