package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoDetectionClassWriteDao {
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
    int insert(CarAutoDetectionClass record);

    /**
     * �������Բ�Ϊ�յļ�¼
     */
    int insertSelective(CarAutoDetectionClass record);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     * �����������¼�¼
     */
    int updateByExample(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClass record);

    /**
     * �����������¼�¼
     */
    int updateByPrimaryKey(CarAutoDetectionClass record);
}