package com.sst.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class ProblemContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 常见问题---标题
     */
    private String title;

    /**
     * 常见问题---原因
     */
    private String reason;

    /**
     * 常见问题---解决方案
     */
    private String solution;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 左侧菜单id
     */
    @TableField("pmId")
    private Long pmId;

    /**
     * 是否启用: 0-禁用;1-启用
     */
    private Boolean status;


}
