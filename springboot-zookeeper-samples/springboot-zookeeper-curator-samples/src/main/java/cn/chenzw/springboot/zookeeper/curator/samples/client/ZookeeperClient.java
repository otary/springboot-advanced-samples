package cn.chenzw.springboot.zookeeper.curator.samples.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZookeeperClient {

    @Autowired
    private CuratorFramework curatorFramework;

    public ZookeeperClient(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @param createMode
     */
    public void save(String path, String data, CreateMode createMode) {
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询节点信息
     *
     * @param path
     * @return
     */
    public String query(String path) {
        try {
            byte[] data = curatorFramework.getData().forPath(path);
            if (data != null && data.length > 0) {
                return new String(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发布消息
     *
     * @param path
     * @param data
     */
    public void publish(String path, String data) {
        try {
            Stat stat = this.curatorFramework.checkExists().forPath(path);

            if (stat == null) {
                save(path, data, CreateMode.PERSISTENT);
            } else {
                this.curatorFramework.setData().forPath(path, data.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅
     *
     * @param path
     */
    public void subscribe(String name, String path) {
        NodeCache nodeCache = new NodeCache(this.curatorFramework, path);
        nodeCache.getListenable().addListener(() -> {
            System.out.printf("%s监听到节点信息发生变化，当前数据=%s\n", name, new String(nodeCache.getCurrentData().getData()));
        });

        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
