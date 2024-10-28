package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void enrollSession(EnrollRequest request) {
        Session session = request.getSession();

        Course course = courseRepository.findById(request.getCourseId());
        session.enrollStudent(request.getLoginUser(), request.getPayAmount());
        course.addSession(session);
    }
}
