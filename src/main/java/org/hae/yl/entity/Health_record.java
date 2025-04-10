package org.hae.yl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Health_record {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer user_id;

    @TableField(value = "date")
    private LocalDateTime date;

    @TableField(value = "blood_pressure")
    private String blood_pressure;

    @TableField(value = "heart_rate")
    private Integer heart_rate;

    @TableField(value = "temperature")
    private BigDecimal temperature;

    @TableField(value = "remark")
    private String remark;
}
