package br.com.exercicio.utils;
/**
 *Classe que contem metodos 'utilitarios' que podem ser utilizados por diversas classes do sistema.
 */

public class Utils {

	
	/**
	 * Traduz para o portugues(pt_BR) um valor int.
	 * @param valor Um valor int.
	 * @return Retona a String <b>Ativa</b> para int <code>numero 1</code> e <b>Inativa</b> para o valor <code>numero 0</code>.
	 */
	
	// Medoto para converte numero em boolean trazido do banco mais não consegue fazer 
	/*public static String traduzBoolean(boolean valor){
		
		if(valor == true)
			return "Ativa";
		else {
			return "Inativa";
		}
		
	}*/
	
	
	public static String traduzBoolean(int valor){
		
		if(valor == 1)
			return "Ativa";
		else {
			return "Inativa";
		}
		
			
	
	}
	
	
	
	
	
	
	
	
}
