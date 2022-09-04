package com.test.coindesk;

import com.test.coindesk.coin.Coin;
import com.test.coindesk.coin.CoinController;
import com.test.coindesk.coin.CoinService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoinController.class)
public class GetCoinsTest {

    private Coin coin = new Coin("USD", "美金", 34.22, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinService service;

    @Test
    public void getCoin() throws Exception {
        String url = "/coin";
        MvcResult result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(coin.toString())).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}
