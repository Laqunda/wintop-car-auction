package com.wintop.ms.carauction.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * ����������ѯ��
 */
public class Criteria {
    /**
     * ���������ѯֵ
     */
    private Map<String, Object> condition;

    /**
     * �Ƿ�����
     */
    protected boolean distinct;

    /**
     * �����ֶ�
     */
    protected String orderByClause;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
        this.mysqlLength = example.mysqlLength;
        this.mysqlOffset = example.mysqlOffset;
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        condition.clear();
        orderByClause = null;
        distinct = false;
        this.mysqlOffset = null;
        this.mysqlLength = null;
    }

    /**
     * @param condition 
	 *            ��ѯ����������
	 * @param value
	 *            ��ѯ��ֵ
     */
    public Criteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return (Criteria) this;
    }

    /**
     * @param orderByClause 
	 *            �����ֶ�
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param distinct 
	 *            �Ƿ�����
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    /**
     * @param mysqlOffset 
	 *            ָ�����ؼ�¼�е�ƫ����<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // ������¼�� 6-15
     */
    public void setMysqlOffset(Integer mysqlOffset) {
        this.mysqlOffset = mysqlOffset;
    }

    /**
     * @param mysqlLength 
	 *            ָ�����ؼ�¼�е������Ŀ<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // ������¼�� 6-15
     */
    public void setMysqlLength(Integer mysqlLength) {
        this.mysqlLength = mysqlLength;
    }
}