package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageTypeTest {

    @DisplayName("확장자로부터 이미지 타입을 반환받는다")
    @Test
    void from(){
        ImageType jpg = ImageType.from("jpg");

        assertThat(ImageType.from("jpg")).isEqualTo(ImageType.JPG);
    }

}