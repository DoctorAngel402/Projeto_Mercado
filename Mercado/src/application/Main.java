package application;
	
import java.sql.Connection;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Cliente;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene main;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			stage = primaryStage;
			
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));
			main = new Scene(fxmlLogin);
			
			primaryStage.setScene(main);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO =new ClienteDAO();
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		cliente.setNomeCliente("CABRAL");
		cliente.setCpfCliente("85295175236");
		cliente.setDataNasc("2005-01-30");
		cliente.setTelefone("63992000000");
		cliente.setEmail("cabral@gmail.com");
		cliente.setEndereco("Rua Portugal");
		
		//clienteDAO.update(cliente);
		//clienteDAO.delete("85295175236");
		
		
		
		
		
		
		
		
		
		clientes = clienteDAO.search("mariana");
		
		for(int i = 0; i < clientes.size(); i++) {		
		cliente = clientes.get(i);
		System.out.print("||");
		System.out.print(cliente.getIdCliente());
		System.out.print(" | ");
		System.out.print(cliente.getNomeCliente());
		System.out.print(" | ");
		System.out.print(cliente.getCpfCliente());
		System.out.print(" | ");
		System.out.print(cliente.getDataNasc());
		System.out.print(" | ");
		System.out.print(cliente.getTelefone());
		System.out.print(" | ");
		System.out.print(cliente.getEmail());
		System.out.print(" | ");
		System.out.print(cliente.getEndereco());
		System.out.print("");
		
		}
		
		launch(args);
	}
}
