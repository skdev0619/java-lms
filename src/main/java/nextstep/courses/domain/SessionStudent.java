package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionStudent {

    private final int availableSeats;
    private List<NsUser> students = new ArrayList<NsUser>();

    public SessionStudent(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void addStudent(Pricing pricing, NsUser user) {
        checkAddStudent(pricing, user);
        students.add(user);
    }

    private void checkAddStudent(Pricing pricing, NsUser user) {
        if (pricing.isFree()) {
            return;
        }

        if (students.size() == availableSeats) {
            throw new IllegalStateException("이 강의는 정원이 초과되었습니다.");
        }
    }
}
