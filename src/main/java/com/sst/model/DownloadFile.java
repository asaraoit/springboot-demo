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
public class DownloadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 下载文件的名称
     */
    private String filename;

    /**
     * 版本
     */
    private String version;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 服务器中文件的名称
     */
    private String filenameServer;

    /**
     * 下载次数
     */
    private Long downloadCount;

    /**
     * 运行环境
     */
    private String runtimeEnv;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件分类ID
     */
    private Long categoryFile;

    /**
     * 是否启用: 0-禁用;1-启用
     */
    private Boolean status;


}
