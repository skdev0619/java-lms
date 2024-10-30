package nextstep.courses.domain;

public class SessionImage {

    private static final long LIMIT_FILE_SIZE = 1_048_576;
    private static final String EXTENSION_SEPERATOR = ".";

    private final String filePath;
    private final long fileSize;
    private final Size size;

    private ImageType fileType;

    public SessionImage(String filePath, long fileSize, Size size) {
        validateFileSize(fileSize);
        validateFilePath(filePath);
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.size = size;
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

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Size getSize() {
        return size;
    }

    public ImageType getFileType() {
        return fileType;
    }
}
