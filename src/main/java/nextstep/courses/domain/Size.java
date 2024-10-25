package nextstep.courses.domain;

import javax.swing.*;

public class Size {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private final int width;
    private final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
        validate();
    }

    private void validate() {
        validateSize();
        validateRatio();
    }

    private void validateSize() {
        if(width < DEFAULT_WIDTH && height < DEFAULT_HEIGHT){
            throw new IllegalArgumentException(
                    String.format("가로 %d픽셀, 세로 %d픽셀 이상이어야 합니다",
                            DEFAULT_WIDTH, DEFAULT_HEIGHT)
            );
        }
    }

    private void validateRatio() {
        double ratio = (double) width / height;
        double expectedRatio = 3.0 / 2.0;

        if(ratio != expectedRatio){
            throw new IllegalArgumentException("가로와 세로의 비율은 3:2여야 합니다");
        }
    }
}
