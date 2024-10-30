package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private final Long id;

    private final SessionPeriod dateRange;

    private final SessionStatus status;

    private final SessionImage image;

    private final Pricing pricing;

    private final SessionStudent student;

    private final Long creator_id;

    private final LocalDateTime created_at;

    private LocalDateTime updated_at;

    public Session(SessionPeriod dateRange, SessionStatus status, SessionImage image, Pricing pricing, int availableSeats, Long creator_id, LocalDateTime created_at) {
        this(0L, dateRange, status, image, pricing, availableSeats, creator_id, created_at);
    }

    public Session(Long id, SessionPeriod dateRange, SessionStatus status, SessionImage image, Pricing pricing, int availableSeats, Long creator_id, LocalDateTime created_at) {
        this.id = id;
        this.dateRange = dateRange;
        this.status = status;
        this.image = image;
        this.pricing = pricing;
        this.student = new SessionStudent(availableSeats);
        this.creator_id = creator_id;
        this.created_at = created_at;
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

    public SessionImage getImage() {
        return image;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public SessionStudent getStudent() {
        return student;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
}
