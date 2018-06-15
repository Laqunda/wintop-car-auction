package com.wintop.ms.carauction.model;
import com.wintop.ms.carauction.entity.CarQuestion;
import com.wintop.ms.carauction.mapper.read.ICarQuestionReadDao;
import com.wintop.ms.carauction.mapper.write.ICarQuestionWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
@Repository
public class CarQuestionModel {
    @Autowired
    private ICarQuestionReadDao readDao;
    @Autowired
    private ICarQuestionWriteDao writeDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarQuestion> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarQuestion selectById(Long id){
        return readDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarQuestion carQuestion){
        return writeDao.insert(carQuestion);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarQuestion carQuestion){
        return writeDao.updateByIdSelective(carQuestion);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarQuestion carQuestion){
        return writeDao.updateById(carQuestion);
    }

    /**
     *  根据问题分类查询该分类下面的问题集
     *  @Autor 付陈林
     *  @Time  2018-3-5
     */
    public List<CarQuestion> selectByClassifyId(Long classifyId){
        return readDao.selectByClassifyId(classifyId);
    }
    /**
     *  根据问题分类编码查询该分类下面的问题集
     *  @Autor zhangzijuan
     *  @Time  2018-3-6
     */
    public List<CarQuestion> getQuestionByCode(String code){
        return readDao.getQuestionByCode(code);
    }

    /**
     * 查询首页展示
     * @return
     */
    public List<CarQuestion> selectIndexQuestion(int limit){
        return readDao.selectIndexQuestion(limit);
    }

    /**
     * 根据分类id删除问题
     * @param classifyId
     * @return
     */
    public int deleteByClassifyId(Long classifyId){
        return writeDao.deleteByClassifyId(classifyId);
    }
}
