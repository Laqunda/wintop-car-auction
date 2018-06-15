package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarAutoConfDetail implements Serializable {

    private static final long serialVersionUID = 7106530788828276629L;
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 配置标题id
     */
    private Long confTitleId;

    /**
     * 车辆配置名称
     */
    private String confTitleName;

    /**
     * 配置选项编号
     */
    private String confOption;

    /**
     * 配置选项中文
     */
    private String confOptionCn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 车辆ID
     */
    public Long getAutoId() {
        return autoId;
    }

    /**
     * @param autoId 
	 *            车辆ID
     */
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    /**
     * @return 配置标题id
     */
    public Long getConfTitleId() {
        return confTitleId;
    }

    /**
     * @param confTitleId 
	 *            配置标题id
     */
    public void setConfTitleId(Long confTitleId) {
        this.confTitleId = confTitleId;
    }

    /**
     * @return 车辆配置名称
     */
    public String getConfTitleName() {
        return confTitleName;
    }

    /**
     * @param confTitleName 
	 *            车辆配置名称
     */
    public void setConfTitleName(String confTitleName) {
        this.confTitleName = confTitleName;
    }

    /**
     * @return 配置选项编号
     */
    public String getConfOption() {
        return confOption;
    }

    /**
     * @param confOption 
	 *            配置选项编号
     */
    public void setConfOption(String confOption) {
        this.confOption = confOption;
    }

    /**
     * @return 配置选项中文
     */
    public String getConfOptionCn() {
        return confOptionCn;
    }

    /**
     * @param confOptionCn 
	 *            配置选项中文
     */
    public void setConfOptionCn(String confOptionCn) {
        this.confOptionCn = confOptionCn;
    }
}