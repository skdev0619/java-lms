package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUsersRepository;
import nextstep.courses.entity.SessionUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final SessionUsersRepository sessionUsersRepository;

    @Autowired
    public EnrollmentService(SessionUsersRepository sessionUsersRepository) {
        this.sessionUsersRepository = sessionUsersRepository;
    }

    @Transactional
    public void enrollSession(EnrollRequest request) {
        Session session = request.getSession();
        session.enrollStudent(request.getLoginUser(), request.getPayAmount());

        sessionUsersRepository.deleteBySessionId(session.getId());
        List<SessionUsersEntity> users = SessionUsersEntity.toList(
                session.getId(),
                session.getStudents(),
                request.getLoginUser());
        sessionUsersRepository.bulkSave(users);
    }
}
