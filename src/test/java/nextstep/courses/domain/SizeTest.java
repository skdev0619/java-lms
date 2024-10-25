package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SizeTest {

    @DisplayName("이미지의 가로 300픽셀, 세로 200픽셀 이상이어야 한다")
    @Test
    void limitSize(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Size(299, 199))
                .withMessage("가로 300픽셀, 세로 200픽셀 이상이어야 합니다");
    }

    @DisplayName("이미지의 가로와 세로의 비율은 3:2여야 한다")
    @Test
    void sizeRatio(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Size(400, 200))
                .withMessage("가로와 세로의 비율은 3:2여야 합니다");
    }
}