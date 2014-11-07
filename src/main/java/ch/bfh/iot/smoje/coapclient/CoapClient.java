package ch.bfh.iot.smoje.coapclient;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.network.config.NetworkConfig;

/**
 * Coap Sample
 *
 */
public class CoapClient {
	public static void main(String[] args) throws Exception {
		NetworkConfig.createStandardWithoutFile();
		Request req = new Request(Code.GET);
		req.setURI("coap://192.168.43.205:5683/raspicam");
		req.send();
		Response resp;
		resp = req.waitForResponse();
		System.out.println("" + resp.getPayloadString());

		OutputStream stream = new FileOutputStream("/home/heroku/temp/coap.jpg");
		stream.write(resp.getPayload());
		stream.close();
	}

}
