package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionStudent {

    private final int availableSeats;
    private Set<NsUser> students = new HashSet<>();

    public SessionStudent(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void addStudent(Pricing pricing, NsUser user) {
        checkAddStudent(pricing, user);
        students.add(user);
    }

    public int size() {
        return students.size();
    }

    private void checkAddStudent(Pricing pricing, NsUser user) {
        if (pricing.isFree()) {
            return;
        }
        checkFullSession();
        checkExistingStudent(user);
    }

    private void checkFullSession() {
        if (students.size() == availableSeats) {
            throw new IllegalStateException("이 강의는 정원이 초과되었습니다.");
        }
    }

    private void checkExistingStudent(NsUser user) {
        boolean isExistingStudent = students.stream()
                .anyMatch(student -> student.equals(user));
        if (isExistingStudent) {
            throw new IllegalStateException("이미 수강신청한 유저입니다.");
        }
    }
}
