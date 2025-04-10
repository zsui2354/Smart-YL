package org.hae.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Service_appointment {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer user_id;

    @TableField(value = "service_id")
    private Integer service_id;

    @TableField(value = "appointment_time")
    private LocalDateTime appointment_time;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "note")
    private String note;
}
