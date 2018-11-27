package com.bytehonor.standard.sping.server.web.error.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.standard.sping.server.web.error.exception.InternalRestfulException;
import com.bytehonor.standard.sping.server.web.error.exception.ServerDefinedException;
import com.bytehonor.standard.sping.server.web.error.stragety.DefaultExceptionStragety;
import com.bytehonor.standard.sping.server.web.error.stragety.ExceptionStragety;
import com.bytehonor.standard.sping.server.web.error.stragety.InternalRestfulExceptionStragety;
import com.bytehonor.standard.sping.server.web.error.stragety.NullPointerExceptionStragety;
import com.bytehonor.standard.sping.server.web.error.stragety.ServerDefinedExceptionStragety;

public class ExceptionStragetyFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionStragetyFactory.class);

	public static final ExceptionStragety build(Exception e) {

		if (e instanceof ServerDefinedException) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("ServerDefinedException process");
			}
			return new ServerDefinedExceptionStragety((ServerDefinedException) e);
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
