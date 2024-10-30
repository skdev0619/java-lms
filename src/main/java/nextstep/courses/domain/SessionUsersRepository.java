package nextstep.courses.domain;

import nextstep.courses.entity.SessionUsersEntity;

import java.util.List;

public interface SessionUsersRepository {
    int save(SessionUsersEntity users);
    SessionUsersEntity findById(Long id);
    List<SessionUsersEntity> findBySessionId(Long sessionId);
}
