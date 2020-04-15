package edu.yctc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author luna
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GenesisApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(GenesisApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            "  ___ _   _  ___ ___ ___  ___ ___    \n" +
            " / __| | | |/ __/ __/ _ \\/ __/ __|    \n" +
            " \\__ \\ |_| | (_| (_|  __/\\__ \\__ \\  \n" +
            " |___/\\__,_|\\___\\___\\___||___/___/ \n");
    }
}