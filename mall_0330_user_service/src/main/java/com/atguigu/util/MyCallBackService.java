package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyCallBackService implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];

		String identifier = wsc.getIdentifier();
		String pwd = MyProperty.getMyProperty(identifier);

		String mdpwd = MD5Util.md5(pwd + MyDateUtil.getPasswordDate());

		wsc.setPassword(mdpwd);
	}

}
