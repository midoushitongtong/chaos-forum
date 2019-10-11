package com.chaos.forum.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chaos.forum.entity.ArticleComment;
import com.chaos.forum.entity.ArticleListPage;
import com.chaos.forum.entity.PersonUser;
import com.chaos.forum.exception.DataException;
import com.chaos.forum.mapper.ArticleCommentMapper;
import com.chaos.forum.returnx.enumx.ResultEnum;
import com.chaos.forum.service.IArticleCommentService;
import com.chaos.forum.tools.PageTools;
import com.chaos.forum.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * { describe }
 * </p>
 *
 * @Author kay
 * 2019-09-21 13:41
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    /**
     * 创建文章评论
     *
     * @param session
     * @param articleComment
     * @return
     */
    @Override
    public ResultVO SaveComment(HttpSession session, ArticleComment articleComment) {
        PersonUser userIn = (PersonUser) session.getAttribute("personUser");
        if (userIn != null) {
            articleComment.setUserId(userIn.getId());
            /**
             * 传入评论内容，文章ID，被回复人ID可以为null（null的话 就是评论的第一条）
             */
            if (this.save(articleComment)){
                return new ResultVO(ResultEnum.SUCCESS);
            }
        }
        throw new DataException(ResultEnum.LI_GIN_NOT);
    }

    /**
     * 获取文章评论
     *
     * @param id 文章ID
     * @param articleListPage
     * @return
     */
    @Override
    public ResultVO getComment(int id, ArticleListPage articleListPage) {

        PageTools<ArticleComment> pageTools = new PageTools<>(articleListPage);
        IPage<ArticleComment> iPage = pageTools.autoPaging()
                .result((page, wrapper, articleListPage1)
                        -> this.articleCommentMapper.selectComment(page, wrapper, articleListPage1,id));

        if (iPage.getTotal() == 0) {
            throw new DataException(ResultEnum.SELECT_ERROR);
        }
        return new ResultVO(ResultEnum.SUCCESS, iPage);
    }

    /**
     * 删除评论
     *
     * @param session
     * @return
     */
    @Override
    public ResultVO delectComment(HttpSession session) {
        PersonUser userIn = (PersonUser) session.getAttribute("personUser");
        if (userIn == null) {
            throw new DataException(ResultEnum.LI_GIN_NOT);
        }
        if (this.list(new QueryWrapper<ArticleComment>().eq("user_id", userIn.getId())) == null){
            return new ResultVO(ResultEnum.DELETE_ERROR);
        }
        if (this.remove(new QueryWrapper<ArticleComment>().eq("user_id", userIn.getId()))) {
            return new ResultVO(ResultEnum.SUCCESS);
        }
        throw new DataException(ResultEnum.DELETE_ERROR);
    }
}