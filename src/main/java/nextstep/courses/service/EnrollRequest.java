package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;

public class EnrollRequest {
    private final NsUser loginUser;
    private final Session session;
    private final int payAmount;

    public EnrollRequest(NsUser loginUser, Session session, int payAmount) {
        this.loginUser = loginUser;
        this.session = session;
        this.payAmount = payAmount;
    }

    public static EnrollRequest from(NsUser loginUser, Session session, int payAmount) {
        return new EnrollRequest(loginUser, session, payAmount);
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
