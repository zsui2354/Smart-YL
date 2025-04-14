package org.hae.yl.service;

import org.hae.yl.entity.Service_appointment;

import java.util.List;

public interface Service_appointmentService {

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
