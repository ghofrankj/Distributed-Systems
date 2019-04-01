/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

/**
 *
 * @author Ghadeer
 */

public class Client {

    private static BookInterface server;

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            server = (BookInterface) reg.lookup("rmi://localhost/service");

            boolean findmore;
            do {

                String[] options = {"Show All", "Find a book", "Exit"};

                int choice = JOptionPane.showOptionDialog(null, "Choose an action", "Option dialog",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                switch (choice) {

                    case 0:
                        List<Book> list = server.allBooks();
                        StringBuilder message = new StringBuilder();
                        list.forEach(x -> {
                            message.append(x.toString() + "\n");
                        });
                        JOptionPane.showMessageDialog(null, new String(message));
                        break;
                    case 1:
                        String code = JOptionPane.showInputDialog("Type the code of the book you want to find.");
                        try {
                            Book response = server.findBook(new Book(code));
                            JOptionPane.showMessageDialog(null, "Title: "
                                    + response.getTitle() + "\n" + "Cost: $"
                                    + response.getCost(),
                                    response.getCode(), JOptionPane.INFORMATION_MESSAGE);
                        } catch (NoSuchElementException ex) {
                            JOptionPane.showMessageDialog(null, "Not found");
                        }
                        break;
                    default:
                        System.exit(0);
                        break;
                }
                findmore = (JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION);
            } while (findmore);

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
    }
}
