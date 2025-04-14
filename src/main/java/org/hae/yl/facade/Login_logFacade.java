package org.hae.yl.facade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Login_log;
import org.hae.yl.entity.Message_board;
import org.hae.yl.service.Login_logService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Login_logFacade {

    @Resource
    private Login_logService login_logService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Login_log> queryByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  // 启动分页
        List<Login_log> list = login_logService.SelectAll();  // 原始查询
        return new PageInfo<>(list);  // 包装分页对象返回
    }

    /**
     * 查询所有
     */
    public List<Login_log> SelectAll(){
        return login_logService.SelectAll();
    }

    /**
     * 根据Id查询
     */
    public Login_log SelectById(int id){
        return login_logService.SelectById(id);
    }

    /**
     *  根据 Id 修改
     */
    public void Update(int id, Login_log nursing_home){
        login_logService.Update(id, nursing_home);
    }

    /**
     *  根据 Id 删除
     *  根据 Id 批量删除
     */
    public void DeleteById(int id){
        login_logService.DeleteById(id);
    }

    public void DeleteByIdbatch(List<Integer> ids){
        login_logService.DeleteByIdbatch(ids);
    }

    /**
     *  增加
     */
    public void Insert(Login_log login_log){
        login_logService.Insert(login_log);
    }
}
