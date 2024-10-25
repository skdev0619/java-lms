package nextstep.courses.domain;

import java.time.LocalDateTime;

public class DateRange {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public DateRange(LocalDateTime startDate, LocalDateTime endDate) {
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
