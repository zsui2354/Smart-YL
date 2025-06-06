package org.hae.yl.controller.API.comadmin;

import com.github.pagehelper.PageInfo;
import org.hae.yl.dto.DiscussionDetailDTO;
import org.hae.yl.entity.Comment;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.facade.IssuesFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/comadmin/issues")
public class ApiComadmin_IssuesController {

    @Resource
    private IssuesFacade issuesFacade;

    /**
     * 创建一个新的议题（类似 GitHub Issue）
     */
    public void createDiscussion(Discussion discussion){
        issuesFacade.createDiscussion(discussion);
    }

    /**
     * 给某个讨论添加评论
     */
    public void addComment(@RequestBody Comment comment){
        issuesFacade.addComment(comment);
    }

    /**
     * 查询某个讨论的完整内容（包含评论列表）
     */
    public DiscussionDetailDTO getDiscussionDetail(int discussionId){
        return issuesFacade.getDiscussionDetail(discussionId);
    }

    /**
     * 删除评论（需要判断用户是否为本人或管理员）
     */
    public void deleteComment(HttpServletRequest request, int id){
        issuesFacade.deleteComment(request,id);
    }

    /**
     * 查询对应ID议题中的所有评论
     */
    public List<Comment> SelectIssuesComment(int id){
        return issuesFacade.SelectIssuesComment(id);
    }

    /**
     * 查询对应ID议题中的所有评论的ID 集合
     */
    public List<Integer> SelectIssuesCommentId(int id){
        return issuesFacade.SelectIssuesCommentId(id);
    }

    /**
     * 删除议题（需要判断用户是否为本人或管理员）
     */
    public void deleteDiscussion(HttpServletRequest request,int id){
        issuesFacade.deleteDiscussion(request,id);
    }

    /**
     * 查询所有讨论列表（分页）
     */
    @GetMapping("/list")
    public PageInfo<Discussion> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        return issuesFacade.queryByPage(pageNum, pageSize);
    }
}
