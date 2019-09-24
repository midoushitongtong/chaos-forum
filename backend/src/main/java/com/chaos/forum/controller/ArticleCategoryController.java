package com.chaos.forum.controller;

import com.chaos.forum.entity.ArticleCategory;
import com.chaos.forum.entity.ArticleListPage;
import com.chaos.forum.returnx.enumx.ResultEnum;
import com.chaos.forum.service.ArticleCategorySercvice;
import com.chaos.forum.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * { describe }
 * </p>
 *
 * @Author kay
 * 2019-09-23 14:48
 */
@RestController
@RequestMapping("/backend")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategorySercvice categorySercvice;

    @PostMapping("/articleCategory")
    public ResultVO createCategory(ArticleCategory articleCategoryName) {
        return categorySercvice.createCategory(articleCategoryName);
    }

    @DeleteMapping("/articleCategory/{id}")
    public ResultVO delectCategory(@PathVariable int id) {
        if (categorySercvice.removeById(id)) {
            return new ResultVO(ResultEnum.SUCCESS);
        }
        return new ResultVO(ResultEnum.DELETE_ERROR);
    }

    @PutMapping("/articleCategory/{id}")
    public ResultVO updateCategory(ArticleCategory articleCategoryName, @PathVariable int id) {
        articleCategoryName.setId(id);
        return categorySercvice.updateCategory(articleCategoryName);
    }

    @GetMapping("/articleCategory/{id}")
    public ResultVO selectOneCategory(@PathVariable int id) {
        if (categorySercvice.getById(id) != null) {
            return new ResultVO(ResultEnum.SUCCESS, categorySercvice.getById(id));
        }
        return new ResultVO(ResultEnum.LI_GIN_PAST);
    }

    /** 文章分类-分页*/
    @GetMapping("/articleCategory")
    public ResultVO selectCategory(ArticleListPage articleListPage) {
        return categorySercvice.selectCategory(articleListPage);
    }


    /** 返回所有文章 */
    @GetMapping("/articleCategoryAll")
    public ResultVO selectCategoryAll() {
        return this.categorySercvice.selectArticleAll();
    }

}
