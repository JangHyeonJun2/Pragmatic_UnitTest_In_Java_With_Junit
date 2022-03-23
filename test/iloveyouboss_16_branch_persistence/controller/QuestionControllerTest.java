package iloveyouboss_16_branch_persistence.controller;

import iloveyouboss_16_branch_persistence.domain.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class QuestionControllerTest {
    private QuestionController controller;
    @BeforeEach
    public void create() {
        controller = new QuestionController();
    }

    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);

        Assertions.assertThat(question.getCreateTimestamp()).isEqualTo(now);
    }
}
