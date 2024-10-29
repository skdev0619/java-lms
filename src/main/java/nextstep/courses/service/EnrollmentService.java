package nextstep.courses.service;

import nextstep.courses.domain.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    @Transactional
    public void enrollSession(EnrollRequest request) {
        Session session = request.getSession();
        session.enrollStudent(request.getLoginUser(), request.getPayAmount());
    }
}
