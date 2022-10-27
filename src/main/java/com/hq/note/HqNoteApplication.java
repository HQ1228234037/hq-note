package com.hq.note;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目启动类
 *
 * @author HQ
 **/
@Slf4j
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.hq.note.mapper")
public class HqNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqNoteApplication.class, args);
        String hq = "\n\n\n" +
                "    ██     ██      ██████\n" +
                "   ░██    ░██    ░██   ░██\n" +
                "   ░██    ░██    ░██   ░██\n" +
                "   ░█████████    ░██   ░██\n" +
                "   ░██░░░░░██    ░██   ░██\n" +
                "   ░██    ░██    ░██   ░██\n" +
                "   ░██    ░██     ░██████░█\n" +
                "   ░░     ░░       ░░░░░░ ░█" +
                "\n\n\n";
        log.info(hq);
    }

}
