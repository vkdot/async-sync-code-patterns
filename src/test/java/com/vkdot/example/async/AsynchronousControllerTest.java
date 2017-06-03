/*
 * Copyright 2017 Vojtech Krizek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vkdot.example.async;

import static org.junit.Assert.*;

import com.vkdot.example.dto.Request;
import com.vkdot.example.dto.Response;
import com.vkdot.example.system.ExampleSynchronousSystem;
import com.vkdot.example.systems.ResponseHandler;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of asynchronous controller
 *
 * @author Vojtech Krizek
 */
public class AsynchronousControllerTest {

	private AsynchronousController controller;

	@Before
	public void before() {
		controller = new AsynchronousController(new ExampleSynchronousSystem());
	}

	@Test
	public void testProcess() throws InterruptedException {
		AtomicReference<Response> responseReference = new AtomicReference<>();

		controller.process(new Request("Hello"), new ResponseHandler() {
			@Override
			public void handle(Response resp) {
				responseReference.set(resp);
			}
		});

		// process method is not blocking, so we do not have a response yet
		assertNull(responseReference.get());

		// example system has a response time 100 ms, so let's wait for 150 ms
		Thread.sleep(150);

		// now we should have a response
		Response response = responseReference.get();
		assertNotNull(response);
		assertEquals("Hello World!", response.getMessage());
	}

}
