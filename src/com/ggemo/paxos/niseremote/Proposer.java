package com.ggemo.paxos.niseremote;

import com.ggemo.paxos.niseremote.dto.PrepareDTO;
import com.ggemo.paxos.niseremote.dto.PromiseDTO;

import java.util.Set;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 5:49
 * Email: 2894700792@qq.com
 */
public interface Proposer {

    boolean prepare(Set<Acceptor> acceptors);

    boolean propose(Set<Acceptor> acceptors);
}
