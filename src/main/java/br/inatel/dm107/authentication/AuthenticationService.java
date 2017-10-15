package br.inatel.dm107.authentication;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import br.inatel.dm107.dao.UsuarioDao;

public class AuthenticationService {
	
	private UsuarioDao usuarioDao = new UsuarioDao();

	public boolean authenticate(String credentials) {
		if (Objects.isNull(credentials)) {
			return false;
		}
	
		final String encodedUsuarioSenha = credentials.replaceFirst("Basic ", "");
		
		String usernameAndPassword = null;
		
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUsuarioSenha);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final String usernameAndPassSplit[] = usernameAndPassword.split(":");
		final String usuario = usernameAndPassSplit[0];
		final String senha = usernameAndPassSplit[1];
	

		boolean authenticationStatus = usuarioDao.userExists(usuario, senha);
		
		return authenticationStatus;
}
}
