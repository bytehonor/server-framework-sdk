package com.bytehonor.standard.server.sping.web.error.message;

public final class NormalErrorMessage implements ErrorMessage {
	
	private String content;
	
	public NormalErrorMessage() {
		this.content = "unknown";
	}
	
	@Override
	public final String toString() {
		return this.content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
