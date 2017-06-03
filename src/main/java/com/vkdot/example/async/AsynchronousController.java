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

import com.vkdot.example.dto.Request;
import com.vkdot.example.dto.Response;
import com.vkdot.example.systems.ResponseHandler;
import com.vkdot.example.systems.SynchronousSystem;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Asynchronous controller of a middleware system
 *
 * @author Vojtech Krizek
 */
public class AsynchronousController {

	private final SynchronousSystem system;
	private final ExecutorService executor = Executors.newCachedThreadPool();

	public AsynchronousController(SynchronousSystem system) {
		this.system = system;
	}

	/**
	 * Process method of the controller that behaves asynchronously
	 *
	 * @param req Request object
	 * @param respHandler Response handler
	 */
	public void process(Request req, ResponseHandler respHandler) {
		// execute task in the background
		executor.execute(() -> {

			// synchronous call
			Response resp = system.makeCall(req);

			// return a response asynchronously
			respHandler.handle(resp);
		});
	}

}
