import java.io.*;
import java.net.*;
import java.util.Hashtable;

public class Client
{

    public static void main(String []args)
    {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            System.out.println("Usage:\n" +
                    "Use one of commands:\n" +
                    "check - to check is year leap\n" +
                    "calc - to calc interval length\n" +
                    "day - to get the name of day of week\n" +
                    "quit - to exit.\n" +
                    "----");

            Socket socket = new Socket("127.0.0.1",555);

            String year = "", month = "", day = "", result = "", firstDate = "", secondDate = "";
            String clientMessage = "";

            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            OutputStream out = socket.getOutputStream();
            PrintWriter pr = new PrintWriter(out, true);

            while (!clientMessage.equals("quit"))
            {
                System.out.println("Input command:");
                clientMessage = input.readLine();
                switch(clientMessage)
                {
                    case "check":
                        pr.println("check");
                        System.out.println("Input the year:");
                        year = input.readLine();
                        while(!year.matches("^[1-9][0-9]{0,3}")){
                            System.out.println("It's not a year. Try again");
                            year = input.readLine();
                        }
                        pr.println(year);
                        result = br.readLine();
                        System.out.println("Is year " + year + " leap? " + result);
                        break;
                    case "day":
                        pr.println("day");
                        System.out.println("Input the year:");
                        year = input.readLine();
                        while(!year.matches("^[1-9][0-9]{0,3}")){
                            System.out.println("It's not a year. Try again");
                            year = input.readLine();
                        }
                        System.out.println("Input the month:");
                        month = input.readLine();
                        while(!month.matches("^[1-9]|^[1][0-2]")){
                            System.out.println("It's not a month. Try again and input number from 1 to 12");
                            month = input.readLine();
                        }
                        System.out.println("Input the day:");
                        day = input.readLine();
                        while(!day.matches("^[1-9][0-1]*")){
                            System.out.println("It's not a day. Try again and input number from 1 to 31");
                            day = input.readLine();
                        }
                        pr.println(year);
                        pr.println(month);
                        pr.println(day);
                        result = br.readLine();
                        System.out.println(result);
                        break;
                    case "calc":
                        pr.println("calc");
                        System.out.println("Input the first date in format dd.MM.yyyy:");
                        firstDate = input.readLine();
                        while(!firstDate.matches("^[0-3][0-9].[0-1][0-9].[1-9][0-9]*")){
                            System.out.println("Date is wrong. Try again");
                            firstDate = input.readLine();
                        }
                        System.out.println("Input the second date in format dd.MM.yyyy:");
                        secondDate = input.readLine();
                        while(!secondDate.matches("^[0-3][0-9].[0-1][0-9].[1-9][0-9]*")){
                            System.out.println("Date is wrong. Try again");
                            secondDate = input.readLine();
                        }
                        pr.println(firstDate);
                        pr.println(secondDate);
                        result = br.readLine();
                        System.out.println(result);
                        break;
                    case "quit":
                        pr.println("quit");
                        System.out.println("Trying to disconnect from server...");
                        break;
                    default:
                        System.out.println("Command doesn't exist. Try again.");
                        break;
                }
            }

            socket.close();
            System.out.println("End of this session.");

        }
        catch(Exception e)
        {
            System.out.println("Some kind of error has occurred.");
            System.exit(0);
        }

    }
}
