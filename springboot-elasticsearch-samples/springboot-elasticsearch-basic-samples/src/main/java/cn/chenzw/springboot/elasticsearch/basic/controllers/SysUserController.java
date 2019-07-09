package cn.chenzw.springboot.elasticsearch.basic.controllers;

import cn.chenzw.springboot.elasticsearch.basic.domain.entity.SysUser;
import cn.chenzw.springboot.elasticsearch.basic.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;


    @GetMapping("/save")
    // @PostMapping("/save")
    public Long saveUser(SysUser sysUser) {
        return sysUserService.save(sysUser);
    }

    @GetMapping("/search")
    public List<SysUser> search(@RequestParam(defaultValue = "0") Integer offset,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                String searchContent) {
        return sysUserService.search(offset, pageSize, searchContent);
    }

}
