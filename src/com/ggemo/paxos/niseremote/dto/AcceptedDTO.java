package com.ggemo.paxos.niseremote.dto;

import com.ggemo.paxos.niseremote.ProposeRes;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: 清纯的小黄瓜
 * Date: 2020/5/29 21:48
 * Email: 2894700792@qq.com
 */
@Data
@AllArgsConstructor
public class AcceptedDTO {
    ProposeRes proposeRes;
}
