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
package com.vkdot.example.sync;

import com.vkdot.example.dto.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test of blocking response handler
 *
 * @author Vojtech Krizek
 */
public class BlockingResponseHandlerTest {

	private BlockingResponseHandler handler;

	@Before
	public void before() {
		handler = new BlockingResponseHandler();
	}

	@Test
	public void testQuickHandle() throws InterruptedException {
		handler.handle(new Response("Quick"));
		Response response = handler.waitForResponse();
		assertNotNull(response);
		assertEquals("Quick", response.getMessage());
	}

	@Test
	public void testSlowHandle() throws Exception {
		new Thread(() -> {
			try {
				Thread.sleep(100);
				handler.handle(new Response("Slow"));
			} catch (InterruptedException e) {
				System.err.println("Failed to handle a response");
			}
		}).start();
		Response response = handler.waitForResponse();
		assertNotNull(response);
		assertEquals("Slow", response.getMessage());
	}

}
