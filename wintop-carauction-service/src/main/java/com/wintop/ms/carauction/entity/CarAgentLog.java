package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAgentLog implements Serializable {

    private static final long serialVersionUID = -2779433379911306418L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 代办主表id
     */
    private Long agentId;

    /**
     * 议价状态：1等待处理；2确认过户事宜，3出票，4出牌，5交档，6提档，7手续回传，8回传审核不通过，9回传审核完成
     */
    private String status;

    /**
     * 状态名称ID
     */
    private String statusCn;

    /**
     * 日志详细说明
     */
    private String logMsg;

    /**
     * 提交时间
     */
    private Date upTime;

    /**
     * 审核时间
     */
    private Date authTime;

    /**
     * 审核意见
     */
    private String authMsg;

    /**
     * 审核人
     */
    private String authManager;

    /**
     * 上传资料内容
     */
    private String upDoc;

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 代办主表id
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * @param agentId 
	 *            代办主表id
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * @return 议价状态：1等待处理；2确认过户事宜，3出票，4出牌，5交档，6提档，7手续回传，8回传审核不通过，9回传审核完成
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            议价状态：1等待处理；2确认过户事宜，3出票，4出牌，5交档，6提档，7手续回传，8回传审核不通过，9回传审核完成
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 状态名称ID
     */
    public String getStatusCn() {
        return statusCn;
    }

    /**
     * @param statusCn 
	 *            状态名称ID
     */
    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    /**
     * @return 日志详细说明
     */
    public String getLogMsg() {
        return logMsg;
    }

    /**
     * @param logMsg 
	 *            日志详细说明
     */
    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    /**
     * @return 提交时间
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * @param upTime 
	 *            提交时间
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    /**
     * @return 审核时间
     */
    public Date getAuthTime() {
        return authTime;
    }

    /**
     * @param authTime 
	 *            审核时间
     */
    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    /**
     * @return 审核意见
     */
    public String getAuthMsg() {
        return authMsg;
    }

    /**
     * @param authMsg 
	 *            审核意见
     */
    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }

    /**
     * @return 审核人
     */
    public String getAuthManager() {
        return authManager;
    }

    /**
     * @param authManager 
	 *            审核人
     */
    public void setAuthManager(String authManager) {
        this.authManager = authManager;
    }

    /**
     * @return 上传资料内容
     */
    public String getUpDoc() {
        return upDoc;
    }

    /**
     * @param upDoc 
	 *            上传资料内容
     */
    public void setUpDoc(String upDoc) {
        this.upDoc = upDoc;
    }
}