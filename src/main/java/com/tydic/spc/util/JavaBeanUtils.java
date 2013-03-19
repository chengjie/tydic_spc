package com.tydic.spc.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class JavaBeanUtils {
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getJavaBeanFiledMap(Object clazz)
	{
		try {
			return BeanUtils.describe(clazz);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
