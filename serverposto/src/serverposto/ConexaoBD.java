package serverposto;

import java.sql.*;

import javax.swing.JOptionPane;

//responsável pela conexao com o BD 
public class ConexaoBD {
	
	public Statement stm; //realizar pesquisa no BD
	public ResultSet rs; //armazenar resultado da pesquisa
	private String driver = "org.postgreslq.Driver"; //identifica o servico de BD
	private String caminho = "jdbc:postgresql://localhost/postos"; //qual o caminho do BD
	private String usuario = "admin_bd";
	private String senha = "12345"; 
	public Connection con; //variavel por fazer conexao com BD
	
	public void conexao () {//metodo para realizar conexao
	
		try {
			System.setProperty("jdbc.Drivers", driver); //seta a propriedade de driver de conexao
			try {
				Class.forName("org.postgresql.Driver");
			}catch (ClassNotFoundException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("VERIFICAR ERRO!");
			}
			con=DriverManager.getConnection(caminho, usuario, senha);
			//JOptionPane.showMessageDialog(null, "Conexao Efetuada com sucesso!!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao se conectar com o BD:\n"+ex.getMessage());
		}
	}
	
	public void executaSql(String sql) {
		try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ExecutaSql: \n"+ex.getMessage());
		}
		
		
	}
		
		public void desconecta(){
			try {
				con.close();
				//JOptionPane.showMessageDialog(null, "BD desconectado com sucesso");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao fechar conexao com BD:\n" +ex.getMessage());
			}
			
			
		}

		
	
}
