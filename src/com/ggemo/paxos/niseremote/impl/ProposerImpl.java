package com.ggemo.paxos.niseremote.impl;

import com.ggemo.paxos.niseremote.Acceptor;
import com.ggemo.paxos.niseremote.Propose;
import com.ggemo.paxos.niseremote.ProposeRes;
import com.ggemo.paxos.niseremote.Proposer;
import com.ggemo.paxos.niseremote.dto.AcceptDTO;
import com.ggemo.paxos.niseremote.dto.AcceptedDTO;
import com.ggemo.paxos.niseremote.dto.PrepareDTO;
import com.ggemo.paxos.niseremote.dto.PromiseDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 11:39
 * Email: 2894700792@qq.com
 */
public class ProposerImpl implements Proposer {
    Propose propose;

    @Override
    public boolean prepare(Set<Acceptor> acceptors) {
        PrepareDTO prepareDTO = new PrepareDTO(this.propose.getRnd());
        int len = acceptors.size();
        int quorumLeast = len / 2;
        int accepted = 0;

        for (Acceptor acceptor : acceptors) {
            PromiseDTO promiseDTO = acceptor.promise(prepareDTO);
            switch (promiseDTO.getProposeRes()){
                case REJCT:
                    // 有一个reject就返回
                    return false;
                case ACCEPT:
                    accepted += 1;
                    break;
                case TIMEOUT:
                    // ignore
                    break;
            }
        }
        return accepted > quorumLeast;
    }

    public boolean accept(Set<Acceptor> acceptors) {
        AcceptDTO acceptDTO = new AcceptDTO(this.propose.getRnd(), this.propose.getValue());
        int len = acceptors.size();
        int quorumLeast = len / 2;
        int accepted = 0;

        for (Acceptor acceptor : acceptors) {
            AcceptedDTO acceptedDTO = acceptor.accepted(acceptDTO);
            switch (acceptedDTO.getProposeRes()){
                case REJCT:
                    return false;
                case ACCEPT:
                    accepted += 1;
                    break;
                case TIMEOUT:
                    // ignore
                    break;
            }
        }
        return accepted > quorumLeast;
    }

    @Override
    public boolean propose(Set<Acceptor> acceptors) {
        boolean prepareRes = this.prepare(acceptors);
        if (!prepareRes) {
            // prepare失败, 本次propose结束
            return false;
        }
        boolean acceptRes = this.accept(acceptors);
        if(!acceptRes){
            return false;
        }
        // 向学习者发送消息
        return true;

    }
}
