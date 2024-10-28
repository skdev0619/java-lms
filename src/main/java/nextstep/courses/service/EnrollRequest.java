package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;

public class EnrollRequest {
    private final long courseId;
    private final NsUser loginUser;
    private final Session session;
    private final int payAmount;

    public EnrollRequest(long courseId, NsUser loginUser, Session session, int payAmount) {
        this.courseId = courseId;
        this.loginUser = loginUser;
        this.session = session;
        this.payAmount = payAmount;
    }

    public static EnrollRequest from(long courseId, NsUser loginUser, Session session, int payAmount) {
        return new EnrollRequest(courseId, loginUser, session, payAmount);
    }

    public long getCourseId() {
        return courseId;
    }

    public NsUser getLoginUser() {
        return loginUser;
    }

    public Session getSession() {
        return session;
    }

    public int getPayAmount() {
        return payAmount;
    }
}
