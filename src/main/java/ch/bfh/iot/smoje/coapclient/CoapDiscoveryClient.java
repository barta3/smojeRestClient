package ch.bfh.iot.smoje.coapclient;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.network.config.NetworkConfig;

/**
 * Coap Sample
 *
 */
public class CoapDiscoveryClient {
	public static void main(String[] args) throws Exception {
		NetworkConfig.createStandardWithoutFile();
		Request req = new Request(Code.GET);
		req.setURI("coap://224.00.1.0:5683/.well-known/core");
		req.setMulticast(true);
		req.send();
		Response resp;
		resp = req.waitForResponse();
		System.out.println("" + resp.getPayloadString());
	}

}
