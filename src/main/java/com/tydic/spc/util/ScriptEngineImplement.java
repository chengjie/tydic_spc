package com.tydic.spc.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author exiong
 *         <p>
 *         <b>JDK 6 new features. Using script engine(java-script engine to
 *         resolve the json object easier.)
 */
@SuppressWarnings("restriction")
public class ScriptEngineImplement {

	private static final String JSON_FORMAT_STRING = "json = %s ; json = json.%s";

	private static Object result;
	private static String formatString;

	private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	private static ScriptEngine scriptEngine;

	private static ScriptEngine getScriptEngine() {
		scriptEngine = scriptEngineManager.getEngineByName("js");
		return scriptEngine;
	}

	/**
	 * @param jsonStr
	 * @param jsonKey
	 *            script engine request json's key e.g.: json.a.b[0].c Detail
	 *            information please refer to the java-script json engine.
	 * @return The text value of the specified jsonKey.
	 */
	public static String fetchContentFromJavaScriptJsonString(String jsonStr,String jsonKey) 
	{
		beforeEval(jsonStr, jsonKey);
		try {
			result = scriptEngine.eval(formatString);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * @param jsonStr
	 * @param jsonKey
	 *            script engine request json's key e.g.: json.a.b[0].c Detail
	 *            information please refer to the java-script json engine.
	 * @return The array result which for java script string []
	 */
//	public static Object[] fetchContentFromJavaScriptJsonArray(String jsonStr,String jsonKey) 
//	{
//		beforeEval(jsonStr, jsonKey);
//		try {
//			result = scriptEngine.eval(formatString);
//			NativeArray arr = (NativeArray) result;
//			Object[] array = new Object[(int) arr.getLength()];
//			for (Object o : arr.getIds()) {
//				int index = (Integer) o;
//				array[index] = arr.get(index, null);
//			}
//			return array;
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	
	private static void beforeEval(String jsonStr, String jsonKey) {
		formatString = String.format(JSON_FORMAT_STRING, jsonStr, jsonKey);
		if (scriptEngine == null)
			scriptEngine = getScriptEngine();
	}
}
