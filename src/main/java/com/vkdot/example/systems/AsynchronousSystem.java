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
package com.vkdot.example.systems;

import com.vkdot.example.dto.Request;

/**
 * Asynchronous system interface
 *
 * @author Vojtech Krizek
 */
public interface AsynchronousSystem {

	/**
	 * Make asynchronous non-blocking system call.
	 *
	 * @param req Request object
	 * @param respHandler Response handler (callback)
	 */
	void makeCall(Request req, ResponseHandler respHandler);
}
