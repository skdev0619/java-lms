package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @DisplayName("질문 작성자와 답변 작성자가 다르면 삭제할 수 없다")
    @Test
    void checkDeletePermission() throws CannotDeleteException {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> answers.checkDeletePermission(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}