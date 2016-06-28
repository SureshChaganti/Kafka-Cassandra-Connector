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

	public static void pumpData(Long company_id, HashMap<String, String> hMap) {

		// queries
		String query1 = "INSERT INTO company_impressions  (  company_id,ipd_campaign,access_point,time_UUID,"
				+ "session_id,yyyymmdd,impression_ts,  impression_properties,TTL)  VALUES ("
				+ company_id
				+ ",'payroll_campaign','qbo_right_nav',NOW(), '99989983232asaswqwq',"
				+ "20160622201,201605052001,  { 'property4'  : 'value4',   'property3'  : 'value3'}, 'TTL');";

		String query2 = "INSERT INTO company_recommendation_dismiss  (  company_id,ipd_campaign,  access_point,"
				+ "time_UUID,URL,impression_ts, TTL)  VALUES ("
				+ company_id
				+ ",'payroll_campaign','qbo_right_nav',NOW(), "
				+ "'www.qbo.intuit.com/access ',201605052201,  'TTL'  );";

		// Creating Cluster object
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				.build();

		// Creating Session object
		Session session = cluster.connect("fingerprints_feedback");

		// Executing the query
		session.execute(query1);

		session.execute(query2);

		session.close();
		cluster.close();

		System.out.println("Data created-->>"+company_id);

	}

}
