package nextstep.courses.domain;

public enum PricingType {
    FREE("무료"),
    PAID("유료");

    private final String description;

    PricingType(String description) {
        this.description = description;
    }

    public boolean isFree() {
        return this == FREE;
    }

    public boolean isPaid() {
        return this == PAID;
    }
}
