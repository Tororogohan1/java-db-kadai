package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Scores_Chapter10 {
    public static void main(String[] args) {

        
        Connection conn = null;
        Statement stmt = null;

        try {
            // データベースに接続
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Rino0221"
            );
            System.out.println("データベース接続成功");
            
         // 2. ステートメントを作成
            stmt = conn.createStatement();
            String updateQuery = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
            int rowsAffected = stmt.executeUpdate(updateQuery);
            System.out.println(rowsAffected + "件のレコードが更新されました");
            
            String selectQuery = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
            ResultSet rs = stmt.executeQuery(selectQuery);
            System.out.println("数学・英語の点数が高い順に並べ替えました");
            
            int count = 1;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int scoreMath = rs.getInt("score_math");
                int scoreEnglish = rs.getInt("score_english");
                System.out.println(count + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + scoreMath + "／英語=" + scoreEnglish);
                count++;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

            
            