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
public class Activity {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "place")
    private String place;

    @TableField(value = "start_time")
    private LocalDateTime start_time;

    @TableField(value = "end_time")
    private LocalDateTime end_time;

    @TableField(value = "max_participants")
    private Integer max_participants;

    @TableField(value = "created_at")
    private LocalDateTime created_at;
}
