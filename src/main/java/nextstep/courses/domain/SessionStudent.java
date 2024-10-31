package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionStudent {
    private final Long id;

    private final Long sessionId;

    private final Long nsUserId;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public SessionStudent(Long sessionId, Long nsUserId, Long creatorId){
        this(sessionId, nsUserId, creatorId, null, null);
    }

    public SessionStudent(Long sessionId, Long nsUserId, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, sessionId, nsUserId, creatorId, createdAt, updatedAt);
    }

    public SessionStudent(Long id, Long sessionId, Long nsUserId, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
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
