package com.pi.swing.task;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class FadeImage {
	public static void main(String[] args) {
	    try {
	        Enumeration nis = NetworkInterface.getNetworkInterfaces();
	        while(nis.hasMoreElements())
	        {
	            NetworkInterface ni = (NetworkInterface) nis.nextElement();
	            Enumeration ias = ni.getInetAddresses();
	            while (ias.hasMoreElements())
	            {
	                InetAddress ia = (InetAddress) ias.nextElement();
	                System.out.println(ia.getHostAddress()+" :: " + ia.getHostName());
	            }

	        }
	    } catch (SocketException ex) {
	    }
	}
}
