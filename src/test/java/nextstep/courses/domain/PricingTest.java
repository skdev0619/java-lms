package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PricingTest {

    @DisplayName("강의 금액은 0원 이상이어야 한다")
    @Test
    void validatePrice() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Pricing(PricingType.PAID, -1000))
                .withMessage("강의 금액은 0원 이상이어야 합니다.");
    }

    @DisplayName("무료 강의인 경우 금액 상관없이 수강 가능하다")
    @Test
    void canEnrollByFree() {
        Pricing pricing = new Pricing(PricingType.FREE, 10000);

        assertThat(pricing.canEnroll(0)).isTrue();
    }

    @DisplayName("유료 강의인 경우, 결제 금액과 수강료 일치해야 한다")
    @Test
    void canEnrollByPaid() {
        Pricing pricing = new Pricing(PricingType.PAID, 10000);

        assertThat(pricing.canEnroll(9999)).isFalse();
        assertThat(pricing.canEnroll(10000)).isTrue();
    }
}
