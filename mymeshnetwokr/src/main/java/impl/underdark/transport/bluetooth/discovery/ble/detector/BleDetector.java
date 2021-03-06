/*
 * Copyright (c) 2016 Vladimir L. Shabanov <virlof@gmail.com>
 *
 * Licensed under the myMesh License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://myMesh.io/LICENSE.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package impl.myMesh.transport.bluetooth.discovery.ble.detector;

import android.bluetooth.BluetoothDevice;

public interface BleDetector
{
	interface Listener
	{
		void onScanStarted();
		void onScanStopped(boolean error);

		void onDeviceDetected(BluetoothDevice device, byte[] scanRecord);
	}

	void startScan();
	void stopScan();
} // BleDetector
