package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyCallBackClient implements CallbackHandler {
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
		wsc.setIdentifier("cxf");

		String mdpwd = MD5Util.md5("wss4j" + MyDateUtil.getPasswordDate());
		System.err.println(mdpwd);
		wsc.setPassword(mdpwd);
	}
}
