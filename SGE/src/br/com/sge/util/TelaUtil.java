package br.com.sge.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.jdesktop.swingx.JXDatePicker;

import br.com.sge.exception.EntradaUsuarioException;

import com.toedter.calendar.JDateChooser;


public class TelaUtil {
	
	public static String getSenha(JPasswordField pfSenha, JPasswordField pfConfirmacaoSenha) throws EntradaUsuarioException{
		String senha = String.copyValueOf(pfSenha.getPassword()).trim();
		String confirmacao = String.copyValueOf(pfConfirmacaoSenha.getPassword()).trim();
		if(senha.equals("") || "".equals(senha)){
			throw new EntradaUsuarioException(pfSenha, "As senhas não conferem!");
		}else{
			if(senha.equals(confirmacao) && confirmacao.equals(senha)){
				return senha;
			}else{
				throw new EntradaUsuarioException(pfSenha, "As senhas não conferem!");
			}
		}
	}
	
	public static String getUsuario(JTextField tfUser) throws EntradaUsuarioException{
		String valor = tfUser.getText().trim();
		if(valor.equals("") || "".equals(valor)){
			throw new EntradaUsuarioException(tfUser, "Campo " + tfUser.getName() + " Obrigatï¿½rio!");
		}else{
			//colocar dao login
			
			return valor;
		}
	}

	public static String getCpf(JFormattedTextField ftCpf, boolean obrigatorio) throws EntradaUsuarioException{
		String cpf = ftCpf.getText().replace(".", "").replace("-", "").trim();
		if(obrigatorio == true){
			if(cpf.equals("") || "".equals(cpf)){
				throw new EntradaUsuarioException(ftCpf, "Valor invï¿½lido no campo " + ftCpf.getName());
			} else{
				return cpf;
			}
		}else{
			return cpf;
		}
	}
	
	public static String getValueCheck(JCheckBox ck){
		if(ck.isSelected()){
			return "S";
		}else{
			return "N";
		}
	}
	
	public static String getCep(JFormattedTextField ftCep, boolean obrigatorio) throws EntradaUsuarioException{
		String valor = ftCep.getText().replace("-", "");
		if(obrigatorio == true){
			if(valor.equals("") || "".equals(valor)){
				throw new EntradaUsuarioException(ftCep, "Campo " + ftCep.getName() + " Obrigatï¿½rio!");
			}else{
				return valor;
			}
		}else{
			return valor;
		}
	}
	
	public static int getIndexCombo(JComboBox<String> combo, boolean obrigatorio) throws EntradaUsuarioException{
		int valor = combo.getSelectedIndex();
		if(obrigatorio == true){
			if(valor < 1){
				throw new EntradaUsuarioException(combo, "Campo " + combo.getName() + " Obrigatï¿½rio!");
			}else{
				return valor;
			}
		}else{
			return valor;
		}
	}
	
	public static String getTelefone(JFormattedTextField ftTelefone, boolean obrigatorio) throws EntradaUsuarioException{
		String valor = ftTelefone.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
		if(obrigatorio == true){
			if(valor.equals("") || "".equals("")){
				throw new EntradaUsuarioException(ftTelefone, "Campo " + ftTelefone.getName() + " Obrigatï¿½rio!");
			}else{
				return valor;
			}
		}else {
			return valor;
		}
	}
	
	public static String getCelular(JFormattedTextField ftCelular, boolean obrigatorio) throws EntradaUsuarioException{
		String valor = ftCelular.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
		if(obrigatorio == true){
			if(valor.equals("") || "".equals(valor)){
				throw new EntradaUsuarioException(ftCelular, "Campo " + ftCelular.getName() + " Obrigatï¿½rio!");
			}else{
				return valor;
			}
		}else {
			return valor;
		}
	}
	
	public static String getCharSexo(JRadioButton rbMasculino) throws EntradaUsuarioException{
		if((rbMasculino != null) && rbMasculino.isSelected()){
			return "M";
		} else {
			return "F";
		}
	}
	
