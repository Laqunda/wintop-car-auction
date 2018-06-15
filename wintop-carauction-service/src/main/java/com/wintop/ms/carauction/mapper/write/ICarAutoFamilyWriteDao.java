package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoFamilyWriteDao {

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
    int insert(CarAutoFamily record);

    /**
     * �������Բ�Ϊ�յļ�¼
     */
    int insertSelective(CarAutoFamily record);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByExampleSelective(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     * �����������¼�¼
     */
    int updateByExample(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByPrimaryKeySelective(CarAutoFamily record);

    /**
     * �����������¼�¼
     */
    int updateByPrimaryKey(CarAutoFamily record);
}