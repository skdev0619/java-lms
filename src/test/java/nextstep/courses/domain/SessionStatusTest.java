package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    
    @DisplayName("강의 상태가 모집 중일때만 수강신청 가능하다")
    @Test
    void canEnroll(){
        SessionStatus status = SessionStatus.RECRUITING;

        assertThat(status.canEnroll()).isTrue();
    }

    @DisplayName("강의 상태가 준비중, 종료일 때는 수강신청 불가능하다")
    @Test
    void canNotEnroll(){
        SessionStatus preparing = SessionStatus.PREPARING;
        SessionStatus finished = SessionStatus.FINISHED;

        assertThat(preparing.canNotEnroll()).isTrue();
        assertThat(finished.canNotEnroll()).isTrue();
    }
}
