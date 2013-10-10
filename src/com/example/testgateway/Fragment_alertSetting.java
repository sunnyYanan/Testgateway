package com.example.testgateway;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_alertSetting extends Fragment {
	public MainActivity ma;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
		 
		     ma = (MainActivity) getActivity();
		     byte [] buffer = {'A','B','C','e'};
		     try {
		    	 if(ma.mSerialPort!=null) {
		    		 ma.mSerialPort.getOutputStream().write(buffer);
		    	 }else {
		    		 System.out.println("has no serialPorts now!");
		    	 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return inflater.inflate(R.layout.fragment_alert_setting, container, false);
	    }
}
