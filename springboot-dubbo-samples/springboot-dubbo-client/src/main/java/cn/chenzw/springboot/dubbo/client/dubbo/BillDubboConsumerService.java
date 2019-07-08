package cn.chenzw.springboot.dubbo.client.dubbo;

import cn.chenzw.springboot.dubbo.api.domain.dto.BillDto;
import cn.chenzw.springboot.dubbo.api.service.BillService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class BillDubboConsumerService {

    @Reference(version = "1.0.0")
    BillService billService;


    public BillDto getByIdCard(String idCard) {
        return billService.getByIdCard(idCard);
    }
}
