package nextstep.courses.domain;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String extension) {
        return ImageType.valueOf(extension.toUpperCase());
    }
}
