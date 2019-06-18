package com.bytehonor.sdk.server.spring.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.exception.InternalRestfulException;
import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.web.error.stragety.DefaultExceptionStragety;
import com.bytehonor.sdk.server.spring.web.error.stragety.InternalRestfulExceptionStragety;
import com.bytehonor.sdk.server.spring.web.error.stragety.NullPointerExceptionStragety;
import com.bytehonor.sdk.server.spring.web.error.stragety.ServerDefinedExceptionStragety;

public class ExceptionStragetyFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionStragetyFactory.class);

	public static final ExceptionStragety build(Exception e) {

		if (e instanceof SpringServerException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("ServerDefinedException process");
			}
			return new ServerDefinedExceptionStragety((SpringServerException) e);
		}

		if (e instanceof InternalRestfulException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("InternalRestfulException process");
			}
			return new InternalRestfulExceptionStragety((InternalRestfulException) e);
		}

		if (e instanceof NullPointerException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("NullPointerException process");
			}
			return new NullPointerExceptionStragety((NullPointerException) e);
		}

		return new DefaultExceptionStragety(e);
	}

}
