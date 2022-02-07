package ilobeyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//Criteria : 기준, Criterion : 표준
class ProfileTest {
    private Profile profile;
    private Criteria criteria;

    private Question question;

    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;

    @BeforeEach
    public void createProfile() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }

    @BeforeEach
    public void createQuestionsAndAnswers() {
        question =
                new BooleanQuestion(1, "Got Bonuses?");

        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNoRelocation =
                new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);

        questionOnsiteDaycare =
                new BooleanQuestion(1, "Onsite daycare?");
        answerHasOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.TRUE);
        answerNoOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.FALSE);
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriterial() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);
    }

    @Test
    public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerDoesNotReimburseTuition,Weight.Important));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);

    }

    @Test
    public void scoreIsZeroWhenThereAreNotMatches() {
        profile.add(answerThereIsNoRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        profile.matches(criteria);

        assertEquals(0, profile.score());
    }

    @Test
    public void scoreIsCriterionValueForSingleMatch() {
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.VeryImportant));

        profile.matches(criteria);

        assertEquals(5000, profile.score());
    }

}