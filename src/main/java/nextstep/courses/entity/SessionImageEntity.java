package nextstep.courses.entity;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionImage;

import java.time.LocalDateTime;

public class SessionImageEntity {
    private Long id;
    private Long session_id;
    private String file_path;
    private Long creator_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public SessionImageEntity(Long session_id, String file_path, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(null, session_id, file_path, creator_id, created_at, updated_at);
    }

    public SessionImageEntity(Long id, Long session_id, String file_path, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.session_id = session_id;
        this.file_path = file_path;
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

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
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
