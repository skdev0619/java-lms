package nextstep.courses.domain;

public enum PricingType {
    FREE,
    PAID;
    
    public boolean isLimitEnrollment() {
        return this == PAID;
    }
}
