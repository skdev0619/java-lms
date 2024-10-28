package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageTypeTest {

    @DisplayName("확장자로부터 이미지 타입을 반환받는다")
    @Test
    void from() {
        ImageType jpg = ImageType.from("jpg");

        assertThat(ImageType.from("jpg")).isEqualTo(ImageType.JPG);
    }

    @DisplayName("유효하지 않은 확장자를 체크한다")
    @Test
    void invalidExtension(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ImageType.from("txt"))
                .withMessage("허용하지 않는 이미지 확장자입니다.");
    }

}
