package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private final Long id;

    private final SessionPeriod dateRange;

    private final SessionStatus status;

    private final SessionImage image;

    private final Pricing pricing;

    private final SessionStudents students;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(SessionPeriod dateRange, SessionStatus status, SessionImage image, Pricing pricing, SessionStudents student, Long creatorId, LocalDateTime createdAt) {
        this(0L, dateRange, status, image, pricing, student, creatorId, createdAt);
    }

    public Session(SessionPeriod dateRange, SessionStatus status, SessionImage image, Pricing pricing, int availableSeats, Long creatorId, LocalDateTime createdAt) {
        this(0L, dateRange, status, image, pricing, new SessionStudents(availableSeats), creatorId, createdAt);
    }

    public Session(Long id, SessionPeriod dateRange, SessionStatus status, SessionImage image, Pricing pricing, SessionStudents student, Long creatorId, LocalDateTime createdAt) {
        this.id = id;
        this.dateRange = dateRange;
        this.status = status;
        this.image = image;
        this.pricing = pricing;
        this.students = student;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public void enrollStudent(NsUser loginUser, int payAmount) {
        checkEnrollmentPermission(payAmount);
        students.addStudent(pricing, loginUser);
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

    public SessionStudents getStudents() {
        return students;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
