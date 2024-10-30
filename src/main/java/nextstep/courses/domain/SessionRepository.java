package nextstep.courses.domain;

import nextstep.courses.entity.SessionEntity;

public interface SessionRepository {
    int save(SessionEntity session);

    SessionEntity findById(Long id);
}
