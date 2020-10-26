package cn.chenzw.springboot.zookeeper.curator.samples;

import cn.chenzw.springboot.zookeeper.curator.samples.client.ZookeeperClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperClientTests {


    @Autowired
    private ZookeeperClient zookeeperClient;

    @Test
    public void testSave() {
        String path = "/School/Students";

        // 保存
        zookeeperClient.save(path, "For All Students", CreateMode.PERSISTENT);

        // 查询
        String data = zookeeperClient.query(path);

        Assert.assertEquals("For All Students", data);
    }

    /**
     * 测试发布与订阅
     */
    @Test
    public void testPublishSubcribe() throws InterruptedException {
        String path = "/NodeCache/PubSub";

        // 发布
        zookeeperClient.publish(path, "hello");

        // 订阅
        zookeeperClient.subscribe("sub", path);

        // 订阅2
        zookeeperClient.subscribe("sub2", path);

        Thread.sleep(300);

        // 再次发布
        zookeeperClient.publish(path, "hello, zhangsan");
    }
}
