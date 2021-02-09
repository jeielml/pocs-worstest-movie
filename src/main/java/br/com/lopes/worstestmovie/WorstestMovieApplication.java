package br.com.lopes.worstestmovie;

import br.com.lopes.worstestmovie.enterprise.OpenCsvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WorstestMovieApplication {

    @Autowired
    private OpenCsvLoader service;

    public static void main(String[] args) {
        SpringApplication.run(WorstestMovieApplication.class, args);

//        OpenCsvUtil.getInstance().loadDataIntoH2();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        service.loadDataIntoH2();
    }

}


