package nextstep.courses.domain;

import java.util.List;

public interface SessionUsersRepository {
    int save(SessionStudent users);
    SessionStudent findById(Long id);
    SessionStudents findBySessionId(Long sessionId);
    void deleteBySessionId(Long sessionId);
    int[] bulkSave(List<SessionStudent> students);
}
