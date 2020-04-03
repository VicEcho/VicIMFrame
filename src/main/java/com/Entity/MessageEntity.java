package com.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vic Zhang
 * @date 2020/2/25 4:07 PM
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageEntity {

    private String name;

    private String words;

}
