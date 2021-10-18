import java.io.*;
import java.net.*;

public class ClientHandler extends Thread
{
    Socket socket;
    DateWorker dateWorker = new DateWorker();

    public ClientHandler(Socket s)
    {
        socket = s;
    }

    public void run()
    {
        String year = "", month = "", day = "", firstDate = "", secondDate = "";
        try
        {

            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            OutputStream out = socket.getOutputStream();
            PrintWriter pr = new PrintWriter(out, true);

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage="", strResult="";

            while(!clientMessage.equals("quit"))
            {
                clientMessage = br.readLine();
                System.out.println("User command is " + clientMessage);
                switch(clientMessage)
                {
                    case "check":
                        System.out.println("Client want to check the year.");
                        year = br.readLine();
                        pr.println(dateWorker.isLeap(year));
                        break;
                    case "day":
                        System.out.println("Client want to know the day of week");
                        year = br.readLine();
                        month = br.readLine();
                        day = br.readLine();
                        pr.println(dateWorker.getDay(year, month, day));
                        break;
                    case "calc":
                        System.out.println("Client want to calculate the interval");
                        firstDate = br.readLine();
                        secondDate = br.readLine();
                        pr.println("Interval: " + dateWorker.getInterval(firstDate, secondDate) + " days");
                        break;
                    case "quit":
                        System.out.println("Client want to disconnect from server");
                        break;
                }
            }

            socket.close();
            System.out.println("Client disconnected");

        }
        catch(Exception e)
        {
            System.out.println("Error has occurred in Worker.");
        }

    }

}
