package com.sst.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author IAskWind
 * @since 2018-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FunctionCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 卡片名称
     */
    private String name;

    /**
     * 卡片文本
     */
    private String text;

    /**
     * 卡片图标
     */
    private String icon;

    /**
     * 是否启用:0-禁用;1-启用
     */
    private Boolean status;


}
