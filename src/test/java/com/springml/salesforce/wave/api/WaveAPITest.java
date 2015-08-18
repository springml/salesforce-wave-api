package com.springml.salesforce.wave.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.springml.salesforce.wave.model.QueryResult;

public class WaveAPITest {
	private WaveAPI waveAPI = null;
	
	@Before
	public void setup() throws Exception {
		waveAPI = APIFactory.getInstance().waveAPI("samspark@palmtreeinfotech.com", 
				"Fire2015!uRu7NN7L99uIiRZr9VCngTCg", "https://login.salesforce.com");
	}
	
	@Test
	public void queryTest() throws Exception {
		String saql = "q = load \"0FbB000000007qmKAA/0FcB00000000LgTKAU\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;";
		QueryResult result = waveAPI.query(saql);
		System.out.println(result.getResults().getRecords().get(0).get("count"));
		System.out.println("result :  " + result);
		assertEquals(100, result.getResults().getRecords().size());
		assertEquals("11", result.getResults().getRecords().get(0).get("count"));
	}
}
