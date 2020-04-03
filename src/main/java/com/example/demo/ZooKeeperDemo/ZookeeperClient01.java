package com.example.demo.ZooKeeperDemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author Vic Zhang
 * @date 2019/3/24 4:18 PM
 */
public class ZookeeperClient01 {

    public static void main(String[] args) throws  Exception{
        String conn = "0.0.0.0:2181,0.0.0.0:2182,0.0.0.0:2183";
        /**
         * params02  第二个参数为sessionTimeOut
         * params03  第三个为watch
         */
        ZooKeeper zk = new ZooKeeper(conn, 5000, null);

        Thread.sleep(3000);

        zk.create("/vic", "vic".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Thread.sleep(5000);

        zk.close();
    }
}
