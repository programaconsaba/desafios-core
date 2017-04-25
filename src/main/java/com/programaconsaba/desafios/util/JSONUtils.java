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

package com.programaconsaba.desafios.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class JSONUtils {
	private static Log log = LogFactory.getLog(JSONUtils.class);

	/**
	 * Método que dado un POJO, devuelve su representación en JSON
	 * 
	 * @param obj
	 *            Objeto en formato POJO
	 * @return String con la representación json del objeto.
	 */
	public static String getJsonObject(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(obj);

		} catch (Exception e) {
			log.error("JSONUtils.getJsonObject [Error inesperado en el parser de jackson]", e);
		}

		return json;
	}

	/**
	 * Método que dado un POJO, devuelve los bytes de su representación en JSON
	 * 
	 * @param obj
	 *            Objeto en formato POJO
	 * @return bytes[] con la representación en bytes del json del objeto.
	 */
	public static byte[] getJsonObjectBytes(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonBytes = null;

		try {
			jsonBytes = mapper.writeValueAsBytes(obj);

		} catch (Exception e) {
			log.error("JSONUtils.getJsonObject [Error inesperado en el parser de jackson]", e);
		}

		return jsonBytes;
	}

	/**
	 * Método que dado un JSON devuelve el pojo
	 * 
	 * @param <T>
	 * @param json
	 *            String con la representación json del objeto.
	 * @param obj
	 *            Objeto en formato POJO
	 * @return el POJO relleno
	 */
	public static <T> Object setJsonObject(String json, Class<T> obj) {
		ObjectMapper mapper = new ObjectMapper();

		Object objMapper = null;
		try {
			objMapper = mapper.readValue(json, obj);
		} catch (Exception e) {
			log.error("JSONUtils.setJsonObject [Error inesperado en el parser de jackson]", e);
		}

		return objMapper;

	}

	/**
	 * Método que dado un grupo de JSON formato lista devuelve la lista el pojo
	 * correspondiente
	 * 
	 * @param <T>
	 * @param json
	 *            String con la representación json del objeto.
	 * @param obj
	 *            Objeto en formato POJO
	 * @return el POJO relleno
	 */
	public static <T> List<T> setJsonListObject(String json, TypeReference<List<T>> obj) {
		ObjectMapper mapper = new ObjectMapper();

		List<T> objMapper = null;
		try {

			objMapper = mapper.readValue(json, obj);
		} catch (Exception e) {
			log.error("JSONUtils.setJsonObject [Error inesperado en el parser de jackson]", e);
		}

		return objMapper;

	}

	/**
	 * Función que dado un bean y un json hace un merge de ambos
	 * 
	 * @param originObj
	 *            Bean (POJO) base sobre el cual se hace merge
	 * @param updaterJSON
	 *            datos de actualización
	 * @return
	 * @return Bean con el merge hecho
	 */
	public static Object merge(Object originObj, String updaterJSON) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			ObjectReader updater = mapper.readerForUpdating(originObj);
			originObj = updater.readValue(updaterJSON);
		} catch (Exception e) {
			log.error("JSONUtils.jsonMerge [Error inesperado en el parser de jackson]", e);
		}

		return originObj;

	}
}
