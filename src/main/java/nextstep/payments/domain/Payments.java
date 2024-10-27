package nextstep.payments.domain;

import java.util.List;

public class Payments {
    private final List<Payment> payments;

    public Payments(List<Payment> payments) {
        this.payments = payments;
    }

    public Long getTotalAmount() {
        return payments.stream()
                .mapToLong(Payment::getAmount)
                .sum();
    }
}
