package nextstep.courses.domain;

public class Session {

    private final Long id;

    private final DateRange dateRange;

    private final SessionStatus status;

    private final Image image;

    private final PricingType pricingType;

    private final int price;

    private int availableSeats;

    public Session(DateRange dateRange, SessionStatus status, Image image, PricingType pricingType, int price, int availableSeats){
        this(0L, dateRange, status, image, pricingType, price, availableSeats);
    }

    public Session(Long id, DateRange dateRange, SessionStatus status, Image image, PricingType pricingType, int price, int availableSeats) {
        this.id = id;
        this.dateRange = dateRange;
        this.status = status;
        this.image = image;
        this.pricingType = pricingType;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public void decreaseSeats(int payAmount) {
        checkEnrollmentPermission(payAmount);
        decreaseAvailableSeats();
    }

    private void checkEnrollmentPermission(int paymentAmount) {
        validateStatus();
        validateEnrollmentLimit();
        validatePrice(paymentAmount);
    }

    private void validateStatus() {
        if(status.canNotEnroll()){
            throw new IllegalStateException("강의 상태가 모집 중이 아니면 수강 신청 할 수 없습니다.");
        }
    }

    private void validateEnrollmentLimit() {
        if(pricingType.isLimitEnrollment() && availableSeats <= 0){
            throw new IllegalStateException("수강 가능 인원이 0명 이하면 수강 신청 할 수 없습니다");
        }
    }

    private void validatePrice(int paymentAmount) {
        if(pricingType.isFree()){
            return;
        }

        if(price < paymentAmount){
            throw new IllegalStateException("강의 가격보다 지불한 돈이 더 많습니다.");
        }

        if(price > paymentAmount){
            throw new IllegalStateException("수강 신청하기에 지불한 돈이 부족합니다.");
        }
    }

    private void decreaseAvailableSeats() {
        if(pricingType.isPaid()){
            this.availableSeats -= 1;
        }
    }

    public Long getId() {
        return id;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Image getImage() {
        return image;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
