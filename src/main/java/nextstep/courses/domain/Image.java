package nextstep.courses.domain;

import java.util.List;

public class Image {
    private static final long LIMIT_FILE_SIZE = 1_048_576;

    private final byte[] data;
    private final ImageType fileType;
    private final Size size;

    public Image(byte[] data, Size size, String extension){
        this(data, size, ImageType.from(extension));
    }

    public Image(byte[] data, Size size, ImageType fileType) {
        if(data.length > LIMIT_FILE_SIZE){
            throw new IllegalArgumentException("이미지 크기가 1MB 이하여야 합니다");
        }
        this.data = data;
        this.size = size;
        this.fileType = fileType;
    }
}
