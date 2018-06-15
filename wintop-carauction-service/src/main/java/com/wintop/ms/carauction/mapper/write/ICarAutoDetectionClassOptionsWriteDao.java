package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ICarAutoDetectionClassOptionsWriteDao {

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
    int insert(CarAutoDetectionClassOptions record);

    /**
     * �������Բ�Ϊ�յļ�¼
     */
    int insertSelective(CarAutoDetectionClassOptions record);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     * �����������¼�¼
     */
    int updateByExample(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClassOptions record);

    /**
     * �����������¼�¼
     */
    int updateByPrimaryKey(CarAutoDetectionClassOptions record);
}