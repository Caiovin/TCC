package br.com.sge.exception;

import java.awt.Component;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class EntradaUsuarioException extends Exception {

	private Component campo;
	
	public Component getCampo() {
		return campo;
	}

	public EntradaUsuarioException(Component campo) {
		this.campo = campo;
	}
	
	public EntradaUsuarioException(Component campo, String message, Throwable cause) {
		super(message, cause);
		this.campo = campo;
	}

	public EntradaUsuarioException(Component campo, String message) {
		super(message);
		this.campo = campo;
		JOptionPane.showMessageDialog(null, message);
	}

	public EntradaUsuarioException(Component campo, Throwable cause) {
		super(cause);
		this.campo = campo;
	}
}
