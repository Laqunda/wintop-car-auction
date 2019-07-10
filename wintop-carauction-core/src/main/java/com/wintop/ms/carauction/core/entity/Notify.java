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

    public static class Assess {
        /**
         * 新增评估
         */
        private Integer carAssessAdd = -1;
        /**
         * 申请采购
         */
        private Integer carAssessOrderAdd = -1;


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

        /**
         * 车辆库存
         */
        private Integer carStockCount = -1;

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

        public Integer getCarStockCount() {
            return carStockCount;
        }

        public void setCarStockCount(Integer carStockCount) {
            this.carStockCount = carStockCount;
        }
    }

    public static  class Order {
        /**
         * 线上订单
         */
        private Integer insertOnlineOrder = -1;

        /**
         * 零售订单
         */
        private Integer insertRetailOrder = -1;

        public Integer getInsertOnlineOrder() {
            return insertOnlineOrder;
        }

        public void setInsertOnlineOrder(Integer insertOnlineOrder) {
            this.insertOnlineOrder = insertOnlineOrder;
        }

        public Integer getInsertRetailOrder() {
            return insertRetailOrder;
        }

        public void setInsertRetailOrder(Integer insertRetailOrder) {
            this.insertRetailOrder = insertRetailOrder;
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
