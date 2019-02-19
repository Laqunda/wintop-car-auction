package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Author: 付陈林.
 * @Description: 现场拍--竞拍场次
 * @Date: 18:32 on 2018/3/6.
 * @Modified by:
 */
public class CarLocaleAuction implements Serializable {

    private static final long serialVersionUID = 8127231230919715585L;
    /**
     * 现场拍--竞拍场次--主键
     */
    private Long id;

    /**
     * 现场竞拍--场次编号
     */
    private Integer code;

    /**
     * 现场拍--场次主题
     */
    private String title;

    /**
     * 关联基站物理ID
     */
    private String stationRealId;

    /**
     * 现场拍--拍卖场次--可见范围，该场次的车在那些用户组中可见,多选，用字符串进行拼接
     * */
    private String  regionId;


    /**
     * 现场拍--拍卖场次--所在城市
     * */
    private Long cityId;

    /**
     * 现场拍--拍卖场次--场次封面海报
     */
    private String poster;

    /**
     * 现场拍--拍卖场次--详细地址：省市区详细
     */
    private String address;

    /**
     * 现场拍--地址--gps经度坐标
     */
    private BigDecimal gpsLongitude;

    /**
     * 现场拍--地址--gps纬度
     */
    private BigDecimal gpsLatitude;

    /**
     * 现场拍--拍卖场次--开拍时间
     */
    private Date startTime;

    /**
     * 代办公司ID
     */
    private Long corporateAgent;

    /**
     * 场次状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
     */
    private String status;

    /**
     * 看车联系人
     */
    private String seeCarMan;

    /**
     *看车联系电话
     * */
    private String seeCarPhone;

    /**
     * 可看车时间段
     */
    private String seeCarTime;

    /**
     * 创建人Id
     * */
    private Long createPerson;

    /**
     * 创建人姓名
     * */
    private String createPersonName;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 修改人
     * */
    private Long modifyPerson;

    /**
     * 修改时间
     * */
    private Date modifyTime;

    /**
     * 删除人
     * */
    private Long delPerson;

    /**
     * 删除时间
     * */
    private Date delTime;

    /**
     * 删除状态
     * */
    private String delFlag;

    /**
     * 车辆数量
     * */
    private Integer carNum;

    private String mainPhoto;
    private String autoInfoName;
    private BigDecimal startingPrice;
    private Date auctionStartTime;
    private Long carId;
    private String carAutoNo;
    private String name;

    private BigDecimal transactionFee;
    private String publishUserName;
    private Date auctionEndTime;

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public Date getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Date auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    /**
     * @return 拍卖活动表
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            拍卖活动表
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 拍卖活动编号
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code 
	 *            拍卖活动编号
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return 封面海报
     */
    public String getPoster() {
        return poster;
    }

    /**
     * @param poster 
	 *            封面海报
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 
	 *            标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 详细地址：省市区详细
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            详细地址：省市区详细
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return gps经度坐标
     */
    public BigDecimal getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * @param gpsLongitude 
	 *            gps经度坐标
     */
    public void setGpsLongitude(BigDecimal gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    /**
     * @return gps纬度
     */
    public BigDecimal getGpsLatitude() {
        return gpsLatitude;
    }

    /**
     * @param gpsLatitude 
	 *            gps纬度
     */
    public void setGpsLatitude(BigDecimal gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    /**
     * @return 开拍时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime 
	 *            开拍时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return 代办公司ID
     */
    public Long getCorporateAgent() {
        return corporateAgent;
    }

    /**
     * @param corporateAgent 
	 *            代办公司ID
     */
    public void setCorporateAgent(Long corporateAgent) {
        this.corporateAgent = corporateAgent;
    }

    /**
     * @return 状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 看车人
     */
    public String getSeeCarMan() {
        return seeCarMan;
    }

    /**
     * @param seeCarMan 
	 *            看车人
     */
    public void setSeeCarMan(String seeCarMan) {
        this.seeCarMan = seeCarMan;
    }

    /**
     * @return 看车时间
     */
    public String getSeeCarTime() {
        return seeCarTime;
    }

    /**
     * @param seeCarTime 
	 *            看车时间
     */
    public void setSeeCarTime(String seeCarTime) {
        this.seeCarTime = seeCarTime;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getSeeCarPhone() {
        return seeCarPhone;
    }

    public void setSeeCarPhone(String seeCarPhone) {
        this.seeCarPhone = seeCarPhone;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(Date auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public String getStationRealId() {
        return stationRealId;
    }

    public void setStationRealId(String stationRealId) {
        this.stationRealId = stationRealId;
    }
}