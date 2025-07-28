package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.VendaProduto;


public class VendaProdutoDAO {


		public void create(VendaProduto vendaProduto) {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("INSERT INTO VendaProduto values(?, ?, ?, ?, ?)");
				stmt.setString(1, vendaProduto.getIdVendaProduto());
				stmt.setString(2, vendaProduto.getIdVenda());
				stmt.setString(3, vendaProduto.getIdProduto());
				stmt.setString(4, vendaProduto.getQuantidade());
				stmt.setString(5, vendaProduto.getValorTotal());
				
							
				stmt.execute();
				System.out.println("Venda Produto cadastrado!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao cadastrar!", e);			
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
		}
		
			public ArrayList<VendaProduto> read(){
				Connection con = ConnectionDatabase.getConnection();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				ArrayList<VendaProduto> vendaProdutos = new ArrayList<>();
				
			try {
				stmt = con.prepareStatement("SELECT * FROM VendaProduto");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					VendaProduto vendaProduto = new VendaProduto();
					vendaProduto.setIdVendaProduto(rs.getString("idVendaProduto"));
					vendaProduto.setIdVenda(rs.getString("idVenda"));
					vendaProduto.setIdProduto(rs.getString("idProduto"));
					vendaProduto.setQuantidade(rs.getString("quantidade"));
					vendaProduto.setValorTotal(rs.getString("valorTotal"));
					vendaProdutos.add(vendaProduto);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				throw new RuntimeException("Erro ao ler os dados!",e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			return vendaProdutos;
			
		}
			
			public void update(VendaProduto vendaProduto) {
				Connection con = ConnectionDatabase.getConnection();
				PreparedStatement stmt = null;
				
				try {
					stmt = con.prepareStatement("UPDATE VendaProduto set idVendaProduto = ?, idVenda = ?, idProduto = ?,"
							+ " quantidade = ?, valorTotal = ? where idVendaProduto = ?");
					stmt.setString(1, vendaProduto.getIdVendaProduto());
					stmt.setString(2, vendaProduto.getIdVenda());
					stmt.setString(3, vendaProduto.getIdProduto());
					stmt.setString(4, vendaProduto.getQuantidade());
					stmt.setString(5, vendaProduto.getValorTotal());
					
					stmt.execute();
					System.out.println("Venda do Produto atualizado!");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Erro ao atualizar!", e);			
				}finally {
					ConnectionDatabase.closeConnection(con, stmt);
				}
			}
			
			public void delete(String idVendaProduto) {
				Connection con = ConnectionDatabase.getConnection();
				PreparedStatement stmt = null;
				
				try {
					stmt = con.prepareStatement("DELETE FROM VendaProduto where idVendaProduto = ?");
					stmt.setString(1,  idVendaProduto);
					
					stmt.execute();
					System.out.println("Venda do Produto apagada!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Erro ao apagar!", e);			
				}finally {
					ConnectionDatabase.closeConnection(con, stmt);
				}
			}
			
			public ArrayList<VendaProduto> search(String pesquisar){
				Connection con = ConnectionDatabase.getConnection();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				pesquisar = "%" + pesquisar + "%";
				ArrayList<VendaProduto> vendaProdutos = new ArrayList<>();
				
			try {
				stmt = con.prepareStatement("SELECT * FROM VendaProduto where idVendaProduto like ?");
				stmt.setString(1, pesquisar);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					VendaProduto vendaProduto = new VendaProduto();
					vendaProduto.setIdVendaProduto(rs.getString("idVendaProduto"));
					vendaProduto.setIdVenda(rs.getString("idVenda"));
					vendaProduto.setIdProduto(rs.getString("idProduto"));
					vendaProduto.setQuantidade(rs.getString("quantidade"));
					vendaProduto.setValorTotal(rs.getString("valorTotal"));				
					vendaProdutos.add(vendaProduto);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				throw new RuntimeException("Erro ao ler os dados!",e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			return vendaProdutos;
			
		}
	}
