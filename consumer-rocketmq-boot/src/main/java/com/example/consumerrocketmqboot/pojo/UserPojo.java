package com.example.consumerrocketmqboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer userId;
    private String userName;
}
