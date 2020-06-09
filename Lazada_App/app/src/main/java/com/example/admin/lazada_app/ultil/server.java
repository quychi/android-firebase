package com.example.admin.lazada_app.ultil;

public class server {
    public static String localhost = "192.168.3.113";// ip ở máy là ip động
//    public static String Path_loaisp = "http://" + localhost + "/server/getloaisp.php";
//    public static String Path_sanpham = "http://" + localhost + "/server/getsanphammoinhat.php";
//    public static String Path_dienthoai = "http://" + localhost + "/server/getsanpham.php?page=";
//    public static String Path_laptop = "http://" + localhost + "/server/getlaptop.php?page=";
    public static String Path_Thongtinkhachhang = "http://" + localhost + "/server/thongtinkhachhang.php";
    public static String Path_Chitietdonhang = "http://" + localhost + "/server/chitietdonhang.php";
    public static String Path_Khachhang = "http://" + localhost + "/server/getkhachhang.php";
//    public static String Path_ThemKhachhang = "http://" + localhost + "/server/themkhachhang.php";
//    public static String Path_nhanxet = "http://" + localhost + "/server/getkhachhangnhanxet.php";
    public static String Path_ThemNhanXet = "http://" + localhost + "/server/themnhanxet.php";
    public static String Path_ThemSanPham = "http://" + localhost + "/server/themsanpham.php";
    public static String Path_SuaSanPham = "http://" + localhost + "/server/suasanpham.php";
    public static String Path_XoaSanPham = "http://" + localhost + "/server/xoasanpham.php";
//    public static String Path_GetAllSanPham = "http://" + localhost + "/server/getallsanpham.php";

//    public static void KQ(Context context) {
//        String wifiIp = getWifiIPAddress(context);
//
////        if(wifiIp.equals(""))
////            localhost=mobileIp;4
////        else
//        localhost = wifiIp;
//    }

//    public static String getMobileIPAddress() {
//        try {
//            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface intf : interfaces) {
//                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
//                for (InetAddress addr : addrs) {
//                    if (!addr.isLoopbackAddress()) {
//                        return addr.getHostAddress();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//        } // for now eat exceptions
//        return "";
//    }
//
//        public  static String getWifiIPAddress(Context context) {
//        WifiManager wifiMgr = (WifiManager) context.getSystemService(WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
//        int ip = wifiInfo.getIpAddress();
//        String ipAddress = Formatter.formatIpAddress(ip);
//        return ipAddress;
//    }
////    public static String getWifiIPAddress(Context context) {
////        WifiManager wifiMgr = (WifiManager) context.getSystemService(WIFI_SERVICE);
////        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
////        int ip = wifiInfo.getIpAddress();
////        String ipAddress = Formatter.formatIpAddress(ip);
////        return "http://" + ipAddress + "/server/getloaisp.php";
////    }
}
