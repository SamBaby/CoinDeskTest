package com.test.coindesk.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/coin")
    public List<Coin> getAllCoin() {
        return coinService.getCoins();
    }

    @GetMapping("/coin/{id}")
    public Coin getCoin(@PathVariable("id") int id) {
        return coinService.getCoinByCode(id);
    }

    @DeleteMapping("/coin/{id}")
    public void deleteCoin(@PathVariable("id") int id){
        coinService.delete(id);
    }

    @PostMapping("/coin")
    public void saveCoin(@RequestBody Coin coin){
        coin.setUpdated(Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        coinService.saveOrUpdate(coin);
    }

    @PutMapping("/coin")
    public void update(@RequestBody Coin coin){
        coin.setUpdated(Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        coinService.saveOrUpdate(coin);
    }
}
