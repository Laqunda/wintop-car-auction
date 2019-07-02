package com.wintop.ms.carauction.core.entity;

/**
 * 角标消息提醒
 */
public class Notify {

    /**
     * 评估管理，采购管理
     */
    private Assess assess = new Assess();
    /**
     * 库存管理
     */
    private Stock stock = new Stock();
    /**
     * 订单管理
     */
    private Order order = new Order();
    /**
     * 查博士-维修记录
     */
    private Integer cbs = -1;

    public Notify() {
    }

    public Notify(Assess assess, Stock stock, Order order, Integer cbs) {
        this.assess = assess;
        this.stock = stock;
        this.order = order;
        this.cbs = cbs;
    }

    public static class Assess {
        /**
         * 新增评估
         */
        private Integer carAssessAdd = -1;
        /**
         * 申请采购
         */
        private Integer carAssessOrderAdd = -1;
        /**
         * 采购驳回
         */
        private Integer carAssessReject = -1;
        /**
         * 采购通过
         */
        private Integer carAssessPass = -1;


        public Integer getCarAssessAdd() {
            return carAssessAdd;
        }

        public void setCarAssessAdd(Integer carAssessAdd) {
            this.carAssessAdd = carAssessAdd;
        }

        public Integer getCarAssessOrderAdd() {
            return carAssessOrderAdd;
        }

        public void setCarAssessOrderAdd(Integer carAssessOrderAdd) {
            this.carAssessOrderAdd = carAssessOrderAdd;
        }

        public Integer getCarAssessReject() {
            return carAssessReject;
        }

        public void setCarAssessReject(Integer carAssessReject) {
            this.carAssessReject = carAssessReject;
        }

        public Integer getCarAssessPass() {
            return carAssessPass;
        }

        public void setCarAssessPass(Integer carAssessPass) {
            this.carAssessPass = carAssessPass;
        }
    }

    public static  class Stock {
        /**
         * 创建线上车辆
         */
        private Integer insertOnlineCar = -1;

        /**
         * 创建现场车辆
         */
        private Integer insertOnSiteCar = -1;

        public Integer getInsertOnlineCar() {
            return insertOnlineCar;
        }

        public void setInsertOnlineCar(Integer insertOnlineCar) {
            this.insertOnlineCar = insertOnlineCar;
        }

        public Integer getInsertOnSiteCar() {
            return insertOnSiteCar;
        }

        public void setInsertOnSiteCar(Integer insertOnSiteCar) {
            this.insertOnSiteCar = insertOnSiteCar;
        }
    }

    public static  class Order {
        /**
         * 个人车源-创建订单
         */
        private Integer insertPersonOrder = -1;
        /**
         * 店铺车源
         */
        private Integer insertStoreOrder = -1;

        public Integer getInsertPersonOrder() {
            return insertPersonOrder;
        }

        public void setInsertPersonOrder(Integer insertPersonOrder) {
            this.insertPersonOrder = insertPersonOrder;
        }

        public Integer getInsertStoreOrder() {
            return insertStoreOrder;
        }

        public void setInsertStoreOrder(Integer insertStoreOrder) {
            this.insertStoreOrder = insertStoreOrder;
        }
    }


    public Assess getAssess() {
        return assess;
    }

    public void setAssess(Assess assess) {
        this.assess = assess;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getCbs() {
        return cbs;
    }

    public void setCbs(Integer cbs) {
        this.cbs = cbs;
    }
}
