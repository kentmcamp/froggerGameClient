import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientService implements Runnable {

  private Socket s;
  private Scanner in;

  public ClientService(Socket aSocket) {
    this.s = aSocket;
  }

  public void run() {
    try {
      in = new Scanner(s.getInputStream());
      processRequest();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        s.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  //processing the requests
  public void processRequest() throws IOException {
    //if next request is empty then return
    while (true) {
      if (!in.hasNext()) {
        return;
      }
      String command = in.next();
      if (command.equals("Quit")) {
        return;
      } else {
        executeCommand(command);
      }
    }
  }

  public void executeCommand(String command) throws IOException {
    if (command.equals("PLAYER")) {
      int playerNo = in.nextInt();
      String playerAction = in.next();
      int playerX = in.nextInt();
      int playerY = in.nextInt();
      System.out.println(
        "Player " +
        playerNo +
        " " +
        playerAction +
        " " +
        playerX +
        ", " +
        playerY
      );
    }

    if (command.equals("MOVEUP")) {
      String response = in.nextLine();
      Frogger frogger = new Frogger();
      frogger.setPosY(Integer.parseInt(response));
    }

    if (command.equals("MOVEDOWN")) {
      String response = in.nextLine();
      Frogger frogger = new Frogger();
      frogger.setPosY(Integer.parseInt(response));
    }
  }
}
