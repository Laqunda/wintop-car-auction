package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;

public interface ICarAutoFamilyReadDao {
    /**
     * ����������ѯ��¼����
     */
    int countByExample(Criteria example);

    /**
     * ����������ѯ��¼��
     */
    List<CarAutoFamily> selectByExample(Criteria example);

    /**
     * ����������ѯ��¼
     */
    CarAutoFamily selectByPrimaryKey(Long id);
}