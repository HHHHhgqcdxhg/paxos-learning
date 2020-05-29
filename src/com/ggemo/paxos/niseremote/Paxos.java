package com.ggemo.paxos.niseremote;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.concurrent.Executors;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 9:51
 * Email: 2894700792@qq.com
 */
@AllArgsConstructor
@NoArgsConstructor
public class Paxos {
    protected Set<Proposer> proposers;
    protected Set<Acceptor> acceptors;
    protected Set<Leaner> leaners;

    public void propose() {
        var service = Executors.newFixedThreadPool(proposers.size());

        for (Proposer proposer : proposers) {
            service.submit(() -> {
                proposer.propose(acceptors);
            });
        }

    }
}
