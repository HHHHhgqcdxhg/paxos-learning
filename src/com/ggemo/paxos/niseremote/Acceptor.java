package com.ggemo.paxos.niseremote;

import com.ggemo.paxos.niseremote.dto.AcceptDTO;
import com.ggemo.paxos.niseremote.dto.AcceptedDTO;
import com.ggemo.paxos.niseremote.dto.PrepareDTO;
import com.ggemo.paxos.niseremote.dto.PromiseDTO;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 5:50
 * Email: 2894700792@qq.com
 */
public interface Acceptor {
    PromiseDTO promise(PrepareDTO prepareDTO);

    AcceptedDTO accepted(AcceptDTO acceptDTO);
}
