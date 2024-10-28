package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String extension) {
        validate(extension);
        return ImageType.valueOf(extension.toUpperCase());
    }

    private static void validate(String extension) {
        Arrays.stream(values())
                .filter(type -> extension.equalsIgnoreCase(type.name()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("허용하지 않는 이미지 확장자입니다."));
    }
}
