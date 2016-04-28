/**
 * 
 */
package com.glebow.jclouds.config;

import java.util.Properties;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.filesystem.reference.FilesystemConstants;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.swift.v1.SwiftApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

/**
 * @author pglebow
 *
 */
@Configuration
@PropertySource("classpath:/com/glebow/jclouds/config/openstack/openstack.properties")
public class JCloudsConfigClass {

	@Value("${openstack_host}")
	private String host;

	@Value("${openstack_tenant}")
	private String tenant;

	@Value("${openstack_username}")
	private String userName;

	@Value("${openstack_password}")
	private String password;

	@Bean(name = "fileSystemContext")
	public BlobStoreContext getFileSystemContext() {

		Properties properties = new Properties();
		properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, "/tmp");

		return ContextBuilder.newBuilder("filesystem").overrides(properties).buildView(BlobStoreContext.class);
	}

	@Bean(name = "openStackSwiftApi")
	public SwiftApi getSwiftApi() {
		Iterable<Module> modules = ImmutableSet.<Module>of(new SLF4JLoggingModule());
		SwiftApi swiftApi = ContextBuilder.newBuilder("openstack-swift").endpoint("https://" + host + ":5000/v2.0/")
				.credentials(tenant + ":" + userName, password).modules(modules).buildApi(SwiftApi.class);

		return swiftApi;
	}

}
