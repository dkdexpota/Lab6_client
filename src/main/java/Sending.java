import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Sending {
    public static void sanding (SendPack sp, int SERVICE_PORT, DatagramSocket clientSocket) throws UnknownHostException {
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] receivingDataBuffer = new byte[2048];
        DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

        try {
            byte[] data = Serialize.serialize(sp);
            byte[] bytes = ByteBuffer.allocate(4).putInt(data.length).array();
            DatagramPacket dp = new DatagramPacket(data, data.length , IPAddress , SERVICE_PORT);
            DatagramPacket lp = new DatagramPacket(bytes, bytes.length, IPAddress , SERVICE_PORT);
            clientSocket.send(dp);
            clientSocket.receive(inputPacket);
            ReturnTreat.treatment(Deserialize.deserialize(inputPacket.getData()));
        } catch (SocketTimeoutException e) {
            System.out.println("Истекло время ожидания ответа сервера.");
        } catch (IOException e) {
            System.out.println("Ошибка подключения.");
        }
    }
}
