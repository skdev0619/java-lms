package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.entity.SessionUsersEntity;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class EnrollmentServiceTest {
    
    @Autowired
    private EnrollmentService service;

    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    SessionImageRepository sessionImageRepository;
    
    @Autowired
    SessionUsersRepository sessionUsersRepository;
    
    private Session session;

    @BeforeEach
    void setUp() {
        SessionEntity sessionById = sessionRepository.findById(10000L);
        SessionImageEntity imageById = sessionImageRepository.findById(10000L);
        List<SessionUsersEntity> sessionUsers = sessionUsersRepository.findBySessionId(10000L);

        SessionImage image = imageById.fromEntity();
        SessionStudent student = SessionUsersEntity.of(sessionById.getAvailable_seat(), sessionUsers);
        session = sessionById.of(image, student);
    }

    @DisplayName("수강 신청 성공하면 수강 인원 목록에 유저가 포함된다")
    @Test
    void enrollSession() {
        EnrollRequest request = new EnrollRequest(NsUserTest.SANJIGI, session, 10000);

        service.enrollSession(request);

        assertThat(session.getStudent().size()).isEqualTo(2);
    }
}