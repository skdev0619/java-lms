package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageTest {

    @DisplayName("이미지 파일 크기가 1MB 이하여야 한다")
    @Test
    void limitFileSize(){
        byte[] overSizeData = new byte[1_048_577];

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(overSizeData, "jpg"))
                .withMessage("이미지 크기가 1MB 이하여야 합니다");

    }

}