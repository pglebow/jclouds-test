/**
 * 
 */
package com.glebow.jclouds.config;

import java.util.Properties;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.filesystem.reference.FilesystemConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pglebow
 *
 */
@Configuration
public class JCloudsConfigClass {

	@Bean(name = "fileSystemContext")
	public BlobStoreContext getFileSystemContext() {
		
		Properties properties = new Properties();
		properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, "/tmp");
		
		return ContextBuilder.newBuilder("filesystem").overrides(properties).buildView(BlobStoreContext.class);
	}

}
