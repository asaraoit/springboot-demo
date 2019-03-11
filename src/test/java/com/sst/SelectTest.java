package com.sst;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.model.FunctionCard;
import com.sst.service.FunctionCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SelectTest
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/12 18:27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTest {
    @Autowired
    private FunctionCardService functionCardService;

    @Test
    public void test1(){
        //functionCardService.getCondition();
        IPage<FunctionCard> all = functionCardService.getAll(1L, 10L);
        System.out.println(JSONObject.toJSONString(all));
    }
}
