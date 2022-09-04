package com.test.coindesk.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<Coin>();
        coinRepository.findAll().forEach(coin -> coins.add(coin));
        return coins;
    }

    public Coin getCoinByCode(int id) {
        return coinRepository.findById(id).get();
    }

    public int delete(int id) {
        coinRepository.deleteById(id);
        return id;
    }

    public Coin saveOrUpdate(Coin coin) {
        coinRepository.save(coin);
        return coin;
    }

    public List<Coin> saveAll(List<Coin> coins) {
        coinRepository.saveAll(coins);
        return coins;
    }
}
