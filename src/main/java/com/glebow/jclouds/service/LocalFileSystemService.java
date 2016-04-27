/**
 * 
 */
package com.glebow.jclouds.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glebow.jclouds.dao.LocalFileSystemDao;
import com.google.common.io.ByteSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pglebow
 *
 */
@Service
@Slf4j
public class LocalFileSystemService {

	@Autowired
	private LocalFileSystemDao dao;

	/**
	 * Writes the data into the container
	 * @param data
	 * @param container
	 * @param name
	 * @return eTag
	 */
	public String writeData(final byte[] data, final String container, final String name) {
		String retVal = null;

		try {
			retVal = dao.save(container, name, ByteSource.wrap(data));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return retVal;
	}
}
