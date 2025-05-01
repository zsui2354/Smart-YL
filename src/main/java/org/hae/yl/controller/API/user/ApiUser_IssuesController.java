package org.hae.yl.controller.API.user;

import com.github.pagehelper.PageInfo;
import org.hae.yl.dto.DiscussionDetailDTO;
import org.hae.yl.entity.Announcement;
import org.hae.yl.entity.Comment;
import org.hae.yl.entity.Discussion;
import org.hae.yl.facade.IssuesFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/issues")
public class ApiUser_IssuesController {

    @Resource
    private IssuesFacade issuesFacade;

    /**
     * 查询所有讨论列表（分页）
     */
    @GetMapping("/getDiscussionsqueryByPage")
    public PageInfo<Discussion> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return issuesFacade.queryByPage(pageNum, pageSize);
    }



    /**
     * 创建一个新的议题（类似 GitHub Issue）
     */
    @PostMapping("/createIssues")
    public void createDiscussion(@RequestBody Discussion discussion){
        issuesFacade.createDiscussion(discussion);
    }

    /**
     * 根据 ID 给讨论添加评论
     */
    @GetMapping("/addcomment")
    public void addComment(@RequestBody Comment comment){
        issuesFacade.addComment(comment);
    }

    /**
     * 查询某个讨论的完整内容（包含评论列表）
     */
    @GetMapping("getDiscussionDetail")
    public DiscussionDetailDTO getDiscussionDetail(int discussionId){
        return issuesFacade.getDiscussionDetail(discussionId);
    }

    /**
     * 删除评论（需要判断用户是否为本人或管理员）
     */
    @PostMapping("/deletecomment")
    public void deleteComment(HttpServletRequest request, int id){
        issuesFacade.deleteComment(request,id);
    }

    /**
     * 查询对应ID议题中的所有评论
     */
    @GetMapping("/queryIssuesComment")
    public List<Comment> SelectIssuesComment(int id){
        return issuesFacade.SelectIssuesComment(id);
    }

    /**
     * 删除议题（需要判断用户是否为本人或管理员）
     */
    public void deleteDiscussion(HttpServletRequest request,int id){
        issuesFacade.deleteDiscussion(request,id);
    }
}
