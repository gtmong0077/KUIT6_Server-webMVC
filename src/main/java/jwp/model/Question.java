package jwp.model;

import java.security.Timestamp;

public class Question {

    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private java.sql.Timestamp createdDate;
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, java.sql.Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }


    public long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }
    public int getCountOfAnswer() {
        return countOfAnswer;
    }

}
