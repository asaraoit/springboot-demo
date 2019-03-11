package com.sst.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.ApplyTryout;

@Mapper
public interface ApplyTryoutMapper extends BaseMapper<ApplyTryout> {

	int addApplyTryout(ApplyTryout applyTryout);
}
