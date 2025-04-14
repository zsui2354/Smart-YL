package org.hae.yl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hae.yl.entity.Service_appointment;

import java.util.List;

@Mapper
public interface Service_appointmentMapper extends BaseMapper<Service_appointment> {

    /**
     * 查询所有
     */
    List<Service_appointment> SelectAll();

    /**
     * 根据Id查询
     */
    Service_appointment SelectById(int id);

    /**
     * 根据 Id 修改
     */
    void Update(int id, Service_appointment service_appointment);

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    void DeleteById(int id);

    void DeleteByIdbatch(List<Integer> ids);

    /**
     * 增加
     */
    void Insert(Service_appointment service_appointment);
}