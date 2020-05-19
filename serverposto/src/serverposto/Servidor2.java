package serverposto;

//import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 


import javax.swing.JOptionPane;
import javax.swing.JTextArea;

class Servidor2 extends Thread {
	//ConexaoBD conexPosto;
			
			private Socket socket;
			private JTextArea textAreaServidor;
			private ConexaoBD conexPosto;
			
			public Servidor2(Socket socket, JTextArea textAreaServidor, ConexaoBD conexPosto) {
				this.socket = socket;
				this.textAreaServidor = textAreaServidor; 
				this.conexPosto = conexPosto; 
			}


			public void run () {
						
				try {
						
						DataInputStream dadosEntrada = new DataInputStream(socket.getInputStream());
					
						int opcao;
						do {
						opcao = dadosEntrada.readInt(); 
						System.out.println(opcao);
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
						//JOptionPane.showMessageDialog(null, "Cliente conectado com sucesso ao Servidor2" + socket.getInetAddress().toString());
						//ObjectOutputStream objectOutputStream = new ObjectOutputStream (socket.getOutputStream()); 
						//objectOutputStream.flush();
						//objectOutputStream.writeObject("Cliente conectou com sucesso");
						//objectOutputStream.writeObject("\nOs dados dessa conexao: " + socket.toString());
						//objectOutputStream.writeObject("\nOK, at� mais");
						//objectOutputStream.writeObject("\nFIM");
						
						}//fecha o if (opcao == 1)
						
						
						if (opcao == 2) {
							String nomePosto = dadosEntrada.readUTF(); 
							DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
							conexPosto.executaSql("select *from novoposto where lower(nome) like '%"+nomePosto.toLowerCase()+"%'");
							try {
								if (conexPosto.rs.first()) {//pegando primeiro resultado que encontrou no BD
								String codigo = conexPosto.rs.getString("codposto");
								dadosSaida.writeUTF(codigo); 
								//conexPosto.rs.getString("nome");
								String nome = conexPosto.rs.getString("nome");
								dadosSaida.writeUTF(nome);
								String endereco = conexPosto.rs.getString("endereco");
								dadosSaida.writeUTF(endereco);
								String telefone = conexPosto.rs.getString("telefone");	
								dadosSaida.writeUTF(telefone);
								}
								else
								{
									String codigo = " ";
									dadosSaida.writeUTF(codigo);
									String nome = " ";
									dadosSaida.writeUTF(nome);
									String endereco = " ";
									dadosSaida.writeUTF(endereco);
									String telefone = " ";
									dadosSaida.writeUTF(telefone);
									
								}
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
								dadosSaida.writeUTF("Dados exclu�dos com sucesso");
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null, "Erro ao excluir dados\n:Erro:" + ex);
							}
							
							
						} //fecha if (opcao == 4)
						
						if (opcao == 5) {
							
								DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
								dadosSaida.writeUTF("Você escolheu sair do programa");
								textAreaServidor.append("Conexao esta fechada com o cliente: " + socket.getInetAddress().toString()+"\n");
								socket.close();
								conexPosto.desconecta();
						}
						
						if (opcao == 6) {
						
							PegaPosto pegaPosto = new PegaPosto(conexPosto);
							//pegaPosto.buscarPostos();
							ArrayList<BeansPosto> arrayListPostos = new ArrayList<>();
							arrayListPostos = pegaPosto.buscarPostos();
							
							 //TESTE PARA VERIFICAR SE ESTÁ CRIANDO LISTA COM OS POSTOS BD - OK ESTÁ - OK
							//for(int i = 0;i<arrayListPostos.size();i++){   
							//	System.out.println(arrayListPostos.get(i));  
							//}
							
							
							ObjectOutputStream dadosSaida = new ObjectOutputStream(socket.getOutputStream());
							
							dadosSaida.writeObject(arrayListPostos);//passando a lista
							
						}
						
						
						} while (opcao >0 && opcao <7); //fecha do while (valor de opcao)
					
			}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro" + ex);
			} 
		
			}// fecha metodo run
				
			}// fecha classe
