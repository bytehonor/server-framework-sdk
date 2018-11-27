package com.bytehonor.standard.sping.server.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * 属性拷贝工具
 * 
 * @author lijianqiang
 *
 */
public class BeanCopierUtils {

	private static final Logger LOG = LoggerFactory.getLogger(BeanCopierUtils.class);

	private static Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<String, BeanCopier>();

	private static final String CONN = "-";

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	private static BeanCopier build(Object from, Object to) {

		String sourceName = from.getClass().getSimpleName();
		String targetName = to.getClass().getSimpleName();
		String beanKey = generateKey(sourceName, targetName);
		LOG.debug("sourceName:{}, targetName:{}, beanKey:{}", sourceName, targetName, beanKey);

		BeanCopier copier = BEAN_COPIER_MAP.get(beanKey);
		if (copier == null) {
			copier = BeanCopier.create(from.getClass(), to.getClass(), false);
			BEAN_COPIER_MAP.put(beanKey, copier);
			LOG.info("BEAN_COPIER_MAP put beanKey:{}", beanKey);
		}
		return copier;
	}

	/**
	 * @param from
	 * @param to
	 */
	public static void copy(Object from, Object to) {
		copy(from, to, null);
	}

	/**
	 * @param from
	 * @param to
	 * @param converter
	 */
	public static void copy(Object from, Object to, Converter converter) {
		BeanCopier copier = build(from, to);
		try {
			copier.copy(from, to, converter);
		} catch (Exception e) {
			BEAN_COPIER_MAP = new HashMap<String, BeanCopier>();
		}
	}

	private static String generateKey(String sourceClazz, String targetClazz) {
		StringBuilder sb = new StringBuilder();
		sb.append(sourceClazz).append(CONN).append(targetClazz);
		return sb.toString();
	}
}
