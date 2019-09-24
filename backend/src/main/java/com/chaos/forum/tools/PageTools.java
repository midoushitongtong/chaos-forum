package com.chaos.forum.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chaos.forum.tools.lambda.PageMapperLambda;

/**
 * { mybatis-plus 分页查询，模糊查询，排序等工具类}
 *
 * @param Page<T>/QueryWrapper<T> 接收两个对象
 *
 * @Author kay
 * @Author hiems
 * 2019-09-23 16:29
 */
public class PageTools<T> {

    private Page<T> page;

    private QueryWrapper<T> wrapper = new QueryWrapper();


    /**
     * 分页
     *
     *
     * @param page 页码
     * @param pageSize 条数
     * @return IPage
     * */
    public PageTools(int page, int pageSize) {
        this.page = new Page<>(page, pageSize);
    }


    /**
     *模糊查询
     *
     *
     * @param column 键-需要查询的字段
     * @param value 值-需要查询的数据
     * @return IPage
     * */
    public PageTools<T> like(String column, String value) {
        this.wrapper.like(column, value);
        return this;
    }

    /**
     * 排序
     *
     *
     * @param sortField 排序的目标
     * @param sortType 排序的值-desc/asd
     * @return IPage
     * */
    public PageTools<T> sort(String sortField, SortType sortType) {
        if (sortType.equals(SortType.ASD)) {
            this.page.setAsc(sortField);
        } else {
            this.page.setDesc(sortField);
        }
        return this;
    }

    /**
     * 返回
     *
     * @param pageMapperLambda mapper 转换函数
     *
     * @return QueryWrapper<T> wrapper 实体对象封装操作类
     * @return Page<T> page 翻页对象
     * */
    public IPage result(PageMapperLambda<T> pageMapperLambda) {
        return pageMapperLambda.select(this.page, this.wrapper);
    }
}