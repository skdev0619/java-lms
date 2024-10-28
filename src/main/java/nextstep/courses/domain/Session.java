package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {

    private final Long id;

    private final SessionPeriod dateRange;

    private final SessionStatus status;

    private final Image image;

    private final Pricing pricing;

    private final SessionStudent student;

    public Session(SessionPeriod dateRange, SessionStatus status, Image image, Pricing pricing, int availableSeats) {
        this(0L, dateRange, status, image, pricing, availableSeats);
    }

    public Session(Long id, SessionPeriod dateRange, SessionStatus status, Image image, Pricing pricing, int availableSeats) {
        this.id = id;
        this.dateRange = dateRange;
        this.status = status;
        this.image = image;
        this.pricing = pricing;
        this.student = new SessionStudent(availableSeats);
    }

    public void enrollStudent(NsUser loginUser, int payAmount) {
        checkEnrollmentPermission(payAmount);
        student.addStudent(pricing, loginUser);
    }

    private void checkEnrollmentPermission(int paymentAmount) {
        validateStatus();
        validatePrice(paymentAmount);
    }

    private void validateStatus() {
        if (status.canNotEnroll()) {
            throw new IllegalStateException("강의 상태가 모집 중이 아니면 수강 신청 할 수 없습니다.");
        }
    }

    private void validatePrice(int paymentAmount) {
        if (pricing.canNotEnroll(paymentAmount)) {
            throw new IllegalStateException("강의 가격과 지불한 돈이 일치하지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public SessionPeriod getDateRange() {
        return dateRange;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Image getImage() {
        return image;
    }
}
