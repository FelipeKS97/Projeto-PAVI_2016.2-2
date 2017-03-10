package pavi.melhoramigo.bo;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

import pavi.melhoramigo.dao.UsuarioDAO;

public class CadastroUsuarioBO {
	public boolean isCPF (String cpf) {
		Pattern PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
		Pattern PATTERN_NUMBERS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");
		
		if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
			
			cpf = cpf.replaceAll("-|\\.", "");
			
			if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
				// considera-se erro CPF's formados por uma sequencia de numeros iguais
				if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
						|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
						|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
						|| cpf.equals("99999999999") || (cpf.length() != 11))
					return (false);
		
				char dig10, dig11;
				int sm, i, r, num, peso;
		
				// "try" - protege o codigo para eventuais erros de conversao de tipo
				// (int)
				try {
					// Calculo do 1o. Digito Verificador
					sm = 0;
					peso = 10;
					for (i = 0; i < 9; i++) {
						// converte o i-esimo caractere do CPF em um numero:
						// por exemplo, transforma o caractere '0' no inteiro 0
						// (48 eh a posicao de '0' na tabela ASCII)
						num = (int) (cpf.charAt(i) - 48);
						sm = sm + (num * peso);
						peso = peso - 1;
					}
		
					r = 11 - (sm % 11);
					if ((r == 10) || (r == 11))
						dig10 = '0';
					else
						dig10 = (char) (r + 48); // converte no respectivo caractere
													// numerico
		
					// Calculo do 2o. Digito Verificador
					sm = 0;
					peso = 11;
					for (i = 0; i < 10; i++) {
						num = (int) (cpf.charAt(i) - 48);
						sm = sm + (num * peso);
						peso = peso - 1;
					}
		
					r = 11 - (sm % 11);
					if ((r == 10) || (r == 11))
						dig11 = '0';
					else
						dig11 = (char) (r + 48);
		
					// Verifica se os digitos calculados conferem com os digitos
					// informados.
					if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
						return (true);
					else
						return (false);
				} catch (InputMismatchException erro) {
					return (false);
				}
			}
			
			return false;
		}
		
		return false;
	}

	public boolean verificaIdade (String idadeStr) {
		try {
			int idadeInt = Integer.parseInt(idadeStr);

			if (idadeInt >= 18)
				return true;

			return false;
		} catch (NumberFormatException erro) {
			return false;
		}
	}

	public boolean isCEP (String cepStr) {
		Pattern PATTERN_GENERIC = Pattern.compile("[0-9]{5}\\-?[0-9]{3}");
		
		if (cepStr != null && PATTERN_GENERIC.matcher(cepStr).matches()) {		
			cepStr = cepStr.replaceAll("-|", "");
			
			try {
				Integer.parseInt(cepStr);
				
				if (cepStr.length() == 8)
					return true;
				
				return false;
			} catch (NumberFormatException erro) {
				return false;
			}
		}
		
		return false;
	}

	public boolean verificaEmail (Connection conexao, String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.buscaUsuario(conexao, email) == null)
			return true;

		return false;
	}
	
	public boolean isSenha (String senha) {
		if (senha.length() >= 6)
			return true;

		return false;
	}
	
	public boolean verificaConfirmaSenha (String senha1, String senha2) {
		if (senha1.equals(senha2))
			return true;
		
		return false;
	}
	
	public String formataCPF (String cpf) {
		return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-"
				+ cpf.substring(9, 11));
	}
	
	public String formataCEP (String cep) {
		return (cep.substring(0, 5) + "-" + cep.substring(5, 8));
	}
}
