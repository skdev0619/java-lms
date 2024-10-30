package nextstep.courses.domain;

import nextstep.courses.entity.SessionImageEntity;

public interface SessionImageRepository {
    int save(SessionImageEntity image);

    SessionImageEntity findById(Long id);
}
