package br.com.sge.tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import br.com.sge.bean.AlunoBean;
import br.com.sge.bean.Ocorrencia;
import br.com.sge.dao.OcorrenciaDao;
import br.com.sge.exception.DaoException;
import br.com.sge.exception.EntradaUsuarioException;
import br.com.sge.util.FormatarNumero;
import br.com.sge.util.SwingUtil;
import br.com.sge.util.TelaUtil;
import javax.swing.ImageIcon;

public class TelaOcorrencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField tfRM;
	private static JTextField tfNome;
	private static JTextField tfTurma;
	private static JTextField tfResponsavel;
	private JXDatePicker dtOcorrencia;
	private JButton btPesquisar;
	private JButton btSalvar;
	private JButton btLimpar;
	private JButton btCancelar;
	
	private static AlunoBean aluno;
	private JEditorPane epDescricao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaOcorrencia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaOcorrencia() {
		SwingUtil.lookWindows(this);
		setTitle("Ocorr\u00EAncia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 28, 634, 116);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRm = new JLabel("RM:");
		lblRm.setBounds(27, 27, 19, 14);
		panel.add(lblRm);
		
		tfRM = new JTextField();
		tfRM.setEditable(false);
		tfRM.setBounds(66, 24, 114, 20);
		panel.add(tfRM);
		tfRM.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(224, 27, 31, 14);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setBounds(277, 24, 331, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblTurma = new JLabel("Turma:");
		lblTurma.setBounds(12, 55, 34, 14);
		panel.add(lblTurma);
		
		tfTurma = new JTextField();
		tfTurma.setEditable(false);
		tfTurma.setBounds(66, 52, 114, 20);
		panel.add(tfTurma);
		tfTurma.setColumns(10);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel:");
		lblResponsvel.setBounds(190, 55, 65, 14);
		panel.add(lblResponsvel);
		
		tfResponsavel = new JTextField();
		tfResponsavel.setEditable(false);
		tfResponsavel.setColumns(10);
		tfResponsavel.setBounds(277, 52, 331, 20);
		panel.add(tfResponsavel);
		
		btPesquisar = new JButton("Pesquisar");
		btPesquisar.setIcon(new ImageIcon(TelaOcorrencia.class.getResource("/br/com/sge/image/search20.png")));
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaSelecioneAluno(1);
			}
		});
		btPesquisar.setBounds(503, 80, 105, 27);
		panel.add(btPesquisar);
		
		JLabel lblDataDeOcorrncia = new JLabel("Data de Ocorr\u00EAncia:");
		lblDataDeOcorrncia.setBounds(38, 173, 104, 14);
		contentPane.add(lblDataDeOcorrncia);
		
		dtOcorrencia = new JXDatePicker();
		dtOcorrencia.setName("Data Ocorrencia");
		dtOcorrencia.setFormats(new String[] {"dd/MM/yyyy"});
		dtOcorrencia.setBounds(152, 169, 104, 22);
		contentPane.add(dtOcorrencia);
		
		JLabel lblDescrioDaOcorrncia = new JLabel("Descri\u00E7\u00E3o da Ocorr\u00EAncia:");
		lblDescrioDaOcorrncia.setBounds(38, 210, 120, 14);
		contentPane.add(lblDescrioDaOcorrncia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 228, 634, 171);
		contentPane.add(scrollPane);
		
		epDescricao = new JEditorPane();
		epDescricao.setName("Descri\u00E7\u00E3o da Ocorr\u00EAncia");
		scrollPane.setViewportView(epDescricao);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaOcorrencia.class.getResource("/br/com/sge/image/button22.png")));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setBounds(551, 421, 108, 27);
		contentPane.add(btCancelar);
		
		btLimpar = new JButton("Limpar Formul\u00E1rio");
		btLimpar.setIcon(new ImageIcon(TelaOcorrencia.class.getResource("/br/com/sge/image/clean22.png")));
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}

		});
		btLimpar.setBounds(391, 421, 150, 27);
		contentPane.add(btLimpar);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setIcon(new ImageIcon(TelaOcorrencia.class.getResource("/br/com/sge/image/save20.png")));
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		btSalvar.setBounds(290, 421, 91, 27);
		contentPane.add(btSalvar);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void recebeRequisicao(AlunoBean bean){
		aluno = bean;
		tfRM.setText(FormatarNumero.formatNumero(2, bean.getRmAluno()));
		tfNome.setText(bean.getNmAluno());
//		tfResponsavel.setText(bean.);
//		tfTurma.setText(bean.getTurma().getNmTurma());
	}
	
	private void limparFormulario() {
		aluno = null;
		tfRM.setText("");
		tfNome.setText("");
		tfResponsavel.setText("");
		tfTurma.setText("");
		dtOcorrencia.setDate(new Date());
		epDescricao.setText("");
	}
	
	private Ocorrencia getBean() throws EntradaUsuarioException, ParseException{
		Ocorrencia bean = new Ocorrencia();
		
		bean.setDescOcorrencia(TelaUtil.getCampoObrigatorio(epDescricao, true));
		bean.setDtOcorrencia(TelaUtil.getDateFromDatePicker(dtOcorrencia, true));
		if(this.aluno != null){
			bean.setRmAluno(aluno.getRmAluno());
		}else{
			throw new EntradaUsuarioException(tfRM, "Por favor selecione um aluno!");
		}
		
		return bean;
	}
	
	private void inserir(){
		try{
			Ocorrencia ocorrencia = getBean();
			OcorrenciaDao dao = new OcorrenciaDao();
			
			dao.inserirOcorrencia(ocorrencia);
			limparFormulario();
			JOptionPane.showMessageDialog(this, "Ocorrencia realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}catch(DaoException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
