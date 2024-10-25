package nextstep.courses.domain;

public class Session {

    private final Long id;

    private final DateRange dateRange;

    private final Image image;

    public Session(Long id, DateRange dateRange, Image image) {
        this.id = id;
        this.dateRange = dateRange;
        this.image = image;
    }
}
