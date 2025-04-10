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
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "real_name")
    private String real_name;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "role_id")
    private Integer role_id;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "created_at")
    private LocalDateTime created_at;

    private String token;


//            +------------+--------------+------+-----+-------------------+-------------------+
//            | Field      | Type         | Null | Key | Default           | Extra             |
//            +------------+--------------+------+-----+-------------------+-------------------+
//            | id         | bigint       | NO   | PRI | NULL              | auto_increment    |
//            | username   | varchar(50)  | NO   |     | NULL              |                   |
//            | password   | varchar(255) | NO   |     | NULL              |                   |
//            | real_name  | varchar(50)  | YES  |     | NULL              |                   |
//            | phone      | varchar(20)  | YES  |     | NULL              |                   |
//            | avatar     | varchar(255) | YES  |     | NULL              |                   |
//            | role_id    | bigint       | YES  | MUL | NULL              |                   |
//            | status     | tinyint      | YES  |     | 1                 |                   |
//            | created_at | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
//            +------------+--------------+------+-----+-------------------+-------------------+
//            9 rows in set (0.22 sec)


//            mysql> select * from user;
//            +----+----------+----------------------------+-----------+-----------+----------------------+---------+--------+---------------------+
//            | id | username | password                   | real_name | phone     | avatar               | role_id | status | created_at          |
//            +----+----------+----------------------------+-----------+-----------+----------------------+---------+--------+---------------------+
//            |  1 | sysadmin | encrypted_password_example | sysadmin  | 123456789 | /avatars/johndoe.jpg |       3 |      1 | 2025-04-09 09:01:31 |
//            |  2 | user     | user                       | user      | 123456789 | /avatars/johndoe.jpg |       1 |      1 | 2025-04-09 09:02:16 |
//            |  3 | comadmin | comadmin                   | comadmin  | 123456789 | /avatars/johndoe.jpg |       2 |      1 | 2025-04-09 09:03:36 |
//            |  4 | staff    | staff                      | staff     | 123456789 | /avatars/johndoe.jpg |       4 |      1 | 2025-04-09 09:03:56 |
//            +----+----------+----------------------------+-----------+-----------+----------------------+---------+--------+---------------------+
//            4 rows in set (0.04 sec)
}
