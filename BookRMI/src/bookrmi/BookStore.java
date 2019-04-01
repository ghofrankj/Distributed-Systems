/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookrmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Ghadeer
 */
public class BookStore extends UnicastRemoteObject implements BookInterface {

    private static final long serialVersionUID = 1L;
    private List<Book> bookList;

    protected BookStore(List<Book> list) throws RemoteException {
        super();
        this.bookList = list;
    }

    @Override
    public Book findBook(Book book) throws RemoteException {
        Predicate<Book> predicate = x -> x.getCode().equals(book.getCode());
        return bookList.stream().filter(predicate).findFirst().get();
    }

    @Override
    public List<Book> allBooks() throws RemoteException {
        return bookList;
    }

    private static List<Book> initializeList() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Head First Java, 2nd Edition", "A1", 31.41));
        list.add(new Book("Java In A Nutshell", "A2", 10.90));
        list.add(new Book("Java: The Complete Reference", "B1", 40.18));
        list.add(new Book("Head First Servlets and JSP", "B2", 35.41));
        list.add(new Book("Java Puzzlers: Traps, Pitfalls, and Corner Cases", "C1", 39.99));
        return list;
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            BookStore store = new BookStore(initializeList());
            reg.rebind("rmi://localhost/service", store);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

}
