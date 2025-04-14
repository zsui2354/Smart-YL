package org.hae.yl.service;

import org.hae.yl.entity.Message_board;

import java.util.List;

public interface Message_boardService {

    /**
     * 查询所有
     */
    List<Message_board> SelectAll();

    /**
     * 根据Id查询
     */
    Message_board SelectById(int id);

    /**
     * 模糊查询
     */
    List<Message_board> SelectByLike(String like);

    /**
     * 根据 Id 修改
     */
    void Update(int id, Message_board message_board);

    /**
     * 根据 Id 删除
     * 根据 Id 批量删除
     */
    void DeleteById(int id);

    void DeleteByIdbatch(List<Integer> ids);

    /**
     * 增加
     */
    void Insert(Message_board message_board);
}
