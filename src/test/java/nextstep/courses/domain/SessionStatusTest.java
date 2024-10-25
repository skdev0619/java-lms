package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    
    @DisplayName("모집 중일떄만 수강신청 가능하다")
    @Test
    void canEnroll(){
        SessionStatus status = SessionStatus.RECRUITING;

        assertThat(status.canEnroll()).isTrue();
    }
}
