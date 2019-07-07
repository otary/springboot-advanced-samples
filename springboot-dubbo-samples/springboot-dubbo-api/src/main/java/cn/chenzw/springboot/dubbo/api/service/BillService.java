package cn.chenzw.springboot.dubbo.api.service;


import cn.chenzw.springboot.dubbo.api.domain.dto.BillDto;

public interface BillService {

    BillDto getByIdCard(String idCard);
}
