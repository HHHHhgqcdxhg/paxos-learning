package com.ggemo.paxos.niseremote.impl;

import com.ggemo.paxos.niseremote.Acceptor;
import com.ggemo.paxos.niseremote.ProposeRes;
import com.ggemo.paxos.niseremote.dto.AcceptDTO;
import com.ggemo.paxos.niseremote.dto.AcceptedDTO;
import com.ggemo.paxos.niseremote.dto.PrepareDTO;
import com.ggemo.paxos.niseremote.dto.PromiseDTO;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 21:33
 * Email: 2894700792@qq.com
 */
public class AcceptorImpl implements Acceptor {
    private int lastRnd;
    private int vRnd;
    private Object value;

    @Override
    public PromiseDTO promise(PrepareDTO prepareDTO) {
        // TODO: 模拟timeout
        if(prepareDTO.getRnd() > this.lastRnd){
            PromiseDTO res = new PromiseDTO(ProposeRes.ACCEPT, this.lastRnd, this.vRnd, this.value);
            this.lastRnd = prepareDTO.getRnd();
            return res;
        }else{
            return new PromiseDTO(ProposeRes.REJCT, this.lastRnd, 0, null);
        }
    }

    @Override
    public AcceptedDTO accepted(AcceptDTO acceptDTO) {
        // TODO: 模拟timeout
        if(this.lastRnd == acceptDTO.getRnd()){
            this.vRnd = this.lastRnd;
            this.value = acceptDTO.getValue();
            return new AcceptedDTO(ProposeRes.ACCEPT);
        }else{
            return new AcceptedDTO(ProposeRes.REJCT);
        }
    }

    public AcceptorImpl() {
        this.lastRnd = 0;
        this.vRnd = 0;
        this.value = null;
    }
}
