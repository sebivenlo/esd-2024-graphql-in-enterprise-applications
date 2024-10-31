package com.example.graphqlworkshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(properties = {
    "spring.data.cassandra.contact-points=cassandra",
    "spring.data.cassandra.port=9042"
})
class GraphqlWorkshopApplicationTests {

    @Test
    void contextLoads() {
    }

}
