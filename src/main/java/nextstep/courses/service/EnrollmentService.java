package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStudent;
import nextstep.courses.domain.SessionUsersRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final SessionRepository sessionRepository;

    private final SessionUsersRepository sessionUsersRepository;

    private final UserRepository userRepository;

    @Autowired
    public EnrollmentService(SessionRepository sessionRepository, SessionUsersRepository sessionUsersRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionUsersRepository = sessionUsersRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void enrollSession(EnrollRequest request) {
        Session session = request.getSession();
        NsUser loginUser = request.getLoginUser();

        sessionRepository.findById(session.getId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 강의입니다.")
        );

        userRepository.findByUserId(loginUser.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 유저입니다.")
        );

        session.enrollStudent(request.getLoginUser(), request.getPayAmount());

        sessionUsersRepository.deleteBySessionId(session.getId());
        List<SessionStudent> users = List.of(
                new SessionStudent(session.getId(), loginUser.getId(), loginUser.getId())
        );
        sessionUsersRepository.bulkSave(users);
    }
}
