package nextstep.courses.domain;

public class Image {
    private final ImageType fileType;

    public Image(String extension){
        this(ImageType.from(extension));
    }

    public Image(ImageType fileType) {
        this.fileType = fileType;
    }
}
