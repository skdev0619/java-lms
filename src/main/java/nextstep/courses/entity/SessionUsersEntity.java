package nextstep.courses.entity;

import nextstep.courses.domain.SessionStudents;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class SessionUsersEntity {
    private Long id;
    private Long session_id;
    private Long ns_user_id;
    private Long creator_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public SessionUsersEntity(Long session_id, Long ns_user_id, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(null, session_id, ns_user_id, creator_id, created_at, updated_at);
    }

    public SessionUsersEntity(Long id, Long session_id, Long ns_user_id, Long creator_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.session_id = session_id;
        this.ns_user_id = ns_user_id;
        this.creator_id = creator_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static SessionStudents of(int availableSeat, List<SessionUsersEntity> sessionUsers) {
        Set<Long> studentIds = sessionUsers.stream()
                .map(SessionUsersEntity::getNs_user_id)
                .collect(toSet());
        return new SessionStudents(availableSeat, studentIds);
    }

    public static List<SessionUsersEntity> toList(Long sessonId, SessionStudents student, NsUser loginUser){
        return student.getStudentIds().stream()
                .map(user -> new SessionUsersEntity(sessonId, user, loginUser.getId(), LocalDateTime.now(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public Long getNs_user_id() {
        return ns_user_id;
    }

    public void setNs_user_id(Long ns_user_id) {
        this.ns_user_id = ns_user_id;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
