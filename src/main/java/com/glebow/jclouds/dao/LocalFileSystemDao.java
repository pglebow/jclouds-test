/**
 * 
 */
package com.glebow.jclouds.dao;

import java.io.IOException;

import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.domain.PageSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.io.ByteSource;

/**
 * @author pglebow
 *
 */
@Repository
public class LocalFileSystemDao {

	@Autowired
	private BlobStoreContext context;
	
	/**
	 * Default
	 */
	public LocalFileSystemDao() {		
	}
	
	/**
	 * Saves the blob in the container
	 * @param container
	 * @param blob
	 * @return
	 * @throws IOException 
	 */
	public String save(String container, String name, ByteSource payload) throws IOException {
		BlobStore store = context.getBlobStore();
		
		store.createContainerInLocation(null, container);
		
		Blob blob = store.blobBuilder(name).payload(payload).contentLength(payload.size()).build();
		
		String eTag = store.putBlob(container, blob);
		
		return eTag;
	}
	
	/**
	 * Lists the contents of the container
	 * @param container
	 * @return
	 */
	public PageSet<?> listContents(String container) {
		PageSet<?> retVal = null;
		
		BlobStore store = context.getBlobStore();
		retVal = store.list(container);
		
		return retVal;
	}

}
