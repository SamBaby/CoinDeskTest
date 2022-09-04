package com.test.coindesk.coin;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class CoinConfig {

    static String COIN_DESK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    static HashMap<String, String> enToCn = new HashMap<>();

    @Bean
    CommandLineRunner commandLineRunner(CoinRepository repository) {
        return args -> {
            List<Coin> coins = transferCoin(loadBitcoinPrice());

            repository.saveAll(coins);
        };
    }

    public static String loadBitcoinPrice() {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(COIN_DESK_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(0);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return builder.toString();
    }

    public static List<Coin> transferCoin(String text){
        try{
            List<Coin> coins = new ArrayList<>();
            setMap();
            JSONParser jsonParser = new JSONParser(loadBitcoinPrice());
            LinkedHashMap<String, Object> jsonMap = jsonParser.parseObject();
            //get updated time
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            //get coins
            LinkedHashMap<String, Object> coinMap = (LinkedHashMap<String, Object>) jsonMap.get("bpi");
            for (String key : coinMap.keySet()) {
                LinkedHashMap<String, Object> coinDetail = (LinkedHashMap<String, Object>) coinMap.get(key);
                coins.add(new Coin(key, enToCn.get(key), ((BigDecimal) coinDetail.get("rate_float")).doubleValue(), Timestamp.valueOf(time)));
            }

            return coins;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void setMap() {
        enToCn.clear();
        enToCn.put("USD", "美金");
        enToCn.put("GBP", "英鎊");
        enToCn.put("EUR", "歐元");
    }

}
