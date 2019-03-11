package com.sst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.FunctionCard;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName FunctionCardMapper
 * @Description 功能卡服务层接口
 * @Author Asarao
 * @Date 2018/11/9 9:43
 * @Version 1.0
 */
@Mapper
public interface FunctionCardMapper extends BaseMapper<FunctionCard> {
}
