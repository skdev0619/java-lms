package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionStudents {

    private final int availableSeats;
    private final Set<Long> studentIds;

    public SessionStudents(int availableSeats){
        this(availableSeats, new HashSet<>());
    }

    public SessionStudents(int availableSeats, Set<Long> studentIds) {
        this.availableSeats = availableSeats;
        this.studentIds = studentIds;
    }

    public void addStudent(Pricing pricing, NsUser user) {
        checkAddStudent(pricing, user);
        studentIds.add(user.getId());
    }

    public int size() {
        return studentIds.size();
    }

    private void checkAddStudent(Pricing pricing, NsUser user) {
        if (pricing.isFree()) {
            return;
        }
        checkFullSession();
        checkExistingStudent(user);
    }

    private void checkFullSession() {
        if (studentIds.size() == availableSeats) {
            throw new IllegalStateException("이 강의는 정원이 초과되었습니다.");
        }
    }

    private void checkExistingStudent(NsUser user) {
        boolean isExistingStudent = studentIds.contains(user.getId());

        if (isExistingStudent) {
            throw new IllegalStateException("이미 수강신청한 유저입니다.");
        }
    }

    public Set<Long> getStudentIds() {
        return studentIds;
    }
}
