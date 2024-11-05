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
    private Long sessionId;
    private final List<Long> studentIds;

    public SessionStudents(Long sessionId){
        this(sessionId, new ArrayList<>());
    }

    public SessionStudents(Long sessionId, List<Long> studentIds) {
        this.sessionId = sessionId;
        this.studentIds = studentIds;
    }

    public void addStudent(NsUser user) {
        checkAddStudent(user);
        studentIds.add(user.getId());
    }

    public int size() {
        return studentIds.size();
    }

    private void checkAddStudent(NsUser user) {
        checkExistingStudent(user);
    }

    private void checkExistingStudent(NsUser user) {
        boolean isExistingStudent = studentIds.contains(user.getId());

        if (isExistingStudent) {
            throw new IllegalStateException("이미 수강신청한 유저입니다.");
        }
    }

    public Long getSessionId() {
        return sessionId;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }
}
