package com.backend.previsaodotempo.Config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class AppConfigTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testRestTemplateBean() {
		assertNotNull(restTemplate);
	}
}
