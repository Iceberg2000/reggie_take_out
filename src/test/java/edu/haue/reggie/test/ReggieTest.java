package edu.haue.reggie.test;

import org.junit.jupiter.api.Test;
import java.util.UUID;

public class ReggieTest {

    @Test
    public void test1() {
        String originalFilename = "asdas.jpg";
//        String filename = UUID.randomUUID().toString();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//        filename += substring;
        System.out.println(substring);
    }

}
