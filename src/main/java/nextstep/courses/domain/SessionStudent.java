package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionStudent {
    private final Long id;

    private final Long sessionId;

    private final Long nsUserId;

    public SessionStudent(Long sessionId, Long nsUserId) {
        this(null, sessionId, nsUserId);
    }

    public SessionStudent(Long id, Long sessionId, Long nsUserId) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
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
}
