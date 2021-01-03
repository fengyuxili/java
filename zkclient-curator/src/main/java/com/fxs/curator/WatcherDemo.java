package com.fxs.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

public class WatcherDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.31.220:2181").sessionTimeoutMs(5000)
                //.authorization("", "".getBytes())
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        addListenerNode(curatorFramework);
        System.in.read();
    }

    private static void addListenerNode(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/watch" );
        NodeCacheListener nodeCacheListener = ()->{
            System.out.println("receive node change");
            System.out.println(nodeCache.getPath()+"->"+new String(nodeCache.getCurrentData().getData(), "UTF-8"));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
