package com.dva.app_project.broadcast_and_get_robot_ip;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.dva.app_project.activities.LoginActivity;

public class FindBroadcastIp {
    LoginActivity m;

    //클래스 생성시에 activity 클래스를 전달 받아야 됩니다.
    public FindBroadcastIp(LoginActivity m){
        this.m = m;
    }

    //브로드캐스트 ip 구하는 메소드
    public String checkAvailableConnection() {
        //네트워크 연결을 확인하는 객체
        ConnectivityManager connMgr = (ConnectivityManager) this.m.getSystemService(Context.CONNECTIVITY_SERVICE);

        //네트워크 정보를 받아오는 객체
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        String ip_addr="";
        if (wifi.isAvailable()) {
            //스마트폰의 와이파이 하드웨어에서 현재 ip를 받아옵니다.
            WifiManager myWifiManager = (WifiManager) this.m.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
            int ipAddress = myWifiInfo.getIpAddress();

            //받아온 ip 마지막을 255로 바꿈
            ip_addr = android.text.format.Formatter.formatIpAddress(ipAddress);
            ip_addr = ip_addr.substring(0,ip_addr.lastIndexOf(".")+1)+"255";
            //브로드캐스트 ip를 리턴
            return ip_addr;
        }
        else{
            return "Wifi not connected!";
        }

    }
}
