package nextstep.courses.domain;

public class Pricing {

    private final PricingType pricingType;
    private final int price;

    public Pricing(PricingType pricingType, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("강의 금액은 0원 이상이어야 합니다.");
        }
        if (pricingType == PricingType.FREE) {
            price = 0;
        }

        this.pricingType = pricingType;
        this.price = price;
    }

    public boolean canEnroll(int payAmount) {
        if (pricingType.isFree()) {
            return true;
        }
        return price == payAmount;
    }

    public boolean isFree() {
        return pricingType.isFree();
    }

    public boolean canNotEnroll(int payAmount) {
        return !canEnroll(payAmount);
    }
}
