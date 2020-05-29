package com.ggemo.paxos.niseremote;

import com.ggemo.paxos.niseremote.dto.PrepareDTO;
import com.ggemo.paxos.niseremote.dto.PromiseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 5:50
 * Email: 2894700792@qq.com
 */
@Data
@AllArgsConstructor
public class Propose {
    int rnd;
    Object value;
}
