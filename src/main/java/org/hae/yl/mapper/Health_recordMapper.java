package org.hae.yl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hae.yl.entity.Health_record;

import java.util.List;

@Mapper
public interface Health_recordMapper extends BaseMapper<Health_record> {

    /**
     * 查询所有
     */
    List<Health_record> SelectAll();

    /**
     * 根据Id查询
     */
    Health_record SelectById(int id);

    /**
     * 根据 Id 修改
     */
    void Update(int id, Health_record health_record);

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    void DeleteById(int id);

    void DeleteByIdbatch(List<Integer> ids);

    /**
     * 增加
     */
    void Insert(Health_record health_record);
}