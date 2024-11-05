package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SessionStudents {
    private List<SessionStudent> students;
    private List<SessionStudent> approvedStudents = new ArrayList<>();

    public SessionStudents() {
        students = new ArrayList<>();
    }

    public SessionStudents(List<SessionStudent> students) {
        this.students = students;
    }

    public void addStudent(SessionStudent student) {
        checkAddStudent(student);
        students.add(student);
    }

    public void approveStudent() {
        for (SessionStudent student : students) {
            addApprovedStudent(student);
        }
    }

    private void addApprovedStudent(SessionStudent student) {
        if(student.isSelected()){
            approvedStudents.add(student);
        }
    }

    public void cancelStudent() {
        for (SessionStudent student : students) {
            removeUnApprovedStudent(student);
        }
    }

    private void removeUnApprovedStudent(SessionStudent student) {
        if(student.isNotSelected()){
            approvedStudents.remove(student);
            students.remove(student);
        }
    }

    public int size() {
        return students.size();
    }

    public int approvedSize() {
        return approvedStudents.size();
    }

    private void checkAddStudent(SessionStudent student) {
        checkExistingStudent(student);
    }

    private void checkExistingStudent(SessionStudent student) {
        boolean isExistingStudent = students.stream()
                .map(SessionStudent::getNsUserId)
                .anyMatch(nsUserId -> student.getNsUserId().equals(nsUserId));

        if (isExistingStudent) {
            throw new IllegalStateException("이미 수강신청한 유저입니다.");
        }
    }

    public List<SessionStudent> getStudents() {
        return students;
    }

    public List<SessionStudent> getApprovedStudents() {
        return approvedStudents;
    }
}
