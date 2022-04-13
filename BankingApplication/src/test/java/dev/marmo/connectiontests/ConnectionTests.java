package dev.marmo.connectiontests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import dev.marmo.utilities.ConnectionUtil;

import java.sql.Connection;

public class ConnectionTests {

    @Test
    void can_connect(){
        Connection conn = ConnectionUtil.createConnection();
        System.out.println(conn);
        Assertions.assertNotNull(conn);

    }


}
