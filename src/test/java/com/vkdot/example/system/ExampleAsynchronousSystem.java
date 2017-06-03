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
package com.vkdot.example.system;

import com.vkdot.example.dto.Request;
import com.vkdot.example.dto.Response;
import com.vkdot.example.systems.AsynchronousSystem;
import com.vkdot.example.systems.ResponseHandler;

/**
 * Example of asynchronous system
 *
 * @author Vojtech Krizek
 */
public class ExampleAsynchronousSystem implements AsynchronousSystem {

	@Override
	public void makeCall(Request req, ResponseHandler respHandler) {
		if (req == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}

		// emulates an asynchronous call in a separate thread
		new Thread(() -> {
			try {
				// emulates real call, wait for 100 ms
				Thread.sleep(100);
				respHandler.handle(new Response(req.getMessage() + " World!"));
			} catch (InterruptedException e) {
				System.err.println("Failed to produce a response");
			}
		}).start();
	}

}
