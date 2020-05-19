package serverposto;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;


public class PegaPosto {
	
	private ConexaoBD conexPosto;

	public PegaPosto (ConexaoBD conexPosto) {
		
		this.conexPosto = conexPosto;
		
	}

		public ArrayList buscarPostos() {
			System.out.println("TESTE TESTE TESTE");
			ArrayList <BeansPosto> arrayListaPosto = new ArrayList<BeansPosto>();
			String sql = "Select * from novoposto";
			conexPosto.executaSql(sql); 
			try {
				while (conexPosto.rs.next())
				{
					BeansPosto novoPosto = new BeansPosto(conexPosto.rs.getInt(1), conexPosto.rs.getString(2), conexPosto.rs.getString(3), conexPosto.rs.getString(4));
					arrayListaPosto.add(novoPosto);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			 //return arrayListaPosto;
			 // TESTE PARA VERIFICAR SE ESTÁ CRIANDO LISTA COM OS POSTOS BD - OK ESTÁ
			//for(int i = 0;i<arrayListaPosto.size();i++){   
	      // System.out.println(arrayListaPosto.get(i));  
			//}
				
			 
			return arrayListaPosto;
			}//fecha public List
			
	}//fecha class PegaPosto
	 

		


