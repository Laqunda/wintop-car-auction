package com.wintop.ms.carauction.starter;

import com.wintop.ms.carauction.service.ICarRegionSettingService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private ICarRegionSettingService regionSettingService;

    @org.junit.Test
    public void testGetBreachTime(){
        Date date = regionSettingService.getBreachTime(new Date(),1l,"2");
        System.out.println(DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));
    }

}
