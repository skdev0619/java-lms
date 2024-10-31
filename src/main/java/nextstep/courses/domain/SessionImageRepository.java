package nextstep.courses.domain;

public interface SessionImageRepository {
    int save(SessionImage image);

    SessionImage findById(Long id);
}
