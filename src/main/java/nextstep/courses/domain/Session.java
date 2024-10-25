package nextstep.courses.domain;

public class Session {

    private final Long id;

    private final DateRange dateRange;

    private final SessionStatus status;

    private final Image image;

    private final PricingType pricingType;

    private final int price;

    private int enrollmentLimit;

    public Session(DateRange dateRange, SessionStatus status, Image image, PricingType pricingType, int price, int enrollmentLimit){
        this(0L, dateRange, status, image, pricingType, price, enrollmentLimit);
    }

    public Session(Long id, DateRange dateRange, SessionStatus status, Image image, PricingType pricingType, int price, int enrollmentLimit) {
        this.id = id;
        this.dateRange = dateRange;
        this.status = status;
        this.image = image;
        this.pricingType = pricingType;
        this.price = price;
        this.enrollmentLimit = enrollmentLimit;
    }

    public void checkEnrollmentPermission() {
        validateStatus();
    }

    private void validateStatus() {
        if(status.canNotEnroll()){
            throw new IllegalStateException("강의 상태가 모집 중이 아니면 수강 신청 할 수 없습니다.");
        }
    }
}
