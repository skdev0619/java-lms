package nextstep.courses.domain;

import java.util.Objects;

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

    public PricingType getPricingType() {
        return pricingType;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pricing pricing = (Pricing) o;
        return price == pricing.price && pricingType == pricing.pricingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricingType, price);
    }
}
