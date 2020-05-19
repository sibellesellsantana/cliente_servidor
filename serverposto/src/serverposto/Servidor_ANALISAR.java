package serverposto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Servidor_ANALISAR extends Thread {

		ConexaoBD conexPosto;
	
		Socket socket;
		JTextArea textAreaServidor;
	
		public Servidor_ANALISAR(Socket socket, JTextArea textAreaServidor) {
			this.socket = socket;
			this.textAreaServidor = textAreaServidor; 
		}

		public void run () {
						
				try {
						
						DataInputStream dadosEntrada = new DataInputStream(socket.getInputStream());
					
						int opcao;
						do {
						opcao = dadosEntrada.readInt(); 
						if (opcao == 1) {
							String nomePosto = dadosEntrada.readUTF(); 
							String enderecoPosto = dadosEntrada.readUTF();
							String telefonePosto = dadosEntrada.readUTF();
							textAreaServidor.append("Dados recebidos do cliente com sucesso...\n");
							textAreaServidor.append("Nome do posto: " + nomePosto + "\n");
							textAreaServidor.append("Endereco do posto: " + enderecoPosto + "\n");
							textAreaServidor.append("Telefone do posto: " + telefonePosto + "\n");
							
						try {
							PreparedStatement pst = conexPosto.con.prepareStatement("insert into novoposto (nome, endereco, telefone) values (?, ?, ?)");
							pst.setString(1, nomePosto);
							pst.setString(2, enderecoPosto);
							pst.setString(3, telefonePosto);
							pst.execute();
							//JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
							textAreaServidor.append("Enviando dados para o cliente...\n");
							DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
							dadosSaida.writeUTF("Dados do posto gravados com sucesso");
							//socket.close();
							
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Erro ao inserir dados!/nErro:"+ex);
						} 
						
						//textAreaServidor.append("Enviando dados para o cliente...\n");
						//DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
						//dadosSaida.writeUTF("Dados do posto gravados com sucesso");
						//JOptionPane.showMessageDialog(null, "Cliente conectado com sucesso ao servidor" + socket.getInetAddress().toString());
						//ObjectOutputStream objectOutputStream = new ObjectOutputStream (socket.getOutputStream()); 
						//objectOutputStream.flush();
						//objectOutputStream.writeObject("Cliente conectou com sucesso");
						//objectOutputStream.writeObject("\nOs dados dessa conexao: " + socket.toString());
						//objectOutputStream.writeObject("\nOK, até mais");
						//objectOutputStream.writeObject("\nFIM");
						
						}//fecha o if (opcao == 1)
						
						
						if (opcao == 2) {
							String nomePosto = dadosEntrada.readUTF(); 
							DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
							conexPosto.conexao();
							conexPosto.executaSql("select *from novoposto where nome like '%"+nomePosto+"%'");
							try {
								conexPosto.rs.first();//pegando primeiro resultado que encontrou no BD
								String codigo = conexPosto.rs.getString("codposto");
								dadosSaida.writeUTF(codigo); 
								//conexPosto.rs.getString("nome");
								String nome = conexPosto.rs.getString("nome");
								dadosSaida.writeUTF(nome);
								String endereco = conexPosto.rs.getString("endereco");
								dadosSaida.writeUTF(endereco);
								String telefone = conexPosto.rs.getString("telefone");	
								dadosSaida.writeUTF(telefone);
					
								//JOptionPane.showMessageDialog(null,conexPosto.rs.getInt("codposto") );
								//JOptionPane.showMessageDialog(null,conexPosto.rs.getString("nome") );
								//JOptionPane.showMessageDialog(null,conexPosto.rs.getString("endereco") );
								//JOptionPane.showMessageDialog(null,conexPosto.rs.getString("telefone") );
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null, "Erro ao buscar posto!/nErro:"+ex);
							}
							
							
						} // fecha if (opcao == 2)
						
						if (opcao == 3) {
							String nomePosto = dadosEntrada.readUTF(); 
							String enderecoPosto = dadosEntrada.readUTF();
							String telefonePosto = dadosEntrada.readUTF();
							String codigoPosto = dadosEntrada.readUTF();
							int codigoPosto2 =  Integer.parseInt(codigoPosto);
							
							try {
								PreparedStatement pst = conexPosto.con.prepareStatement ("update novoposto set nome=?, endereco=?,telefone=? where codposto =?");
								pst.setString(1, nomePosto);
								pst.setString(2, enderecoPosto);
								pst.setString(3, telefonePosto);
								pst.setInt(4, codigoPosto2);
	 
								pst.execute();
		
								DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
								dadosSaida.writeUTF("Dados do posto atualizados com sucesso");
								//socket.close();
								
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!/nErro:"+ex);
							} 
							
							
						} // fecha if (opcao == 3)
						
						if (opcao == 4) {
							String nomePosto = dadosEntrada.readUTF(); 
							String enderecoPosto = dadosEntrada.readUTF();
							String telefonePosto = dadosEntrada.readUTF();
							String codigoPosto = dadosEntrada.readUTF();
							int codigoPosto2 =  Integer.parseInt(codigoPosto);
							
							try {
								PreparedStatement pst = conexPosto.con.prepareStatement("delete from novoposto where codposto = ? ");
								pst.setInt(1, codigoPosto2);
								pst.execute();
								DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
								dadosSaida.writeUTF("Dados excluídos com sucesso");
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null, "Erro ao excluir dados\n:Erro:" + ex);
							}
							
							
						} //fecha if (opcao == 4)
						
						if (opcao == 5) {
							
								DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
								dadosSaida.writeUTF("Você escolheu sair do programa");
								textAreaServidor.append("Conexao esta fechada com o cliente: " + socket.getInetAddress().toString()+"\n");
						}
						
						
						
						} while (opcao >0 && opcao <6); //fecha do while (valor de opcao)
					
			}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro" + ex);
			} 
		
			}// fecha metodo run
				
			}// fecha classe
