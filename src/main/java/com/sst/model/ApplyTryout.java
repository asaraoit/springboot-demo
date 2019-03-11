package com.sst.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class ApplyTryout implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 企业名称
     */
    private String estaName;

    /**
     * 申请企业纳税人识别号
     */
    private String estaNumber;

    /**
     * 申请人姓名
     */
    private String applyName;

    /**
     * 申请人手机号
     */
    private String applyPhone;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;


}
