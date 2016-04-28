/**
 * 
 */
package com.glebow.jclouds.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import org.jclouds.blobstore.domain.PageSet;
import org.jclouds.openstack.swift.v1.SwiftApi;
import org.jclouds.openstack.swift.v1.domain.Container;
import org.jclouds.openstack.swift.v1.features.ContainerApi;
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

	@Autowired
	private SwiftApi swiftApi;

	/**
	 * Test method for
	 * {@link com.glebow.jclouds.service.LocalFileSystemService#writeData(byte[], java.lang.String, java.lang.String)}
	 * .
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

	@Test
	public void testListContents() {
		String container = this.getClass().getName();

		PageSet<?> s = service.getBlobs(container);
		Assert.assertNotNull(s);
		s.forEach(p -> log.info(p.toString()));
	}

	@Test
	public void testSwift() throws IOException {
		try {
			ContainerApi containerApi = swiftApi.getContainerApi("RegionOne");
			Set<Container> containers = containerApi.list().toSet();

			for (Container container : containers) {
				System.out.println("  " + container);
			}
			swiftApi.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
	}

}
