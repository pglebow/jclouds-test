/**
 * 
 */
package com.glebow.jclouds.service;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pglebow
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LocalFileSystemServiceTest {

	@Autowired
	private LocalFileSystemService service;
	
	/**
	 * Test method for {@link com.glebow.jclouds.service.LocalFileSystemService#writeData(byte[], java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testWriteData() {
		String testData = "This is test data";
		String name = "unitTestData";
		String container = this.getClass().getName();
		
		String eTag;
		try {
			eTag = service.writeData(testData.getBytes(Charset.defaultCharset()), container, name);
			log.info(eTag);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
	}

}
