package com.wintop.ms.carauction.entity;

/**
 * 车辆评估跟进表 car_assess_follow_data
 *
 * @author ruoyi
 * @date 2019-05-05
 */
public class CarAssessFollowData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评估跟进主键ID
     */
    private Long id;
    /**
     * 评估单ID
     */
    private Long assessId;
    /**
     * 评估跟进内容
     */
    private String content;
    /**
     * 评估结果：1评估中，2采购前科，3战败，4确认采购
     */
    private String followResult;
    /**
     * 跟进人
     */
    private Long followUser;

    private String createUser;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public Long getAssessId() {
        return assessId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setFollowResult(String followResult) {
        this.followResult = followResult;
    }

    public String getFollowResult() {
        return followResult;
    }

    public void setFollowUser(Long followUser) {
        this.followUser = followUser;
    }

    public Long getFollowUser() {
        return followUser;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
