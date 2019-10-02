package com.chaos.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chaos.forum.entity.AdminUser;
import com.chaos.forum.exception.DataException;
import com.chaos.forum.mapper.AdminUserMapper;
import com.chaos.forum.returnx.enumx.ResultEnum;
import com.chaos.forum.service.IAdminUserService;
import com.chaos.forum.tools.DatabaseTools;
import com.chaos.forum.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


/**
 * <p>
 * { 管理员用户服务层 }
 * </p>
 *
 * @Author kay
 * 2019-09-20 10:38
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 管理员用户登陆
     *
     * @param adminUser    用户对象
     * @param session 用户情况（登陆情况）
     */
    @Override
    public ResultVO logIn(AdminUser adminUser, HttpSession session) {
        AdminUser adminUserOne = this.getOne(new QueryWrapper<AdminUser>().eq("name", adminUser.getName()));
        this.logger.info("in logIn");
        /** 判断数据库记录
         *      抛出异常：LI_GIN_NULL（用户未注册）
         * */
        if (adminUserOne == null) {
            this.logger.info("throw DataException LI_GIN_NULL");
            throw new DataException(ResultEnum.LI_GIN_NULL);
        }

        /** 判断用户名密码是否一致
         *      抛出异常：LI_GIN_ERROR（用户名或密码不正确）
         * */
        if (!adminUserOne.getPassword().equals(adminUser.getPassword())) {
            this.logger.info("throw DataException LI_GIN_ERROR");
            throw new DataException(ResultEnum.LI_GIN_ERROR);
        }


        /** 保存用户登陆信息 */
        session.setAttribute("adminUser", adminUserOne);
        this.logger.info("login SUCCESS");
        return new ResultVO(ResultEnum.SUCCESS);
    }

}
