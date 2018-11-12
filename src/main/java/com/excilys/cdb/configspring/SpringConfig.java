package com.excilys.cdb.configspring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ServiceConfig.class,DBDemo.class})

public class SpringConfig {


}

