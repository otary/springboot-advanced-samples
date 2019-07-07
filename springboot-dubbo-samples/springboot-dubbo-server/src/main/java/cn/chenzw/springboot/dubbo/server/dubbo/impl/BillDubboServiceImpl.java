package cn.chenzw.springboot.dubbo.server.dubbo.impl;

import cn.chenzw.springboot.dubbo.api.domain.dto.BillDto;
import cn.chenzw.springboot.dubbo.api.service.BillService;
import com.alibaba.dubbo.config.annotation.Service;

import java.math.BigDecimal;

@Service(version = "1.0.0")
public class BillDubboServiceImpl implements BillService {

    @Override
    public BillDto getByIdCard(String idCard) {
        BillDto billDto = new BillDto();
        billDto.setId(1L);
        billDto.setName("zhangsan");
        billDto.setIdCard(idCard);
        billDto.setOweMoney(new BigDecimal(10.34));
        return billDto;
    }
}
