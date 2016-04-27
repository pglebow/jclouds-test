/**
 * 
 */
package com.glebow.jclouds.config;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;
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
		return ContextBuilder.newBuilder("fileSystem").buildView(BlobStoreContext.class);
	}

}
