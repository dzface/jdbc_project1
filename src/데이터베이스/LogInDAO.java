package 데이터베이스;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class LogInDAO {
    static String nickName;
    public boolean logIn(String id,String pw) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT USER_ID, USER_PW, USER_NICK FROM MEMBER WHERE USER_ID = '" + id +"'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                if (!pw.equals(rs.getString("USER_PW"))) {
                    System.out.println("잘못된 비밀번호 입니다. ");
                    return false;
                }
                nickName = rs.getString("USER_NICK");
            }
            else {
                System.out.println("없는 아이디입니다. ");
                return false;
            }
            System.out.println("로그인 성공.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }
}
