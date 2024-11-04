package nextstep.courses.domain;

public class Status {
    private final ProgressStatus progressStatus;
    private final boolean recruitFlag;

    public Status(ProgressStatus progressStatus, boolean recruitFlag) {
        this.progressStatus = progressStatus;
        this.recruitFlag = recruitFlag;
    }

    public boolean canEnroll(){
        return progressStatus.isNotFinished() && recruitFlag;
    }

    public boolean canNotEnroll(){
        return !canEnroll();
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public boolean isRecruitFlag() {
        return recruitFlag;
    }
}
