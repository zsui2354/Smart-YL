package org.hae.yl.service.Impl;

import org.hae.yl.entity.Comment;
import org.hae.yl.mapper.CommentMapper;
import org.hae.yl.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper contentMapper;

    @Override
    public List<Comment> SelectAll() {
        return contentMapper.SelectAll();
    }

    @Override
    public Comment SelectById(int id) {
        return contentMapper.selectById(id);
    }

    @Override
    public List<Comment> SelectByTheme(int id) {
        return contentMapper.SelectByTheme(id);
    }

    @Override
    public List<Integer> SelectByThemeReturnIds(int id) {
        return contentMapper.SelectByThemeReturnIds(id);
    }

    @Override
    public void Insert(Comment content) {
        contentMapper.Insert(content);
    }

    @Override
    public void Update(int id, Comment content) {
        contentMapper.Update(id, content);
    }

    @Override
    public void DeleteById(int id) {
        contentMapper.DeleteById(id);
    }

    @Override
    public void DeleteByIdbatch(List<Integer> ids) {
        contentMapper.DeleteByIdbatch(ids);
    }
}
