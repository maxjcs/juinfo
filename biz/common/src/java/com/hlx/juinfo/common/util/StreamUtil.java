package com.hlx.juinfo.common.util;

import java.io.Closeable;
import java.io.IOException;

//import com.alibaba.common.logging.Logger;
//import com.alibaba.common.logging.LoggerFactory;

public final class StreamUtil {
//	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * �ر�Ŀ��.
	 * @param stream �ɹرյ�Ŀ��.
	 * @return �Ƿ�رճɹ�.
	 */
	public static boolean closeCloseable(Closeable a) {
		if (a == null) {
			return true;
		}
		try {
			a.close();
			return true;
		} catch (IOException e) {
			//LOGGER.error("Close Closeable error", e);
		}
		return false;
	}
}
