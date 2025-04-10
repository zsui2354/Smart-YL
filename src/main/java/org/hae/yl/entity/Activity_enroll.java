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
public class Activity_enroll {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer user_id;

    @TableField(value = "activity_id")
    private Integer activity_id;

    @TableField(value = "enrolled_at")
    private LocalDateTime enrolled_at;
}
