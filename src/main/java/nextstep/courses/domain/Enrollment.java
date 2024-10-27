package nextstep.courses.domain;

public class Enrollment {
    public Enrollment() {
    }

    public void register(Session session, int payAmount) {
        session.decreaseSeats(payAmount);
    }
}
