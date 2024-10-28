package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("강의 시작일은 강의 종료일 이후의 날짜가 되어서는 안됩니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isDateInRange(LocalDateTime date) {
        return isStartDateGreaterThanOrEqual(date) && isEndDateLessThanOrEqual(date);
    }

    private boolean isStartDateGreaterThanOrEqual(LocalDateTime date) {
        return startDate.isBefore(date) || startDate.isEqual(date);
    }

    private boolean isEndDateLessThanOrEqual(LocalDateTime date) {
        return endDate.isAfter(date) || endDate.isEqual(date);
    }
}
