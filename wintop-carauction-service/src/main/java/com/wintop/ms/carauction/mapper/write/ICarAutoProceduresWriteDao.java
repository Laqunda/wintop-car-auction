package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoProceduresWriteDao {

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
    int insert(CarAutoProcedures record);

    /**
     * �������Բ�Ϊ�յļ�¼
     */
    int insertSelective(CarAutoProcedures record);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByExampleSelective(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     * �����������¼�¼
     */
    int updateByExample(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     * ���������������Բ�Ϊ�յļ�¼
     */
    int updateByPrimaryKeySelective(CarAutoProcedures record);

    /**
     * �����������¼�¼
     */
    int updateByPrimaryKey(CarAutoProcedures record);
}