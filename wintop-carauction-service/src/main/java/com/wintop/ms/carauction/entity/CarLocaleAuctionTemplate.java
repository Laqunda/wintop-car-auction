package com.wintop.ms.carauction.entity;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 现场拍主题表
 * 
 * @author mazg
 * @date 2019-05-23 17:40:13
 */
public class CarLocaleAuctionTemplate extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8691772247992736655L;
	private static Map<String, String> weekMap = new HashMap<String, String>(){{
		put("0", "星期日");
		put("1", "星期一");
		put("2", "星期二");
		put("3", "星期三");
		put("4", "星期四");
		put("5", "星期五");
		put("6", "星期六");
	}};
	//竞拍主题ID表
	private Long id;
	//拍卖类型：1、普通线上拍、2现场拍、3委托线上拍
	private String type;
	//封面海报
	private String poster;
	//标题
	private String title;
	//活动所在地区区域
	private Long regionId;
	// 城市名称
	private String cityName;
	//详细地址：省市区详细
	private String address;
	//gps经度坐标
	private BigDecimal gpsLongitude;
	//gps纬度
	private BigDecimal gpsLatitude;
	//开拍时间
	private String startTime;
	//代办公司ID
	private Long corporateAgent;
	//状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
	private String status;
	//拍卖简介
	private String describe;
	//看车人
	private String seeCarMan;
	//看车人电话
	private String seeCarPhone;
	//看车时间
	private String seeCarTime;
	//操作人
	private String editor;
	//编辑时间
	private Date editTime;
	//结束时间
	private Date endTime;
	//选中日期
	private String weeks;
	//停止起始日期
	private Date stopStartTime;
	//停止结束日期
	private Date stopEndTime;
	//-------------------- 展示使用 ----------------
	//选中展示
	private String weekShow;
	//选中日期
	List<CarLocaleAuctionWeek> weekList;
	/**
	 * 设置：竞拍主题ID表
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：竞拍主题ID表
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：拍卖类型：1、普通线上拍、2现场拍、3委托线上拍
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：拍卖类型：1、普通线上拍、2现场拍、3委托线上拍
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：封面海报
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}
	/**
	 * 获取：封面海报
	 */
	public String getPoster() {
		return poster;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：活动所在地区区域
	 */
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	/**
	 * 获取：活动所在地区区域
	 */
	public Long getRegionId() {
		return regionId;
	}
	/**
	 * 设置：详细地址：省市区详细
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址：省市区详细
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：gps经度坐标
	 */
	public void setGpsLongitude(BigDecimal gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	/**
	 * 获取：gps经度坐标
	 */
	public BigDecimal getGpsLongitude() {
		return gpsLongitude;
	}
	/**
	 * 设置：gps纬度
	 */
	public void setGpsLatitude(BigDecimal gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	/**
	 * 获取：gps纬度
	 */
	public BigDecimal getGpsLatitude() {
		return gpsLatitude;
	}
	/**
	 * 设置：开拍时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开拍时间
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置：代办公司ID
	 */
	public void setCorporateAgent(Long corporateAgent) {
		this.corporateAgent = corporateAgent;
	}
	/**
	 * 获取：代办公司ID
	 */
	public Long getCorporateAgent() {
		return corporateAgent;
	}
	/**
	 * 设置：状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：拍卖简介
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：拍卖简介
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：看车人
	 */
	public void setSeeCarMan(String seeCarMan) {
		this.seeCarMan = seeCarMan;
	}
	/**
	 * 获取：看车人
	 */
	public String getSeeCarMan() {
		return seeCarMan;
	}
	/**
	 * 设置：看车人电话
	 */
	public void setSeeCarPhone(String seeCarPhone) {
		this.seeCarPhone = seeCarPhone;
	}
	/**
	 * 获取：看车人电话
	 */
	public String getSeeCarPhone() {
		return seeCarPhone;
	}
	/**
	 * 设置：看车时间
	 */
	public void setSeeCarTime(String seeCarTime) {
		this.seeCarTime = seeCarTime;
	}
	/**
	 * 获取：看车时间
	 */
	public String getSeeCarTime() {
		return seeCarTime;
	}
	/**
	 * 设置：操作人
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	/**
	 * 获取：操作人
	 */
	public String getEditor() {
		return editor;
	}
	/**
	 * 设置：编辑时间
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	/**
	 * 获取：编辑时间
	 */
	public Date getEditTime() {
		return editTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 获取：城市名称
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：城市名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<CarLocaleAuctionWeek> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<CarLocaleAuctionWeek> weekList) {
		this.weekList = weekList;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getWeekShow() {
		if (StringUtils.isNotEmpty(this.weeks)) {
			List<String> tempList = Lists.newArrayList();
			List<String> list = Splitter.on(",").splitToList(weeks);
			list.forEach(item->{
				tempList.add(weekMap.get(item));
			});
			this.weekShow = Joiner.on(",").join(tempList);
			return weekShow;
		}
		return null;
	}

	public void setWeekShow(String weekShow) {
		this.weekShow = weekShow;
	}

	public static String getWeekShow(List<String> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			List<String> tempList = Lists.newArrayList();
			list.forEach(item->{
				tempList.add(weekMap.get(item));
			});
			return Joiner.on(",").join(tempList);

		}
		return null;
	}

	public Date getStopStartTime() {
		return stopStartTime;
	}

	public void setStopStartTime(Date stopStartTime) {
		this.stopStartTime = stopStartTime;
	}

	public Date getStopEndTime() {
		return stopEndTime;
	}

	public void setStopEndTime(Date stopEndTime) {
		this.stopEndTime = stopEndTime;
	}
}
