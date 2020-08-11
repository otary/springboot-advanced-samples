package cn.chenzw.springboot.elasticsearch.data.service;


import cn.chenzw.springboot.elasticsearch.data.domain.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTests {


    @Autowired
    SysUserService sysUserService;

    @Test
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            SysUser sysUser = new SysUser();
            //sysUser.setId(Long.parseLong(String.valueOf(i)));
            sysUser.setName("张三");
            sysUser.setUsername("zhangsan");
            sysUser.setState((byte) 1);
            sysUser.setPassword("123456");
            sysUser.setSalt("abc");

            sysUserService.save(sysUser);
        }
    }

    /**
     * 自定义查询字段
     */
    @Test
    public void testSearch() {
        List<SysUser> sysUsers = sysUserService.search(0, 10, "zhangsan");
        Assert.assertTrue(sysUsers.size() > 0);

        List<SysUser> sysUsers2 = sysUserService.search(0, 10, "zhang");
        Assert.assertTrue(sysUsers2.isEmpty());
    }

    @Test
    public void testFindByUsernameAndAndState() {
        List<SysUser> sysUsers = sysUserService.findByUsernameAndState("zhangsan", (byte) 1);
        Assert.assertTrue(sysUsers.size() > 0);

        List<SysUser> sysUsers2 = sysUserService.findByUsernameAndState("zhangsan", (byte) 0);
        Assert.assertTrue(sysUsers2.isEmpty());
    }

    @Test
    public void testFindByUsernameOrName() {
        List<SysUser> sysUsers = sysUserService.findByUsernameOrName("zhangsan", null);
        Assert.assertTrue(sysUsers.size() > 0);

        List<SysUser> sysUsers2 = sysUserService.findByUsernameOrName(null, "张三");
        Assert.assertTrue(sysUsers2.size() > 0);

    }

    @Test
    public void testFindByUsername() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SysUser> sysUserPage = sysUserService.findByUsername("zhangsan", pageable);
        Assert.assertTrue(sysUserPage.getContent().size() > 0);
        Assert.assertTrue(sysUserPage.getTotalElements() > 0);
    }

    @Test
    public void testFindByUsernameLike() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SysUser> sysUserPage = sysUserService.findByUsernameLike("zhang", pageable);

        Assert.assertTrue(sysUserPage.getContent().size() > 0);
        Assert.assertTrue(sysUserPage.getTotalElements() > 0);
    }

}
