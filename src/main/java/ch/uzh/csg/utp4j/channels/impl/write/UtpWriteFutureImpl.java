/* Copyright 2013 Ivan Iljkic
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License. You may obtain a copy of
* the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/
package ch.uzh.csg.utp4j.channels.impl.write;

import java.io.IOException;

import ch.uzh.csg.utp4j.channels.futures.UtpWriteFuture;
/**
 * Hides impl. details
 * @author Ivan Iljkic (i.iljkic@gmail.com)
 *
 */
public class UtpWriteFutureImpl extends UtpWriteFuture {
		
	
	public UtpWriteFutureImpl() throws InterruptedException {
		super();
	}

	public void finished(IOException exp, int bytesWritten) {
		this.setBytesSend(bytesWritten);
		this.exception = exp;
		isDone = true;
		semaphore.release();
	}

	public void setBytesSend(int position) {
		bytesWritten = position;
	}

}
