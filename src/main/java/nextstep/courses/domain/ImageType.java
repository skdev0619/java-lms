package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String extension) {
        return Arrays.stream(values())
                .filter(type -> extension.equalsIgnoreCase(type.name()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("허용하지 않는 이미지 확장자입니다."));
    }
}
