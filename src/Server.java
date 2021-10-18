import java.io.*;
import java.net.*;
import java.util.Hashtable;

public class Server
{
    public static void main(String []args)
    {
        try
        {

            ServerSocket ss = new ServerSocket(555);

            while(!ss.isClosed())
            {
                Socket socket = ss.accept();	// accept connection from client
                System.out.println("A new client is connected.");

                InputStream in = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                OutputStream out = socket.getOutputStream();
                PrintWriter pr = new PrintWriter(out, true);

                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }

        }
        catch(Exception e)
        {
            System.out.println("Some kind of error has occurred.");
        }

    }
}