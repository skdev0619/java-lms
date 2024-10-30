package nextstep.courses.entity;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.Size;

import java.time.LocalDateTime;

public class SessionImageEntity {
    private Long id;
    private Long session_id;
    private String file_path;
    private int file_size;
    private int width;
    private int height;
    private Long creator_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public SessionImageEntity(Long session_id, String file_path, int file_size, int width, int height, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(null, session_id, file_path, file_size, width, height, creator_id, created_at, updated_at);
    }

    public SessionImageEntity(Long id, Long session_id, String file_path, int file_size, int width, int height, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.session_id = session_id;
        this.file_path = file_path;
        this.file_size = file_size;
        this.width = width;
        this.height = height;
        this.creator_id = creator_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public SessionImage fromEntity() {
        return new SessionImage(
                file_path,
                file_size,
                new Size(width, height)
        );
    }

    public Long getId() {
        return id;
    }

    public Long getSession_id() {
        return session_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public int getFile_size() {
        return file_size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
}
