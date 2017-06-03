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

import static org.junit.Assert.*;

import com.vkdot.example.dto.Request;
import com.vkdot.example.dto.Response;
import com.vkdot.example.system.ExampleAsynchronousSystem;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of synchronous controller
 *
 * @author Vojtech Krizek
 */
public class SynchronousControllerTest {

	private SynchronousController controller;

	@Before
	public void before() {
		controller = new SynchronousController(new ExampleAsynchronousSystem());
	}

	@Test
	public void testProcess() {
		Response response = controller.process(new Request("Hello"));
		assertNotNull(response);
		assertEquals("Hello World!", response.getMessage());
	}

}
