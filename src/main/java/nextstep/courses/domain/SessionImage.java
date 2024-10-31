package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionImage {

    private static final long LIMIT_FILE_SIZE = 1_048_576;
    private static final String EXTENSION_SEPERATOR = ".";

    private final Long id;
    private final Long sessionId;
    private final String filePath;
    private final long fileSize;
    private final Size size;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private ImageType fileType;

    public SessionImage(Long session_id, String filePath, long fileSize, Size size, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, session_id, filePath, fileSize, size, creatorId, createdAt, updatedAt);
    }

    public SessionImage(Long id, Long sessionId, String filePath, long fileSize, Size size, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateFileSize(fileSize);
        validateFilePath(filePath);
        this.id = id;
        this.sessionId = sessionId;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.size = size;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fileType = getImageExtension(filePath);
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > LIMIT_FILE_SIZE) {
            throw new IllegalArgumentException("이미지 크기가 1MB 이하여야 합니다");
        }
    }

    private void validateFilePath(String filePath) {
        int extensionIndex = filePath.lastIndexOf(".");
        if (extensionIndex <= 0) {
            throw new IllegalArgumentException("유효하지 않은 파일 경로입니다");
        }
    }

    private ImageType getImageExtension(String filePath) {
        int extensionIndex = filePath.lastIndexOf(EXTENSION_SEPERATOR);
        String extension = filePath.substring(extensionIndex + 1);
        return ImageType.from(extension);
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Size getSize() {
        return size;
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

    public ImageType getFileType() {
        return fileType;
    }
}
