package com.learn.share.learn;

import java.util.Calendar;
import java.util.HashMap;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraUpdater {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {

		int seconds = Calendar.getInstance().getTime().getSeconds()
				+ Calendar.getInstance().getTime().getMinutes()
				+ Calendar.getInstance().getTime().getHours();
		pumpData(new Long(seconds), new HashMap<String, String>());

		System.out.println(seconds);

//		if (args.length > 0) {
//
//			pumpData(new Long(seconds), new HashMap<String, String>());
//		} else {
//			pumpData(new Long(seconds), new HashMap<String, String>());
//		}

		
	}

	public static void pumpData(Long id, HashMap<String, String> hMap) {

	

		String query2 = "INSERT INTO Table1  (  id,ampaign, point,"
				+ "time_UUID,URL,n_ts, TTL)  VALUES ("
				+ id
				+ ",'gampaign','t_nav',NOW(), "
				+ "",  'TTL'  );";

		// Creating Cluster object
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				.build();

		// Creating Session object
		Session session = cluster.connect("ff"); // your KeySpace

		// Executing the query
	

		session.execute(query2);

		session.close();
		cluster.close();

	}

}
