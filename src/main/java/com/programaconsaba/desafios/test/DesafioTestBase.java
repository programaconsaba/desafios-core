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

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programaconsaba.desafios.util.JSONUtils;

public abstract class DesafioTestBase {

	private Log log = LogFactory.getLog(JSONUtils.class);

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private InputStream originalIn = System.in;
	private PrintStream originalOut = System.out;

	private DesafioTestCase testCase;

	@Before
	public void setUpStreams() {

		ObjectMapper mapper = new ObjectMapper();
		try {
			testCase = mapper.readValue(
					Thread.currentThread().getContextClassLoader().getResourceAsStream(getTestFileName()),
					DesafioTestCase.class);
			log.debug(testCase);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setOut(new PrintStream(outContent));

		System.setIn(new ByteArrayInputStream(testCase.getInputForTest().getBytes()));

	}

	@After
	public void cleanUpStreams() {
		System.setIn(originalIn);
		System.setOut(originalOut);

		System.out.println(outContent);

		StringBuffer outputBuff = new StringBuffer();
		for (String resultAux : testCase.getOutput()) {
			outputBuff.append(resultAux + System.lineSeparator());
		}

		assertEquals(outputBuff.toString(), outContent.toString());
	}

	public abstract String getTestFileName();

}
