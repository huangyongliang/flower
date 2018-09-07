import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class TestSocket {
    public static void main(String[] args) {
        String serverIp = "118.178.247.224";

        int port = 13115;

        try{
            Socket client = new Socket(serverIp,port );
            OutputStream outputStream = client.getOutputStream();
            String message="你好  yiwangzhibujian";
            client.getOutputStream().write(message.getBytes("UTF-8"));
            boolean connected = client.isConnected();
            System.out.println(connected);
            outputStream.close();
            client.close();
            URL url = new URL("：https://prepay.jbh.com/payCenters/undeposit/payForAnotherController/singleDebit");
            url.openConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
