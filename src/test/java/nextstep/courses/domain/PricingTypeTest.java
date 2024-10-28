package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PricingTypeTest {

    @DisplayName("무료강의인지 확인한다")
    @Test
    void isFree() {
        PricingType pricingType = PricingType.FREE;

        assertThat(pricingType.isFree()).isTrue();
    }

    @DisplayName("유료강의인지 확인한다")
    @Test
    void isLimitEnrollment() {
        PricingType pricingType = PricingType.PAID;

        assertThat(pricingType.isPaid()).isTrue();
    }
}