	public static Date getDateFromDatePicker(JXDatePicker dtData, boolean obrigatorio) throws EntradaUsuarioException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = dtData.getEditor().getText();
		if(obrigatorio == true){
			if(data.equals("") || "".equals(data)){
				throw new EntradaUsuarioException(dtData, "Campo " + dtData.getName() + " ObrigatÃ³rio");
			}else{
				return sdf.parse(data);
			}
		}else {
			return sdf.parse(data);
		}
	}
	
	public static Date getDateFromDateChooser(JDateChooser dtData, boolean obrigatorio) throws EntradaUsuarioException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(dtData.getDate());
		if(obrigatorio == true){
			if(data.equals("") || "".equals(data)){
				throw new EntradaUsuarioException(dtData, "Campo " + dtData.getName() + " Obrigatï¿½rio");
			}else{
				return sdf.parse(data);
			}
		}else {
			return sdf.parse(data);
		}
	}
	
	public static void centralizar(Window window) {
		Dimension windowDim = Toolkit.getDefaultToolkit().getScreenSize();
		int y = ((int) windowDim.getHeight() / 2) - window.getHeight() / 2;
		int x = ((int) windowDim.getWidth() / 2) - window.getWidth() / 2;
		window.setLocation(x, y);
	}
	
	public static String dateToString(JXDatePicker dtData) throws EntradaUsuarioException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dtData.getEditor().getText().equals("")){
			throw new EntradaUsuarioException(dtData, "Campo " + dtData.getName() + " obrigatório");
		}else{
			return dtData.getDate() == null ? "" : sdf.format(dtData.getDate());
		}
	}

	
	public static String formataDinheiro(double valor) {
		return "R$ " + String.format("%.2f", valor);
	}
	
	public static String formataDinheiro(String valor) {
		return "R$ " + String.format("%.2f", valor);
	}
	
	public static Date getDateFromTextField(JFormattedTextField text) throws EntradaUsuarioException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String dataText = text.getText();
		Date date = null;
		if (!"__/__/____".equals(dataText)) {
			try {
				date = sdf.parse(dataText);
			} catch (Exception e) {
				throw new EntradaUsuarioException(text, "Valor invï¿½lido no campo " + text.getName());
			}
		}
		return date;
	}
	
	public static String getEmail(JTextField txtEmail) throws EntradaUsuarioException {
		String email = txtEmail.getText();
		email = email.trim();
		if (!("".equals(email)) || (email.equals(""))) {
			if (!email.matches(".+@.+\\.[a-z]+")) {
				throw new EntradaUsuarioException(txtEmail, "Email invï¿½lido");
			}
		}
		return email;
	}
	
	public static String getMoneyValue(double valor) {
		return String.format("%.2f", valor);
	}
	
	public static boolean valida(JFormattedTextField cpf){
		String param = cpf.getText().replace("-", "").replace(".", "");
		boolean ret = false;
		if(ValidaCPF.isCPF(param) == false){
			JOptionPane.showMessageDialog(null, "O CPF digitado (" + cpf.getText() + ") ï¿½ invalido, favor tente novamente!");
		} else{
			ret = true;
		}
		return ret;
	}
	
	public static double getCampoObrigatorioDouble(JTextField campoObrigatorio) throws EntradaUsuarioException {
		String valor = getCampoObrigatorio(campoObrigatorio, true);
		valor = valor.replace(".", ""); 
		valor = valor.replace(",", "."); 
		valor = valor.replace("R$", "");
		return Double.parseDouble(valor.trim());
	}
        
        public static float getCampoObrigatorioFloat(JTextField campoObrigatorio) throws EntradaUsuarioException {
		String valor = getCampoObrigatorio(campoObrigatorio, true);
		valor = valor.replace(".", ""); 
		valor = valor.replace(",", "."); 
		return Float.parseFloat(valor);
	}
	
	public static int getCampoObrigatorioCombo(JComboBox<String> combo) throws EntradaUsuarioException{
		int valor = combo.getSelectedIndex();
		if(valor == 0){
			throw new EntradaUsuarioException(combo, "Campo " + combo.getName() + " obrigatï¿½rio");
		}
		return valor;
	}
	
	public static String getCampoObrigatorio(JTextField campoObrigatorio, boolean obrigatorio) throws EntradaUsuarioException {
		String valor = campoObrigatorio.getText();
		if(obrigatorio == true){
			if (valor == null || "".equals(valor.trim())) {
				throw new EntradaUsuarioException(campoObrigatorio, "Campo " + campoObrigatorio.getName() + " obrigatï¿½rio");
			}
			return valor.trim();
		}else{
			return valor;
		}
	}
	
	public static String getCompararSenhas(JPasswordField pfSenha, JPasswordField pfConfirmacaoSenha) throws EntradaUsuarioException{
		String senha = String.copyValueOf(pfSenha.getPassword());
		String confirmacao = String.copyValueOf(pfConfirmacaoSenha.getPassword());
		if((!senha.equals(confirmacao)) || "".equals(senha.trim()) || senha == null || "".equals(confirmacao.trim()) || confirmacao == null){
			throw new EntradaUsuarioException(pfSenha, "Campo " + pfSenha.getName() + " não foi preenchido,\n ou esta incorreto! Tente novamente!");
		}
		
		return senha.trim();
	}
	
	public static String getCampoObrigatorio(JTextPane campoObrigatorio) throws EntradaUsuarioException {
		String valor = campoObrigatorio.getText();
		if (valor == null || "".equals(valor.trim())) {
			throw new EntradaUsuarioException(campoObrigatorio, "Campo " + campoObrigatorio.getName() + " obrigatï¿½rio");
		}
		return valor.trim();
	}
	
	public static String getCampoObrigatorio(JEditorPane campoObrigatorio, boolean obrigatorio) throws EntradaUsuarioException {
		String valor = campoObrigatorio.getText();
		if(obrigatorio == true){
			if (valor == null || "".equals(valor.trim())) {
				throw new EntradaUsuarioException(campoObrigatorio, "Campo " + campoObrigatorio.getName() + " obrigatï¿½rio");
			}
			return valor.trim();
		}else{
			return valor.trim();
		}
	}
	
	public static byte[] convertByte(File dir) throws EntradaUsuarioException{
		byte[] arrayAux = null;
		
		try {
			@SuppressWarnings("resource")
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dir));
			arrayAux = new byte[bis.available()];
			bis.read(arrayAux);

		} catch (IOException e) {
			throw new EntradaUsuarioException(null, "não foi possï¿½vel ler a imagem da foto", e);
		}
		
		return arrayAux;
	}
        
        public static FileInputStream getFile(Component comp, JTextField field) throws FileNotFoundException, EntradaUsuarioException{ 
            
            JFileChooser telaEscolheFoto = new JFileChooser("C:\\");
		telaEscolheFoto.setFileFilter(new FiltroExtensao("pdf", "txt"));
		
		telaEscolheFoto.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int ret = telaEscolheFoto.showSaveDialog(comp);
		FileInputStream fileIn = null;
                
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = telaEscolheFoto.getSelectedFile();
			field.setText(file.getAbsolutePath());
			try {
                                fileIn = new FileInputStream(file);
			} catch (IOException e) {
				throw new EntradaUsuarioException(field, "não foi possï¿½vel ler a imagem da foto", e);
			}
                        
		}
                return fileIn;

        }
	
	public static byte[] showTelaEscolheImage(
			File diretorioOrigem, 
			ImagePanel previewPanel,
			JTextField txtCaminhoFoto,
			Component telaPai,
			String ... extensoes) throws EntradaUsuarioException {
		JFileChooser telaEscolheFoto = new JFileChooser(diretorioOrigem);
		telaEscolheFoto.setFileFilter(new FiltroExtensao(extensoes));
		
		telaEscolheFoto.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		byte[] arrayAux = null;
		
		int ret = telaEscolheFoto.showSaveDialog(telaPai);
		
		if (ret == JFileChooser.APPROVE_OPTION) {
			File fileFoto = telaEscolheFoto.getSelectedFile();
			txtCaminhoFoto.setText(fileFoto.getAbsolutePath());
			try {
				@SuppressWarnings("resource")
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFoto));
				arrayAux = new byte[bis.available()];
				bis.read(arrayAux);

				previewPanel.setImagem(arrayAux);
				previewPanel.repaint();
			} catch (IOException e) {
				throw new EntradaUsuarioException(txtCaminhoFoto, "não foi possivel ler a imagem da foto", e);
			}
		}
		return arrayAux;
	}

}
