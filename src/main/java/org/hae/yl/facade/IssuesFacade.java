package org.hae.yl.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.dto.CommentDTO;
import org.hae.yl.dto.DiscussionDetailDTO;
import org.hae.yl.entity.Comment;
import org.hae.yl.entity.Discussion;
import org.hae.yl.entity.Nursing_home;
import org.hae.yl.entity.User;
import org.hae.yl.service.CommentService;
import org.hae.yl.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.RequestContextFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IssuesFacade {

    @Resource
    private CommentService conmentService;

    @Resource
    private DiscussionService discussionService;

    @Resource
    private UserFacade userFacade;


    /**
     * 创建一个新的议题（类似 GitHub Issue）
     */
    public void createDiscussion(@RequestBody Discussion Issues) {

        Discussion Dn = new Discussion();

        Dn.setTitle(Issues.getTitle());
        Dn.setContent(Issues.getContent());
        Dn.setCreator_id(Issues.getCreator_id());
        discussionService.Insert(Dn);
    }

    /**
     * 给某个讨论添加评论
     */
    public void addComment(@RequestBody Comment comment) {

        Comment Ct = new Comment();
        Ct.setDiscussion_id(comment.getDiscussion_id());
        Ct.setUser_id(comment.getUser_id());
        Ct.setContent(comment.getContent());
        conmentService.Insert(Ct);
    }

    /**
     * 查询某个讨论的完整内容（包含评论列表）
     */
    public DiscussionDetailDTO getDiscussionDetail(int discussionId) {
        Discussion discussion = discussionService.SelectById(discussionId); //获取 议题信息
        List<Comment> commentList = SelectIssuesComment(discussionId);      //获取 议题中的全部评论信息

        // 组装 DTO
        DiscussionDetailDTO dto = new DiscussionDetailDTO();
        dto.setTitle(discussion.getTitle());            //议题 标题
        dto.setContent(discussion.getContent());        //议题 内容
        dto.setCreatedBy(discussion.getCreator_id());   //议题 创建者
        dto.setCreatedAt(discussion.getCreated_at());   //议题 创建时间


        System.out.println(discussion.toString());

        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> {
            CommentDTO c = new CommentDTO();
            c.setContent(comment.getContent());         //评论内容
            c.setCreatedBy(comment.getUser_id());       //评论创建者
            c.setCreatedAt(comment.getCreated_at());    //评论创建时间
            return c;
        }).collect(Collectors.toList());
        dto.setComments(commentDTOList);
        return dto;
    }

    /**
     * 删除评论（需要判断用户是否为本人或管理员）
     */
    public void deleteComment(HttpServletRequest request,int id) {
        User user = userFacade.getUserInfo(request);        //获取当前用户信息
        Comment comment = conmentService.SelectById(id);    //获取当前评论信息

        System.out.println("C :" + comment.toString());
        //System.out.println(comment.getUser_id() +"  :  "+ user.getId());


        if (comment.getUser_id() == user.getId()) {//评论发布人ID 和 当前请求人ID是否一致
            conmentService.DeleteById(id);
        }
        if (user.getRole_id() == 3 || user.getRole_id() == 2) {//是否是管理员
            // TODO: 角色判断是否是管理员
            conmentService.DeleteById(id);
        }
        throw new RuntimeException("无权限删除");
    }

    /**
     * 查询对应ID议题中的所有评论
     */
    public List<Comment> SelectIssuesComment(int id){
        return conmentService.SelectByTheme(id);
    }

    /**
     * 查询对应ID议题中的所有评论的ID 集合
     */
    public List<Integer> SelectIssuesCommentId(int id){
        return conmentService.SelectByThemeReturnIds(id);
    }

    /**
     * 删除议题（需要判断用户是否为本人或管理员）
     */
    public void deleteDiscussion(HttpServletRequest request,int id) {
        User user = userFacade.getUserInfo(request);                 //获取当前用户信息
        Discussion discussion = discussionService.SelectById(id);    //获取当前议题信息


        boolean isOwner = discussion.getCreator_id().equals(user.getId());
        boolean isAdmin = (user.getRole_id() == 2 || user.getRole_id() == 3); // 假设2/3是管理员角色

        if (isOwner || isAdmin) {
            // 删除评论
            conmentService.DeleteByIdbatch(SelectIssuesCommentId(discussion.getId()));
            // 删除议题
            discussionService.DeleteById(discussion.getId());
            return;
        }


//        if(discussion.getCreator_id() == user.getId()){
//            // 先删评论（避免外键约束错误）
//            conmentService.DeleteByIdbatch(
//                    SelectIssuesCommentId(discussion.getCreator_id())
//            );
//            // 再删讨论
//            discussionService.DeleteById(discussion.getCreator_id());
//            return;
//        }
//        if (user.getRole_id() == 3 || user.getRole_id() == 2) {//是否是管理员
//            // TODO: 角色判断是否是管理员
//            // 先删评论（避免外键约束错误）
//            conmentService.DeleteByIdbatch(
//                    SelectIssuesCommentId(discussion.getCreator_id())
//            );
//            // 再删讨论
//            discussionService.DeleteById(discussion.getCreator_id());
//            return;
//        }
        throw new RuntimeException("无权限删除");

    }

    /**
     * 查询所有讨论列表（分页）
     */
    public PageInfo<Discussion> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Discussion> list = discussionService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

}
