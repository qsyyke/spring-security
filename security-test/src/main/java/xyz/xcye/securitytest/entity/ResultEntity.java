package xyz.xcye.securitytest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 11:32
 */
@Data
@ToString
@Builder
public class ResultEntity<T> {

    private int code;

    private String token;

    private T data;
}
