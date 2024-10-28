package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionPeriodTest {

    @DisplayName("강의 시작일은 강의 종료일 이후의 날짜가 되어서는 안됩니다.")
    @Test
    void validDate() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(
                        LocalDateTime.of(2024, 10, 6, 0, 0),
                        LocalDateTime.of(2024, 10, 1, 0, 0)
                ))
                .withMessage("강의 시작일은 강의 종료일 이후의 날짜가 되어서는 안됩니다.");
    }

    @DisplayName("시작일 ~ 종료기간에만 강의 수강 가능하다")
    @Test
    void isDateInRange() {
        SessionPeriod dateRange = new SessionPeriod(
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 10, 5, 23, 59)
        );

        assertThat(dateRange.isDateInRange(LocalDateTime.of(2024, 9, 30, 23, 59))).isFalse();
        assertThat(dateRange.isDateInRange(LocalDateTime.of(2024, 10, 1, 0, 0))).isTrue();
        assertThat(dateRange.isDateInRange(LocalDateTime.of(2024, 10, 6, 0, 0))).isFalse();
    }
}
