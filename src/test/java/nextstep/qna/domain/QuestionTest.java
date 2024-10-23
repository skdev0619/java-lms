package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문자와 로그인 사용자가 다르면 삭제가 불가능하다")
    @Test
    void checkDeletePermission() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.checkDeletePermission(NsUserTest.SANJIGI))
                        .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("질문을 삭제할 권한이 없습니다.");

    }

    @DisplayName("질문의 삭제 상태 플래그를 true로 변경한다")
    @Test
    void markDeleted(){
        Q1.markDeleted();

        assertThat(Q1.isDeleted()).isEqualTo(true);
    }
    
    @DisplayName("질문을 삭제 후, 삭제 이력을 반환한다")
    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).extracting("contentType", "deletedBy")
                .containsExactly(new Tuple(ContentType.QUESTION, NsUserTest.JAVAJIGI));

    }

    @DisplayName("질문과 답변의 작성자가 동일하면 삭제 가능하다")
    @Test
    void deleteBySameUser() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        List<DeleteHistory> history = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(history).extracting("contentType", "deletedBy")
                .containsExactlyInAnyOrder(
                        new Tuple(ContentType.QUESTION, NsUserTest.JAVAJIGI),
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI)
                );
    }

    @DisplayName("질문과 답변의 작성자가 다르면 삭제 불가능하다")
    @Test
    void deleteByDiffrentUser() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A2);

       assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
               .isInstanceOf(RuntimeException.class)
               .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
