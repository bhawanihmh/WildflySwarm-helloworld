package com.swarm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author bhawani.singh
 *
 */
public class HelloUtil {
	
	public boolean generateTimeout() {
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			boolean isEven = false;
			Random rand = new Random();
			isEven = (rand.nextInt(100) % 2) == 0 ? true : false ;
			System.out.println(" isEven = " + isEven);
			
			if(isEven) {
				try {
					System.out.println("\n\n@@@@@@@@@@@@   Before Sleep currentTimeMillis = " + System.currentTimeMillis());
					Thread.sleep(5000);					
					System.out.println("\n\n@@@@@@@@@@@@   After Sleep currentTimeMillis = " + System.currentTimeMillis());
					System.out.println("\n\n@@@@@@@@@@@@   Set status gateway timeout     @@@@@@@@@@@@\n");
					return true;
				} catch (InterruptedException e) {
					e.printStackTrace();
					return true;
				}	
			}			

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
