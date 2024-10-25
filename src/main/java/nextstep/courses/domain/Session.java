package nextstep.courses.domain;

public class Session {

    private final Long id;

    private final DateRange dateRange;

    private final Image image;

    private final PricingType pricingType;

    private int enrollmentLimit;

    public Session(Long id, DateRange dateRange, Image image, PricingType pricingType) {
        this.id = id;
        this.dateRange = dateRange;
        this.image = image;
        this.pricingType = pricingType;
    }

}
