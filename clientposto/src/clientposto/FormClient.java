package clientposto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
//import java.awt.List;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class FormClient extends JFrame {
	JFrame frame = new JFrame("Coleta de lâmpadas Fluorescentes");
	private JPanel contentPane;
	private JTextField txtCliente;
	private JButton btnNewButton_AdicionarPosto;
	private JButton btnNewButton_Salvar;
	private JButton btnNewButton_Cancelar;
	private JButton btnNewButton_Editar;
	private JButton btnNewButton_Deletar;
	private JLabel lblNewLabel_ID;
	private JLabel lblNewLabel_Nome;
	private JLabel lblNewLabel_Endereco;
	private JLabel lblNewLabel_Telefone;
	private JTextField textField_ID;
	private JTextField textField_Nome;
	private JTextField textField_Endereco;
	private JTextField textField_Telefone;
	private JTextField textField_Pesquisar;
	private JButton btnNewButton_Sair;
	private JLabel lblNewLabel_Importancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormClient frame = new FormClient();
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
	public FormClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 794);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Arial", Font.BOLD, 20));
		txtCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCliente.setText("CLIENTE ");
		txtCliente.setBounds(25, 11, 145, 50);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JButton btnIniciarComunicacao = new JButton("Iniciar comunicacao com o servidor");
		btnIniciarComunicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciarComunicacao.setBounds(250, 11, 243, 31);
		contentPane.add(btnIniciarComunicacao);
		
		JTextArea textAreaImportancia = new JTextArea();
		textAreaImportancia.setBounds(180, 53, 488, 141);
		contentPane.add(textAreaImportancia);
		
		btnNewButton_AdicionarPosto = new JButton("Adicionar Posto\r\n");
		btnNewButton_AdicionarPosto.setBounds(146, 387, 135, 31);
		contentPane.add(btnNewButton_AdicionarPosto);
		
		btnNewButton_Salvar = new JButton("Salvar");
		btnNewButton_Salvar.setBounds(485, 344, 89, 23);
		contentPane.add(btnNewButton_Salvar);
		
		btnNewButton_Cancelar = new JButton("Cancelar");
		btnNewButton_Cancelar.setBounds(619, 292, 89, 23);
		contentPane.add(btnNewButton_Cancelar);
		
		btnNewButton_Editar = new JButton("Editar\r\n");
		btnNewButton_Editar.setBounds(485, 292, 89, 23);
		contentPane.add(btnNewButton_Editar);
		
		btnNewButton_Deletar = new JButton("Deletar");
		btnNewButton_Deletar.setBounds(619, 344, 89, 23);
		contentPane.add(btnNewButton_Deletar);
		
		lblNewLabel_ID = new JLabel("ID");
		lblNewLabel_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_ID.setBounds(25, 216, 46, 23);
		contentPane.add(lblNewLabel_ID);
		
		lblNewLabel_Nome = new JLabel("Nome");
		lblNewLabel_Nome.setToolTipText("Nome do estabelecimento (ou ponto de refer\u00EAncia) onde situa-se o posto de coleta.\r\n");
		lblNewLabel_Nome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_Nome.setBounds(25, 270, 58, 20);
		contentPane.add(lblNewLabel_Nome);
		
		lblNewLabel_Endereco = new JLabel("Endere\u00E7o\r\n");
		lblNewLabel_Endereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_Endereco.setBounds(25, 312, 58, 23);
		contentPane.add(lblNewLabel_Endereco);
		
		lblNewLabel_Telefone = new JLabel("Telefone");
		lblNewLabel_Telefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_Telefone.setBounds(25, 353, 58, 27);
		contentPane.add(lblNewLabel_Telefone);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(95, 216, 73, 23);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		textField_ID.setEnabled(false);
		
		textField_Nome = new JTextField();
		textField_Nome.setBounds(95, 270, 349, 20);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);
		
		textField_Endereco = new JTextField();
		textField_Endereco.setBounds(95, 313, 349, 20);
		contentPane.add(textField_Endereco);
		textField_Endereco.setColumns(10);
		
		textField_Telefone = new JTextField();
		textField_Telefone.setBounds(93, 356, 349, 20);
		contentPane.add(textField_Telefone);
		textField_Telefone.setColumns(10);
		
		textField_Pesquisar = new JTextField();
		textField_Pesquisar.setBounds(429, 216, 187, 23);
		contentPane.add(textField_Pesquisar);
		textField_Pesquisar.setColumns(10);
		
		btnNewButton_Sair = new JButton("Sair");
		btnNewButton_Sair.setBounds(577, 402, 89, 23);
		contentPane.add(btnNewButton_Sair);
		
		lblNewLabel_Importancia = new JLabel("Conhe\u00E7a a import\u00E2ncia\r\n");
		lblNewLabel_Importancia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_Importancia.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_Importancia.setBounds(12, 82, 158, 57);
		contentPane.add(lblNewLabel_Importancia);
		
		JButton btnNewButton_Pesquisar = new JButton("Pesquisar");
		btnNewButton_Pesquisar.setBounds(626, 216, 105, 23);
		contentPane.add(btnNewButton_Pesquisar);
		
		JButton btnNewButton_Listar = new JButton("Listar");
		btnNewButton_Listar.setBounds(146, 525, 89, 42);
		contentPane.add(btnNewButton_Listar);
		
		JTextArea textArea_Listagem = new JTextArea();
		textArea_Listagem.setBounds(254, 442, 414, 241);
		contentPane.add(textArea_Listagem);
	
	
	class cliente extends Thread{
		public void run() {
	
			try {
				JOptionPane.showMessageDialog(null, "Iniciando conexão com servidor.\n");
				Socket socket = new Socket("192.168.40.100", 5555);
				JOptionPane.showMessageDialog(null, "Comunicacao feita com sucesso\n");
				JOptionPane.showMessageDialog(null, "Servidor aceito" + socket.getInetAddress().toString());
				
				textAreaImportancia.append("Fazer o descarte de lâmpadas fluorescentes de maneira correta é uma obrigação\n"); 
				textAreaImportancia.append("de todos. Isso porque descartar lâmpadas fluorescentes usadas incorretamente\n");
				textAreaImportancia.append(" pode ser muito nocivo para a saúde humana e do meio ambiente.Isso porque apesar\n");
				textAreaImportancia.append("da praticidade, durabilidade e economiada da lâmpada fluorescente, no interior dela \n");
				textAreaImportancia.append("existe um componente químico muito perigoso à saúde: o mercúrio, um metal pesado\n");
				textAreaImportancia.append("e tóxico. Devido a ele, o descarte se torna muito complicado");
				
				DataOutputStream dadosSaida = new DataOutputStream(socket.getOutputStream());
				DataInputStream dadosRecebidos = new DataInputStream(socket.getInputStream()); 
				
				//textAreaImportancia.append("\nRecebendo mensagens vindas do servidor\n");
				//ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()); 
				//String textoServidor = "";
				//while (!textoServidor.equals("FIM")) {
				//	textoServidor = (String) objectInputStream.readObject();
				//	textAreaImportancia.append(textoServidor);
				//}
				//textAreaImportancia.append("\nFIM");
			 
				btnNewButton_AdicionarPosto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcao = 1;
						btnNewButton_AdicionarPosto.setEnabled(true);
						btnNewButton_Salvar.setEnabled(true);
						btnNewButton_Editar.setEnabled(true);
						btnNewButton_Cancelar.setEnabled(true);
						btnNewButton_Deletar.setEnabled(true);
						btnNewButton_Sair.setEnabled(true);
						textField_ID.setEnabled(false);
						textField_Nome.setEnabled(true);
						textField_Endereco.setEnabled(true);
						textField_Telefone.setEnabled(true);
						textField_Pesquisar.setEnabled(true);
						
					try {
							dadosSaida.writeInt(opcao);	
							dadosSaida.writeUTF(textField_Nome.getText());
							dadosSaida.writeUTF(textField_Endereco.getText());
							dadosSaida.writeUTF(textField_Telefone.getText());
											
							String pegaDadoEntrada;
							pegaDadoEntrada = dadosRecebidos.readUTF(); 
							JOptionPane.showMessageDialog(null, pegaDadoEntrada);
							textField_Nome.setText("");
							textField_Endereco.setText("");
							textField_Telefone.setText("");
							textField_Nome.setEnabled(true);
							textField_Endereco.setEnabled(true);
							textField_Telefone.setEnabled(true);
							
						
					}catch (IOException ex) {
							JOptionPane.showMessageDialog(null,"Erro "+ ex);
					}
				
					}
					});//comando do botao novo
				
				//socket.close();
				
				
				
				btnNewButton_Pesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcao = 2;
						btnNewButton_AdicionarPosto.setEnabled(true);
						btnNewButton_Salvar.setEnabled(true);
						btnNewButton_Editar.setEnabled(true);
						btnNewButton_Cancelar.setEnabled(true);
						btnNewButton_Deletar.setEnabled(true);
						btnNewButton_Sair.setEnabled(true);
						textField_ID.setEnabled(false);
						textField_Nome.setEnabled(true);
						textField_Endereco.setEnabled(true);
						textField_Telefone.setEnabled(true);
						textField_Pesquisar.setEnabled(true);
						
						try {
							dadosSaida.writeInt(opcao);
							dadosSaida.writeUTF(textField_Pesquisar.getText());
							String codigoRecebido;
							codigoRecebido = dadosRecebidos.readUTF();
							textField_ID.setText(codigoRecebido);
							String nomeRecebido;
							nomeRecebido = dadosRecebidos.readUTF();
							textField_Nome.setText(nomeRecebido);
							String enderecoRecebido;
							enderecoRecebido = dadosRecebidos.readUTF();
							textField_Endereco.setText(enderecoRecebido);
							String telefoneRecebido;
							telefoneRecebido = dadosRecebidos.readUTF();
							textField_Telefone.setText(telefoneRecebido);
							if (nomeRecebido.trim().length() == 0)
							{
								JOptionPane.showMessageDialog(null,"Posto nao cadastrado");
								textField_Pesquisar.setText("");
							}
							
							//String pegaDadoEntrada;
							//pegaDadoEntrada = dadosRecebidos.readUTF(); 
							//JOptionPane.showMessageDialog(null, pegaDadoEntrada);
						
						}catch (IOException ex) {
							JOptionPane.showMessageDialog(null,"Erro "+ ex);
						
						}
				
				
					}
				});//fecha botao pesquisar
				
				
				btnNewButton_Editar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcao = 3; 
						btnNewButton_AdicionarPosto.setEnabled(true);
						btnNewButton_Salvar.setEnabled(true);
						btnNewButton_Editar.setEnabled(true);
						btnNewButton_Cancelar.setEnabled(false);
						btnNewButton_Deletar.setEnabled(true);
						btnNewButton_Sair.setEnabled(true);
						textField_ID.setEnabled(false);
						textField_Nome.setEnabled(true);
						textField_Endereco.setEnabled(true);
						textField_Telefone.setEnabled(true);
						textField_Pesquisar.setEnabled(true);
					
						
						try {
							dadosSaida.writeInt(opcao);
							dadosSaida.writeUTF(textField_Nome.getText());
							dadosSaida.writeUTF(textField_Endereco.getText());
							dadosSaida.writeUTF(textField_Telefone.getText());
							dadosSaida.writeUTF(textField_ID.getText());
							String pegaDadoEntrada;
							pegaDadoEntrada = dadosRecebidos.readUTF(); 
							JOptionPane.showMessageDialog(null, pegaDadoEntrada);
							textField_Nome.setText("");
							textField_Endereco.setText("");
							textField_Telefone.setText("");
							textField_ID.setText("");
							textField_Pesquisar.setText("");
							textField_Nome.setEnabled(true);
							textField_Endereco.setEnabled(true);
							textField_Telefone.setEnabled(true);
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,"Erro na edicao dos dados do posto"+ ex);	
						}
						
					}
				});//fecha botao editar
				
				
				btnNewButton_Deletar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcao = 4;
						btnNewButton_AdicionarPosto.setEnabled(true);
						btnNewButton_Salvar.setEnabled(false);
						btnNewButton_Editar.setEnabled(true);
						btnNewButton_Cancelar.setEnabled(false);
						btnNewButton_Deletar.setEnabled(true);
						btnNewButton_Sair.setEnabled(true);
						textField_ID.setEnabled(true);
						textField_Nome.setEnabled(true);
						textField_Endereco.setEnabled(true);
						textField_Telefone.setEnabled(true);
						textField_Pesquisar.setEnabled(true);
						
						try {
							dadosSaida.writeInt(opcao);
							dadosSaida.writeUTF(textField_Nome.getText());
							dadosSaida.writeUTF(textField_Endereco.getText());
							dadosSaida.writeUTF(textField_Telefone.getText());
							dadosSaida.writeUTF(textField_ID.getText());
							String pegaDadoEntrada;
							pegaDadoEntrada = dadosRecebidos.readUTF(); 
							JOptionPane.showMessageDialog(null, pegaDadoEntrada);
							
							textField_Nome.setText("");
							textField_Endereco.setText("");
							textField_Telefone.setText("");
							textField_ID.setText("");
							textField_Pesquisar.setText("");
							
							
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,"Erro na exclusão dos dados do posto"+ ex);	
						}
						
					}
				});//fecha botao deletar
				
				
				btnNewButton_Sair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcao = 5;
						
						try {
							dadosSaida.writeInt(opcao);
							String pegaDadoEntrada;
							pegaDadoEntrada = dadosRecebidos.readUTF(); 
							JOptionPane.showMessageDialog(null, pegaDadoEntrada);	
							System.exit(0);  
							
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,"Erro na exclusão dos dados do posto"+ ex);	
						}
						
					}
				});//fecha botao sair
				
				
				
			btnNewButton_Listar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					int opcao  = 6;
					try {
						dadosSaida.writeInt(opcao);
						
						ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
						ArrayList<BeansPosto> arrayLista = new ArrayList<>();
						//Object object = entrada.readObject(); 
						try {
						arrayLista = (ArrayList<BeansPosto>) entrada.readObject();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
						} 
				       
						//textArea_Listagem.append(object.toString()); 
						
				       for(int i=0; i<arrayLista.size(); i++) {
				        	textArea_Listagem.append((String)arrayLista.get(i).toString()); 
				      	System.out.println(arrayLista.get(i).toString());
				       }
				       
				  //   for(int i = 0;i<arrayLista.size();i++){   
					//      System.out.println(arrayLista.get(i));  
					//}
				        
				        
					}catch (IOException ex) {
						JOptionPane.showMessageDialog(null,"Erro na listagem dos postos"+ ex);		
					}
				}
			}); //fecha botao listar
				
				
				
			}catch (IOException ex) {
				JOptionPane.showMessageDialog(null,"Erro "+ ex);
			} //fecha catch
		
			
		}//fecha método run	
		
	} //fecha classe cliente
		
	btnIniciarComunicacao.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new cliente().start();
			
		}
	});
	

	
	
		}//fecha método FormClient
	} //fecha classe FormClient

