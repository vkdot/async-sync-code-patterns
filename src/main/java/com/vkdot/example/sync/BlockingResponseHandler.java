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
import com.vkdot.example.systems.ResponseHandler;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Response handler that waits until a response is received
 *
 * @author Vojtech Krizek
 */
public class BlockingResponseHandler implements ResponseHandler {

	private final AtomicReference<Response> receivedResponse = new AtomicReference<>();
	private final Object responseLock = new Object();

	@Override
	public void handle(Response resp) {
		// check if no response has beed received yet
		if (receivedResponse.compareAndSet(null, resp)) {
			synchronized (responseLock) {
				responseLock.notifyAll();
			}
		} else {
			throw new IllegalStateException("Response was already set.");
		}
	}

	/**
	 * Blocking wait for a response message
	 *
	 * @return A response message
	 */
	public Response waitForResponse() {
		// loop until we receive a response
		while (receivedResponse.get() == null) {
			synchronized (responseLock) {
				try {
					responseLock.wait();
				} catch (InterruptedException e) {
					throw new IllegalStateException("Failed to wait for a response", e);
				}
			}
		}
		return receivedResponse.get();
	}

}
