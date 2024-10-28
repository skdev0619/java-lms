package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentsTest {
    
    @DisplayName("결제 금액의 총 합을 구한다")
    @Test
    void getTotalAmount(){
        Payment payment1 = createPayment(1000);
        Payment payment2 = createPayment(2000);
        Payments payments = new Payments(List.of(payment1, payment2));

        assertThat(payments.getTotalAmount()).isEqualTo(3000);
    }

    private Payment createPayment(long amount){
        return new Payment("id", 1L, 1L, amount);
    }
}
