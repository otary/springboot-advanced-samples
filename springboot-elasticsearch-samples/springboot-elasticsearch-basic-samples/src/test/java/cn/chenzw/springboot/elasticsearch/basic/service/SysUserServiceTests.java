package cn.chenzw.springboot.elasticsearch.basic.service;

import cn.chenzw.springboot.elasticsearch.basic.ElasticsearchBasicSamplesApp;
import cn.chenzw.springboot.elasticsearch.basic.domain.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ElasticsearchBasicSamplesApp.class)
@WebAppConfiguration
public class SysUserServiceTests {


    @Autowired
    SysUserService sysUserService;

    @Test
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setId(Long.parseLong(String.valueOf(i)));
            sysUser.setName("张三");
            sysUser.setUsername("zhangsan");

            sysUserService.save(sysUser);
        }
    }

    @Test
    public void testSearch(){
        List<SysUser> sysUsers = sysUserService.search(0, 10, "zhangsan");

        Assert.assertNotNull(sysUsers);
    }

}
