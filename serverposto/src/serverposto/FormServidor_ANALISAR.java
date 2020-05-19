package serverposto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.lang.Thread; 

public class FormServidor_ANALISAR extends JFrame {

	ConexaoBD conexPosto;
	private JPanel contentPane;
	private JTextField txtServidor;

	/**
	 * Launch the application.
	 */
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormServidor_ANALISAR frame = new FormServidor_ANALISAR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	  
	}
	/**
	 * Create the frame.
	 */
	public FormServidor_ANALISAR() {
		
		conexPosto = new ConexaoBD();
		conexPosto.conexao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtServidor = new JTextField();
		txtServidor.setFont(new Font("Arial", Font.BOLD, 20));
		txtServidor.setHorizontalAlignment(SwingConstants.CENTER);
		txtServidor.setText("SERVIDOR");
		txtServidor.setBounds(36, 23, 133, 30);
		contentPane.add(txtServidor);
		txtServidor.setColumns(10);
		
		JButton btnIniciaServidor = new JButton("Inicia servidor");
		
		btnIniciaServidor.setBounds(179, 23, 115, 23);
		contentPane.add(btnIniciaServidor);
		
		JTextArea textAreaServidor = new JTextArea();
		textAreaServidor.setBounds(36, 64, 513, 231);
		contentPane.add(textAreaServidor);
	

	
	 class servidor extends Thread {
		public void run() {
			textAreaServidor.append("Criando o servidor socket...\n");
			
			try {
				ServerSocket serverSocket = new ServerSocket(5555);
				textAreaServidor.append("Servidor rodando na porta 5555\n");
				while(true) { 
					textAreaServidor.append("Aguardando conexao de cliente...\n");
					Socket socket = serverSocket.accept();
					new Servidor_ANALISAR(socket, textAreaServidor).start(); 
					/*
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
					//objectOutputStream.writeObject("\nOK, at� mais");
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
							dadosSaida.writeUTF("Dados exclu�dos com sucesso");
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Erro ao excluir dados\n:Erro:" + ex);
						}
						
						
					} //fecha if (opcao == 4)
					
					if (opcao == 5) {
						
							DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
							dadosSaida.writeUTF("Voc� escolheu sair do programa");
					}
					
					
					
					} while (opcao >0 && opcao <6); //fecha do while (valor de opcao)
				
			}// fecha while (true)*/}
			}catch (IOException ex) {
				JOptionPane.showMessageDialog(null,"Erro "+ ex);
			}
				
		}//fecha try
	} //fecha class client

	 btnIniciaServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new servidor().start();
			}
		});
	} //fecha formServidor
	
} //fecha class FormServidor

		