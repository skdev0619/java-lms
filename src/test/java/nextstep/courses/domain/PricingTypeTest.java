package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PricingTypeTest {

    @DisplayName("유료 강의는 는 수강 인원 제한이 있다")
    @Test
    void isLimitEnrollment(){
        PricingType pricingType = PricingType.PAID;

        assertThat(pricingType.isLimitEnrollment()).isTrue();
    }
}