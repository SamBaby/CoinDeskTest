package com.test.coindesk;

import com.test.coindesk.coin.Coin;
import com.test.coindesk.coin.CoinConfig;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinDeskTransferTest {
    @Test
    public void getCoin() throws Exception {
        String coinDesk = CoinConfig.loadBitcoinPrice();
        List<Coin> coins = CoinConfig.transferCoin(coinDesk);
        System.out.println(coins);
        assertThat(coins).isNotNull();
    }
}
