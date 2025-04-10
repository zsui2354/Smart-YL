package org.hae.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "description")
    private String description;
}
