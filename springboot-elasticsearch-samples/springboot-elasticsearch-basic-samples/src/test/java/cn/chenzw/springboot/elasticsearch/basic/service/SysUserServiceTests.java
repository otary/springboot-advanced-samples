package cn.chenzw.springboot.elasticsearch.basic.service;

import cn.chenzw.springboot.elasticsearch.basic.domain.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTests {


    @Autowired
    SysUserService sysUserService;


    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        sysUserService.createIndex();
    }

    /**
     * 删除索引
     *
     * @throws IOException
     */
    @Test
    public void testDeleteIndex() throws IOException {
        sysUserService.deleteIndex();
    }


    @Test
    public void testInsertByString() throws IOException {
        sysUserService.insertByString();
    }

    @Test
    public void testInsertByMap() throws IOException {
        sysUserService.insertMap();
    }

    @Test
    public void testInsertByXContent() throws IOException {
        sysUserService.insertXContent();
    }

    @Test
    public void testInsertXContent() throws IOException {
        sysUserService.insertXContent();
    }

    @Test
    public void testInsertByObject() throws IOException {
        sysUserService.insertByObject();
    }

    @Test
    public void testMatchAllQuery() throws IOException {
        sysUserService.matchAllQuery();
    }

    @Test
    public void testMatchQuery() throws IOException {
        sysUserService.matchQuery();
    }

    @Test
    public void testCommonTermsQuery() throws IOException {
        sysUserService.commonTermsQuery();
    }

    @Test
    public void testMultiMatchQuery() throws IOException {
        sysUserService.multiMatchQuery();
    }

    @Test
    public void testDisMaxQuery() throws IOException {
        sysUserService.disMaxQuery();
    }

    @Test
    public void testIdQuery() throws IOException {
        sysUserService.idQuery();
    }

    @Test
    public void testTermQuery() throws IOException {
        sysUserService.termQuery();
    }

    @Test
    public void testFuzzyQuery() throws IOException {
        sysUserService.fuzzyQuery();
    }

    @Test
    public void testPrefixQuery() throws IOException {
        sysUserService.prefixQuery();
    }

    @Test
    public void testRangeQuery() throws IOException {
        sysUserService.rangeQuery();
    }

    @Test
    public void testWildcardQuery() throws IOException {
        sysUserService.wildcardQuery();
    }

    @Test
    public void testRegexpQuery() throws IOException {
        sysUserService.regexpQuery();
    }

    @Test
    public void testQueryStringQuery() throws IOException {
        sysUserService.queryStringQuery();
    }
}
