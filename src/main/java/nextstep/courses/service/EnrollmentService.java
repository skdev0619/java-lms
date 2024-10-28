package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void enrollSession(NsUser loginUser, long courseId, Session session, int payAmount) {
        Course course = courseRepository.findById(courseId);
        session.enrollStudent(loginUser, payAmount);
        course.addSession(session);
    }
}
