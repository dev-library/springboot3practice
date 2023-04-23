package com.devrary.book.springboot3webservice.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloResponseDTOTest {

    @Test
    @DisplayName("롬복기능 테스트")
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
