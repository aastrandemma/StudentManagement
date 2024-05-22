package com.github.aastrandemma;

import com.github.aastrandemma.config.ComponentScanConfig;
import com.github.aastrandemma.data_access.StudentDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
    }
}