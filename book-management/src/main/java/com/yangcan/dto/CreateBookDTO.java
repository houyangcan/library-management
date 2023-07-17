package com.yangcan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("图书新建参数")
public class CreateBookDTO {

    @ApiModelProperty("书籍名称")
    private String name;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("出版社")
    private String publish;

    @ApiModelProperty("国际标准书号")
    private String isbn;

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("语言")
    private String language;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("出版日期")
    private Date publishDate;

    @ApiModelProperty("分类id")
    private Integer classId;

    @ApiModelProperty("书籍状态")
    private Integer state;
}
