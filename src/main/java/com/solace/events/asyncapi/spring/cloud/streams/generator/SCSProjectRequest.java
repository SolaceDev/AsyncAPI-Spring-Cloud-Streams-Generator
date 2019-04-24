package com.solace.events.asyncapi.spring.cloud.streams.generator;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.spring.initializr.generator.ProjectRequest;

@Configuration
@ConfigurationProperties(prefix = "spring.initializr")
@Data
public class SCSProjectRequest {
	private List<String> style = new ArrayList<>();

	private List<String> dependencies = new ArrayList<>();

	private String name;

	private String type;

	private String description;

	private String groupId;

	private String artifactId;

	private String version;

	private String bootVersion;

	private String packaging;

	private String applicationName;

	private String language;

	private String packageName;

	private String javaVersion;

	// The base directory to create in the archive - no baseDir by default
	private String baseDir;

	public String getPackageName() {
		if (StringUtils.hasText(this.packageName)) {
			return this.packageName;
		}
		if (StringUtils.hasText(this.groupId) && StringUtils.hasText(this.artifactId)) {
			return getGroupId() + "." + getArtifactId();
		}
		return null;
	}

	public ProjectRequest getProjectRequest() {
		ProjectRequest pr = new ProjectRequest();
		pr.setApplicationName(this.applicationName);
		pr.setArtifactId(this.artifactId);
		pr.setBaseDir(this.baseDir);
		pr.setBootVersion(this.bootVersion);
		pr.setDependencies(this.dependencies);
		pr.setDescription(this.description);
		pr.setGroupId(this.groupId);
		pr.setJavaVersion(this.javaVersion);
		pr.setLanguage(this.language);
		pr.setName(this.name);
		pr.setPackageName(this.packageName);
		pr.setPackaging(this.packaging);
		pr.setType(this.type);
		pr.setStyle(this.style);
		pr.setVersion(this.version);
		return pr;
	}

}
