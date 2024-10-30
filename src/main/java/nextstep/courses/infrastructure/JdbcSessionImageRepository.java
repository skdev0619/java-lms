package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcSessionImageRepository implements SessionImageRepository {

    private final JdbcOperations jdbcTemplate;

    @Autowired
    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionImageEntity image) {
        String sql = "insert into session_image (session_id, file_path, file_size, width, height, creator_id, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                image.getSession_id(),
                image.getFile_path(),
                image.getFile_size(),
                image.getWidth(),
                image.getHeight(),
                image.getCreator_id(),
                image.getCreated_at(),
                image.getUpdated_at());
    }

    @Override
    public SessionImageEntity findById(Long id) {
        String sql = "select id, session_id, file_path, file_size, width, height, creator_id, created_at, updated_at from session_image where id = ?";

        RowMapper<SessionImageEntity> rowMapper = (rs, rowNum) -> new SessionImageEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getInt(6),
                rs.getLong(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
