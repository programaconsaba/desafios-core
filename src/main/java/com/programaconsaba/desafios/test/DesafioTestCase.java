/**
 *  desafios-core - source code for the https://programaconsaba.com/desafios application
 *  Copyright (C) 2016 José Antonio Sabalete
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.programaconsaba.desafios.test;

import java.util.Arrays;

public class DesafioTestCase {
	private String[] input;
	private String[] output;

	public String getInputForTest() {
		StringBuffer buff = new StringBuffer();
		for (String aux : input) {

			if (buff.length() > 0) {
				buff.append(" ");
			}

			buff.append(aux);
		}

		return buff.toString();
	}

	public String[] getInput() {
		return input;
	}

	public void setInput(String[] input) {
		this.input = input;
	}

	public String[] getOutput() {
		return output;
	}

	public void setOutput(String[] output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "TestCase [input=" + Arrays.toString(input) + ", output=" + Arrays.toString(output) + "]";
	}

}
