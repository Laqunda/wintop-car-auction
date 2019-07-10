package com.wintop.ms.carauction.entity;

import java.io.Serializable;



/**
 * 主题模板关联周
 * 
 * @author mazg
 * @date 2019-05-24 15:13:55
 */
public class CarLocaleAuctionWeek implements Serializable {
	private static final long serialVersionUID = 8422265331017497809L;
	//主键id
	private Long id;
	//场次模板id
	private Long templateId;
	//周
	private String themeWeek;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：场次模板id
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	/**
	 * 获取：场次模板id
	 */
	public Long getTemplateId() {
		return templateId;
	}
	/**
	 * 设置：周
	 */
	public void setThemeWeek(String themeWeek) {
		this.themeWeek = themeWeek;
	}
	/**
	 * 获取：周
	 */
	public String getThemeWeek() {
		return themeWeek;
	}
}
