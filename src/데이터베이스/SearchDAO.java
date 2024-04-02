package 데이터베이스;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;

public class SearchDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public TreeSet<NutrientsVO> search(int num, String data) {
        String query = null;
        TreeSet<NutrientsVO> set = new TreeSet<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            switch (num) {
                case 1:
                    query = "SELECT * FROM NUTRIENTS n JOIN INGREDIENT i ON i.INGREDIENT_NAME = n.INGREDIENT_A " +
                            "OR i.INGREDIENT_NAME = n.INGREDIENT_B WHERE INGREDIENT_NAME LIKE" + "'%" + data + "%'";
                    break;
                case 2:
                    query = "SELECT * FROM NUTRIENTS n JOIN INGREDIENT i ON i.INGREDIENT_NAME = n.INGREDIENT_A" +
                            " OR i.INGREDIENT_NAME = n.INGREDIENT_B WHERE EFFICACY_A LIKE '%"
                            + data + "%' OR EFFICACY_B LIKE '%" + data + "%' OR EFFICACY_C LIKE '%" + data + "%'";
                    break;
            }

            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("검색결과가 존재하지 않습니다.");
                return set;
            }

            while (rs.next()) {
                String nutrientsName = rs.getString("NUTRIENTS_NAME");
                String ingredientA = rs.getString("INGREDIENT_A");
                String ingredientB = rs.getString("INGREDIENT_B");
                String company = rs.getString("COMPANY");
                String howToTake = rs.getString("HOW_TO_TAKE");

                NutrientsVO vo = new NutrientsVO();
                vo.setNutrientsName(nutrientsName);
                vo.setIngredientA(ingredientA);
                vo.setIngredientB(ingredientB);
                vo.setCompany(company);
                vo.setHowToTake(howToTake);

                set.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return set;
    }

    void printNutrients(TreeSet<NutrientsVO> set) {
        for (NutrientsVO e : set) {
            System.out.println("=".repeat(30));
            System.out.println("영양제 : " + e.getNutrientsName());
            if (e.getIngredientB() != null)
                System.out.println("성분 : " + e.getIngredientA() + ", " + e.getIngredientB());
            else System.out.println("성분 : " + e.getIngredientA());
            System.out.println("제조사 : " + e.getCompany());
        }
    }
}