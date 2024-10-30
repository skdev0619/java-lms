package nextstep.courses.entity;

import java.time.LocalDateTime;

public class SessionEntity {

    private Long id;
    private Long course_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String pricing_type;
    private String session_status;
    private int available_seat;
    private Long creator_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public SessionEntity(Long course_id, LocalDateTime start_date, LocalDateTime end_date, String pricing_type, String session_status, int available_seat, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(null, course_id, start_date, end_date, pricing_type, session_status, available_seat, creator_id, created_at, updated_at);
    }

    public SessionEntity(Long id, Long course_id, LocalDateTime start_date, LocalDateTime end_date, String pricing_type, String session_status, int available_seat, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.course_id = course_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pricing_type = pricing_type;
        this.session_status = session_status;
        this.available_seat = available_seat;
        this.creator_id = creator_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public String getPricing_type() {
        return pricing_type;
    }

    public void setPricing_type(String pricing_type) {
        this.pricing_type = pricing_type;
    }

    public String getSession_status() {
        return session_status;
    }

    public void setSession_status(String session_status) {
        this.session_status = session_status;
    }

    public int getAvailable_seat() {
        return available_seat;
    }

    public void setAvailable_seat(int available_seat) {
        this.available_seat = available_seat;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
