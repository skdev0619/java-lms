package nextstep.courses.entity;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class SessionEntity {

    private Long id;
    private Long course_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String pricing_type;
    private int price;
    private String session_status;
    private int available_seat;
    private Long creator_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public SessionEntity(Long course_id, LocalDateTime start_date, LocalDateTime end_date, String pricing_type, int price, String session_status, int available_seat, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(null, course_id, start_date, end_date, pricing_type, price, session_status, available_seat, creator_id, created_at, updated_at);
    }

    public SessionEntity(Long id, Long course_id, LocalDateTime start_date, LocalDateTime end_date, String pricing_type, int price, String session_status, int available_seat, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.course_id = course_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pricing_type = pricing_type;
        this.price = price;
        this.session_status = session_status;
        this.available_seat = available_seat;
        this.creator_id = creator_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Session of(Long sessionId, SessionImage image, SessionStudent student) {
        SessionPeriod period = new SessionPeriod(this.start_date, this.end_date);
        SessionStatus status = SessionStatus.valueOf(session_status);
        Pricing pricing = new Pricing(PricingType.valueOf(pricing_type), price);

        return new Session(sessionId, period, status, image, pricing, student, creator_id, created_at);
    }

    public Long getId() {
        return id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public String getPricing_type() {
        return pricing_type;
    }

    public int getPrice() {
        return price;
    }

    public String getSession_status() {
        return session_status;
    }

    public int getAvailable_seat() {
        return available_seat;
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
