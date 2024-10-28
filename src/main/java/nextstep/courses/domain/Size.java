package nextstep.courses.domain;

public class Size {

    private static final double WIDTH_RADIO = 3;
    private static final double HEIGHT_RADIO = 2;

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private final int width;
    private final int height;

    public Size(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        validateSize(width, height);
        validateRatio(width, height);
    }

    private void validateSize(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(
                    String.format("가로 %d픽셀, 세로 %d픽셀 이상이어야 합니다",
                            MIN_WIDTH, MIN_HEIGHT)
            );
        }
    }

    private void validateRatio(int width, int height) {
        double ratio = (double) width / height;
        double expectedRatio = WIDTH_RADIO / HEIGHT_RADIO;

        if (ratio != expectedRatio) {
            throw new IllegalArgumentException(
                    String.format("가로와 세로의 비율은 %d:%d여야 합니다", WIDTH_RADIO, HEIGHT_RADIO));
        }
    }
}
