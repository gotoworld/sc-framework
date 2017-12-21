package com.hsd.framework.cache;

import com.hsd.framework.IdGenerator;
import com.hsd.framework.cache.util.IdGeneratorIsRedis;

public class ExampleTest {
    public static void main(String[] args) {
        String tab = "order";
        long userId = 123456789;

        IdGenerator idGenerator = IdGeneratorIsRedis.builder()
                .addHost("192.168.108.100",7000,"ff66a5da2d567ebe92816537571266667d0f8059")
                .addHost("192.168.108.100",7001,"14d8ae98a2d4a6854b442043764f513ae9d5dc13")
                .addHost("192.168.108.100",7002,"39de9cae7dd22046410bdbe3efd8a69be18e6b1d")
                .addHost("192.168.108.100",7003,"93a829aaff0c56360b49f6fbf5dd7027a3515337")
                .addHost("192.168.108.100",7004,"627387ff2ad992fd26c7f9ad0932fdde63ee5d78")
                .addHost("192.168.108.100",7005,"d15b38d109c8f8e1aa5c39183d973725a450ccae")
                .build();

        long id = idGenerator.nextId();
    }
}
