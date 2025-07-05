package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;


public class ProdutoDAO {
	public void create(Produto produto) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Produto values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, produto.getIdProduto());
			stmt.setString(2, produto.getNomeProduto());
			stmt.setString(3, produto.getCodBarra());
			stmt.setString(4, produto.getTipoUn());
			stmt.setString(5, produto.getDataFab());
			stmt.setString(6, produto.getDataVal());
			stmt.setString(7, produto.getPrecoUn());
			stmt.setString(8, produto.getEstoque());
			
			stmt.execute();
			System.out.println("Produto cadastrado!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);			
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	
		public ArrayList<Produto> read(){
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Produto> produtos = new ArrayList<>();
			
		try {
			stmt = con.prepareStatement("SELECT * FROM Produto");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getString("idProduto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setCodBarra(rs.getString("codBarra"));
				produto.setTipoUn(rs.getString("tipoUn"));
				produto.setDataFab(rs.getString("dataFab"));
				produto.setDataVal(rs.getString("dataVal"));
				produto.setPrecoUn(rs.getString("precoUn"));
				produto.setEstoque(rs.getString("estoque"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao ler os dados!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;
		
	}
		
		public void update(Produto produto) {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("UPDATE Produto set nomeProduto = ?, codBarra = ?, tipoUn = ?,"
						+ " dataFab = ?, dataVal = ?, precoUn = ?, estoque = ? where codBarra = ?");
				stmt.setString(1, produto.getNomeProduto());
				stmt.setString(2, produto.getCodBarra());
				stmt.setString(3, produto.getTipoUn());
				stmt.setString(4, produto.getDataFab());
				stmt.setString(5, produto.getDataVal());
				stmt.setString(6, produto.getPrecoUn());
				stmt.setString(7, produto.getEstoque());
				stmt.setString(8, produto.getCodBarra());
				
				stmt.execute();
				System.out.println("Produto atualizado!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao atualizar!", e);			
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
		}
		
		public void delete(String codBarra) {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("DELETE FROM Produto where codBarra = ?");
				stmt.setString(1,  codBarra);
				
				stmt.execute();
				System.out.println("Produto apagado!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao apagar!", e);			
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
		}
		
		public ArrayList<Produto> search(String pesquisar){
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			pesquisar = "%" + pesquisar + "%";
			ArrayList<Produto> produtos = new ArrayList<>();
			
		try {
			stmt = con.prepareStatement("SELECT * FROM Produto where nomeProduto like ? or codBarra like ?");
			stmt.setString(1, pesquisar);
			stmt.setString(2, pesquisar);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getString("idProduto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setCodBarra(rs.getString("codBarra"));
				produto.setTipoUn(rs.getString("tipoUn"));
				produto.setDataFab(rs.getString("dataFab"));
				produto.setDataVal(rs.getString("dataVal"));
				produto.setPrecoUn(rs.getString("precoUn"));
				produto.setEstoque(rs.getString("estoque"));				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao ler os dados!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;
		
	}
}
