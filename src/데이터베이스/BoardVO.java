package 데이터베이스;

public class BoardVO {
    private String commentNo;
    private String nutrientsName;
    private String userId;
    private String content;
    private String userNick;

    public String getCommentNo() {
        return commentNo;
    }

    public String getUserNick() {
        return userNick;
    }

    public String getNutrientsName() {
        return nutrientsName;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setNutrientsName(String nutrientsName) {
        this.nutrientsName = nutrientsName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public void setCommentNo(String commentNo) {
        this.commentNo = commentNo;
    }
}
