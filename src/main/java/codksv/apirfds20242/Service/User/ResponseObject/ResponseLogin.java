package codksv.apirfds20242.Service.User.ResponseObject;

import codksv.apirfds20242.Service.Generic.ResponseGeneric;

public class ResponseLogin extends ResponseGeneric {
	public class Response {
		public Object user = null;
	}

	public Response dto = new Response();
}