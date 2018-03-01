package com.test;

import com.parse.ppt.poi.service.encrypt.impl.EncryptServiceImpl;
import org.junit.Test;

public class Temp {
    @Test
    public void test() {
        EncryptServiceImpl demo1 = new EncryptServiceImpl();
        String pri = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAioPDAXnRkJOUbeCnwFvmkvqtdvPqU23EuNYatdJWcV8aCOOj6O/ck+74qft1BgPfYqjbV6i2354uInqXYyR1lwIDAQABAj903reqhx5K3jpeey8PHnttiBTUSPltazCQxBXFU7Ob81X7IJOnYn5HqP39+oQ5r67+GdESPKfgT9qAqvBI8sECIQD90VNTUSbuSpETaez4Z2Lamyt70HndUYa5e3/mEeJVewIhAIu0pPy0RmLVCWzGBGcLbPp+Q4Zvam7BF8amXIpDK4+VAiEAsczx9vpxMBoSGKxqMPSTfhmW02wOAih7HPHTfYJnr4MCIEJAVWl9eE+QJZsOwLG5KmIUFKOhHIEPUt4isInk1riNAiBriq0myHm4DN5onoZ7SZK4usf3Tmr48efJ9eOCqz97iA==";
        String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIqDwwF50ZCTlG3gp8Bb5pL6rXbz6lNtxLjWGrXSVnFfGgjjo+jv3JPu+Kn7dQYD32Ko21eott+eLiJ6l2MkdZcCAwEAAQ==";
        String d = "W5dUTPuGh1jzJzNAMLK9nk9bLfs7cA9VlZIEOLNKUfL6qphbxDyknB8u7N4X9FEyHu/yMOdDkDn6AdZB4MkM";
        String a = demo1.contentDecrypter(pub, pri, d);
        System.out.println(a);
    }

    @Test
    public void test1() {
        EncryptServiceImpl demo1 = new EncryptServiceImpl();
        String pri = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAjlPXzW2O8Vj5BpScM4eu/LJ7dWPUaRhe7MATdKX7wgZjzzfC3K+j2sBB/5gUzl0fy3+/pJAhm0xSjhXWCqKA2QIDAQABAkB/BFYn7+lMfCGpfMC1wBYrUEGbrt7md6hnUrdKN8MpEF1nwG6jVM9BLo7cPXb9Css9ykUdwwL1aTx2M8H4IGwBAiEA6Ka12i/BAGSq7yt85d8vW58RoMW+KUIiA5vSZtpN8VkCIQCcnIfH+uTiR9T42/4AJ5YvCnQNSIZyC7MVLR2777ObgQIhAN4zQyJVU69Ndlihn1NpOzD1lv+HfVyodhYeP/DN9X1ZAiBLbWhH/KJ4CTidkvbGhEpk5Zf3PdLi4pehv1TuyweqAQIhAMof1bwsbREHwFNnCARjqVj7HXXlZsDrzbhxEDmehsoL";
        String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI5T181tjvFY+QaUnDOHrvyye3Vj1GkYXuzAE3Sl+8IGY883wtyvo9rAQf+YFM5dH8t/v6SQIZtMUo4V1gqigNkCAwEAAQ==";
        String d = "RDaTBTp2IT00AhJjbXimVjXyfnkj8b1jzEzPdCDtE+vh2TcAzTVPKGqOYHrA55piVY/da43PjSnC3LBuXGs/LQ==";
        String a = demo1.contentDecrypter(pub, pri, d);
        System.out.println(a);
    }
}