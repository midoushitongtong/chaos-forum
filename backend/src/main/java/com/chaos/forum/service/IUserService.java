package com.chaos.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chaos.forum.entity.PersonUser;
import com.chaos.forum.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 * { describe }
 * </p>
 *
 * @Author kay
 * 2019-10-02 14:09
 */
public interface IUserService extends IService<PersonUser> {
    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    ResultVO signIn(PersonUser user);

    /**
     * 用户登陆接口
     *
     * @param user 用户实体
     * */
    ResultVO logIn(PersonUser user, HttpSession session);

    /**
     * 用户修改信息(登陆密码)
     *
     * */
    ResultVO alter(HttpSession session, PersonUser user);

    ResultVO getUserName(HttpSession session);
}
