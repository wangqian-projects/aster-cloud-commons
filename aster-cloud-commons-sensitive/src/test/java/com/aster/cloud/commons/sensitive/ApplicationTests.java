package com.aster.cloud.commons.sensitive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试类
 *
 * @author 王骞
 * @date 2021-01-15
 */
@SpringBootTest
public class ApplicationTests {

    /**
     * jackson脱敏测试
     * @throws JsonProcessingException
     */
    @Test
    void testSensitive() throws JsonProcessingException {
        UserVO user = new UserVO();
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(user);
        System.out.println(str);
    }

}
