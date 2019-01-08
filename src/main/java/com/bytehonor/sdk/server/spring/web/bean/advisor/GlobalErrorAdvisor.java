package com.bytehonor.sdk.server.spring.web.bean.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.server.spring.web.error.exception.entity.ExceptionEntity;
import com.bytehonor.sdk.server.spring.web.error.factory.ExceptionStragetyFactory;
import com.bytehonor.sdk.server.spring.web.error.stragety.ExceptionStragety;

@ControllerAdvice
public class GlobalErrorAdvisor {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorAdvisor.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public final ExceptionEntity defaultErrorHandler(Exception ex) {
		if (LOG.isWarnEnabled()) {
			// 错误栈太多无用信息, 只打4行
			StackTraceElement[] stackTrace = ex.getStackTrace();
			StringBuilder sb = new StringBuilder("\n [***Print ").append(ex.getClass().getSimpleName())
					.append(" Stack]");
			for (int i = 0; i < 4; i++) {
				if (stackTrace[i] == null) {
					continue;
				}
				sb.append("\n [***Stack (").append(i).append(")]:").append(stackTrace[i].toString());
			}
			sb.append("\n [***Print Exception Stack End] \n");
			LOG.warn(sb.toString());
		}
		ExceptionStragety exceptionStragety = ExceptionStragetyFactory.build(ex);
		ExceptionEntity error = exceptionStragety.process();
		return error;
	}

}
