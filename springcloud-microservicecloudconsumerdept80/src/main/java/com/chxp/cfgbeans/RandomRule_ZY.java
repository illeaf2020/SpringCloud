package com.chxp.cfgbeans;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 问题：依旧轮询策略，但是加上新需求，每个服务器要求被调用5次。即以前是每台机器一次，现在是每台机器5次。
 */
public class RandomRule_ZY extends AbstractLoadBalancerRule {

//    /**
//     * Randomly choose from all living servers
//     */
//    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE")

    // 当前 Server 被调用的次数(要求每个 Server 被调用5次)
    private int total = 0;
    // 当前提供服务的 Server 号
    private int currentIndex = 0;
    // 当 total 等于 5 时，total 重置为0，currentIndex 变更为下一个 Server 号

    /**
     * public Server choose(ILoadBalancer lb, Object key){ ... }：返回具体服务的服务器
     * ILoadBalancer：负载均衡算法
     * Server：响应的微服务
     */
    public Server choose(ILoadBalancer lb, Object key) {

        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            /**
             * lb.getAllServers()：获取所有的 Server 集合
             * lb.getReachableServers()：获取可用的 Server 集合
             */
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

//            int index = chooseRandomInt(serverCount);
//            server = upList.get(index);

//            if (total < 5) {
//                total++;
//                server = upList.get(currentIndex);
//            }
//            else {
//                if (currentIndex >= (upList.size() - 1)) {
//                    currentIndex = 0;
//                }
//                else {
//                    currentIndex++;
//                    total = 0;
//                }
//                server = upList.get(currentIndex);
//                total++;
//            }

            if(total < 5)
            {
                server = upList.get(currentIndex);
                total++;
            }else {
                total = 0;
                currentIndex++;
                if(currentIndex >= upList.size())
                {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

//    protected int chooseRandomInt(int serverCount) {
//        return ThreadLocalRandom.current().nextInt(serverCount);
//    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}