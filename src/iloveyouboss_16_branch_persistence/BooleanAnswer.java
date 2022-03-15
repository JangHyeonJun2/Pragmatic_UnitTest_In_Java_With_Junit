package iloveyouboss_16_branch_persistence;

public class BooleanAnswer {
    private int questionId;
    private boolean value;

    BooleanAnswer(int questionId, boolean value) {
        this.questionId = questionId;
        this.value = value;
    }

    boolean getValue() {
        return value;
    }

    int getQuestionId() {
        return questionId;
    }
}
