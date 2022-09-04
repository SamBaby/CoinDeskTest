package com.test.coindesk;

import com.test.coindesk.coin.Coin;
import com.test.coindesk.coin.CoinController;
import com.test.coindesk.coin.CoinService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(CoinController.class)
public class CoinDeleteTest {
    private Coin coin = new Coin("USD", "美金", 34.22, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinService service;

    @BeforeEach
    private void prepare() {
        service.saveOrUpdate(coin);
    }

    @Test
    public void deleteCoin() throws Exception {
        MvcResult result = mockMvc.perform(delete("/coin/{id}", 1)).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

}
