package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoStyleWriteDao {

    /**
     * ��������ɾ����¼
     */
    int deleteByExample(Criteria example);

    /**
     * ��������ɾ����¼
     */
    int deleteByPrimaryKey(Long id);

    /**
     * �����¼,���ܼ�¼����������Ƿ�Ϊ��
     */
    int insert(CarAutoStyle record);

    /**
     * �������Բ�Ϊ�յļ�¼
     */
    int insertSelective(CarAutoStyle record);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByExampleSelective(@Param("record") CarAutoStyle record, @Param("condition") Map<String, Object> condition);

    /**
     * �����������¼�¼
     */
    int updateByExample(@Param("record") CarAutoStyle record, @Param("condition") Map<String, Object> condition);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByPrimaryKeySelective(CarAutoStyle record);

    /**
     * �����������¼�¼
     */
    int updateByPrimaryKey(CarAutoStyle record);
}