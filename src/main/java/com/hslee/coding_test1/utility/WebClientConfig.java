package com.hslee.coding_test1.utility;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.responseTimeout(Duration.ofMillis(5000))
				.doOnConnected(conn ->
						conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        String targetUrl = "http://a.b.c";
        return WebClient.builder()
				.baseUrl(targetUrl)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.defaultHeader("Content-Type", "application/json;charset=utf-8")
				.build();
	}
}