package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Venda;

public class VendaDAO {
	public void create(Venda venda) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Produto values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, venda.getIdVenda());
			stmt.setString(2, venda.getIdCliente());
			stmt.setString(3, venda.getIdFuncionario());
			stmt.setString(4, venda.getDataVenda());
			stmt.setString(5, venda.getPrecoTotal());
			stmt.setString(6, venda.getFormaPag());
			stmt.setString(7, venda.getQuantTotal());
			
			
			stmt.execute();
			System.out.println("Venda concluída!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);			
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	
		public ArrayList<Venda> read(){
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Venda> vendas = new ArrayList<>();
			
		try {
			stmt = con.prepareStatement("SELECT * FROM Venda");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getString("idVenda"));
				venda.setIdCliente(rs.getString("idCliente"));
				venda.setIdFuncionario(rs.getString("idFuncionario"));
				venda.setDataVenda(rs.getString("dataVenda"));
				venda.setPrecoTotal(rs.getString("precoTotal"));
				venda.setFormaPag(rs.getString("formaPag"));
				venda.setQuantTotal(rs.getString("quantTotal"));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao ler os dados!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return vendas;
		
	}
		
		public void update(Venda venda) {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("UPDATE Venda set idVenda = ?, idCliente = ?, idFuncionario = ?,"
						+ " dataVenda = ?, precoTotal = ?, formaPag = ?, quantTotal = ? where idVenda = ?");
				stmt.setString(1, venda.getIdVenda());
				stmt.setString(2, venda.getIdCliente());
				stmt.setString(3, venda.getIdFuncionario());
				stmt.setString(4, venda.getDataVenda());
				stmt.setString(5, venda.getPrecoTotal());
				stmt.setString(6, venda.getFormaPag());
				stmt.setString(7, venda.getQuantTotal());
				stmt.setString(8, venda.getIdVenda());
				stmt.execute();
				System.out.println("Venda atualizado!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao atualizar!", e);			
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
		}
		
		public void delete(String idVenda) {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("DELETE FROM Venda where idVenda = ?");
				stmt.setString(1,  idVenda);
				
				stmt.execute();
				System.out.println("Venda apagada!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao apagar!", e);			
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
		}
		
		public ArrayList<Venda> search(String pesquisar){
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			pesquisar = "%" + pesquisar + "%";
			ArrayList<Venda> vendas = new ArrayList<>();
			
		try {
			stmt = con.prepareStatement("SELECT * FROM Venda where idVenda like ?");
			stmt.setString(1, pesquisar);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getString("idVenda"));
				venda.setIdCliente(rs.getString("idCliente"));
				venda.setIdFuncionario(rs.getString("idFuncionario"));
				venda.setDataVenda(rs.getString("dataVenda"));
				venda.setPrecoTotal(rs.getString("precoTotal"));
				venda.setFormaPag(rs.getString("formaPag"));
				venda.setQuantTotal(rs.getString("quantTotal"));				
				vendas.add(venda);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao ler os dados!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return vendas;
		
	}
}

