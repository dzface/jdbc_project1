package 데이터베이스;

public class NutrientsVO implements Comparable<NutrientsVO>{

    private String nutrientsName;
    private String ingredientA;
    private String ingredientB;
    private String company;
    private String howToTake;

    public String getNutrientsName() {
        return nutrientsName;
    }

    public String getIngredientA() {
        return ingredientA;
    }

    public String getIngredientB() {
        return ingredientB;
    }

    public String getCompany() {
        return company;
    }

    public String getHowToTake() {
        return howToTake;
    }

    public void setNutrientsName(String nutrientsName) {
        this.nutrientsName = nutrientsName;
    }

    public void setIngredientA(String ingredientA) {
        this.ingredientA = ingredientA;
    }

    public void setIngredientB(String ingredientB) {
        this.ingredientB = ingredientB;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setHowToTake(String howToTake) {
        this.howToTake = howToTake;
    }

    @Override
    public int compareTo(NutrientsVO o) {
        if (this.nutrientsName.equals(o.nutrientsName)) return 0;
        else return 1;
    }
}