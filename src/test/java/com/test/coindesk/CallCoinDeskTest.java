package com.test.coindesk;

import com.test.coindesk.coin.CoinConfig;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class CallCoinDeskTest {
    @Test
    void loadBitcoinPrice() {
        String coinDesk = CoinConfig.loadBitcoinPrice();
        System.out.println(coinDesk);
        assertThat(coinDesk).isNotNull();
    }
}
