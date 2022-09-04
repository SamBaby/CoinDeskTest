package com.test.coindesk.coin;

import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Integer> {
}
