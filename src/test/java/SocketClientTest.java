import com.Entity.MessageEntity;
import com.Enum.Envent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * @author Vic Zhang
 * @date 2020/2/26 4:58 PM
 */
public class SocketClientTest {

    public static void main(String[] args) throws URISyntaxException, JSONException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;//失败重连的时间间隔
        options.timeout = 500;//连接超时时间(ms)
        Socket socket = IO.socket("http://localhost:9099/", options);

        JSONObject ob=new JSONObject();
        ob.put("name" , "vic");
        ob.put("words" ,"hello");

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit(Envent.MESSAGEEVENT.toString(), ob);
            }
        });

        socket.on(Envent.MESSAGEEVENT.toString(), new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (Object obj : args) {
                    System.out.println("客户端收到" + obj);
                }
                socket.send("hello");
            }
        });

        socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("sessionId:" + socket.id());
                for (Object obj : args) {
                    System.out.println(obj);
                }
                System.out.println("收到服务器应答，将要断开连接...");
//                socket.disconnect();
            }
        });
        socket.connect();
    }

}
