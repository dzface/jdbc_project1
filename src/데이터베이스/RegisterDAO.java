package 데이터베이스;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class RegisterDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    public boolean checkId(String id) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT USER_ID FROM MEMBER WHERE USER_ID = '" + id + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println("이미 사용중인 아이디입니다. ");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return true;
    }
    public void memberInsert(String id, String pw, String name ,String nickName) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "INSERT INTO MEMBER VALUES ("
                    + "'" + id + "'" + ", " + "'" + pw + "'" + ", " + "'" +
                    name + "'" + ", " + "'" + nickName + "'" + ")";

            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);

            System.out.println("회원가입이 완료되었습니다.");

        }
        catch(Exception e){
            e.printStackTrace();
        }

        Common.close(stmt);
        Common.close(conn);
    }
}


