package com.solace.events.asyncapi.spring.cloud.streams.generator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "scs")
@Data
public class SpringCloudStreamsGeneratorProperties {
	static final String SINK = "SINK";
	static final String SOURCE = "SOURCE";
	static final String PROCESSOR = "PROCESSOR";

	@NotBlank
	private String packageName;

	@NotBlank
	private String baseDir;

	@NotBlank
	private String scsType;

	@NotBlank
	private String asyncAPIfile;

}
