package com.chaos.forum.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * <p>
 * { 文章列表 }
 * </p>
 *
 * @Author kay
 * 2019-09-22 21:31
 */
@Data
public class ArticleListPage implements Serializable {

    @ApiModelProperty(value = " 文章分页的页码 ")
    private Integer page = 1;

    @ApiModelProperty(value = " 文章分页的条数 ")
    private Integer pageSize = 5;

    @ApiModelProperty(value = " 文章排序的字段 ")
    private String sortField = "createTime";

    @ApiModelProperty(value = " 文章排序的方式asd/desc ")
    private String sortOrder = "desc";

    @ApiModelProperty(value = " 文章标题 ")
    private String title;

    @ApiModelProperty(value = " 文章ID ")
    private Integer articleId;

    @ApiModelProperty(value = " 文章分类标题 ")
    private String name;

    @ApiModelProperty(value = " 文章分类ID ")
    private Integer articleCategoryId;

}
