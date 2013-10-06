package br.com.sge.tela;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.sge.util.SwingUtil;
import br.com.sge.util.Wallpaper;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		SwingUtil.lookWindows(this);//essa linha muda a personalização da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 475);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmMatricula = new JMenuItem("Matricula");
		mnCadastros.add(mntmMatricula);
		
		JMenuItem mntmProfessor = new JMenuItem("Professor");
		mnCadastros.add(mntmProfessor);
		
		JMenu mnAcademico = new JMenu("Academico");
		menuBar.add(mnAcademico);
		
		JMenuItem mntmCalendrioDeAula = new JMenuItem("Calend\u00E1rio de Aula");
		mnAcademico.add(mntmCalendrioDeAula);
		
		JMenuItem mntmBoletim = new JMenuItem("Boletim");
		mnAcademico.add(mntmBoletim);
		
		JMenuItem mntmOcorrncias = new JMenuItem("Ocorr\u00EAncias");
		mnAcademico.add(mntmOcorrncias);
		
		JMenuItem mntmGradeAcadmica = new JMenuItem("Grade Acad\u00EAmica");
		mnAcademico.add(mntmGradeAcadmica);
		
		JMenu mnTurmas = new JMenu("Turmas");
		mnAcademico.add(mnTurmas);
		
		JMenuItem mntmIncluir = new JMenuItem("Incluir");
		mnTurmas.add(mntmIncluir);
		
		JMenuItem mntmPesquisaralterar = new JMenuItem("Pesquisar/Alterar");
		mnTurmas.add(mntmPesquisaralterar);
		
		JMenu mnAtividade = new JMenu("Atividades");
		menuBar.add(mnAtividade);
		
		JMenuItem mntmIncluirAtividade = new JMenuItem("Incluir Atividade");
		mnAtividade.add(mntmIncluirAtividade);
		
		JMenuItem mntmAplicarCorreo = new JMenuItem("Aplicar Corre\u00E7\u00E3o");
		mnAtividade.add(mntmAplicarCorreo);
		
		JMenu mnProfessor = new JMenu("Professor");
		menuBar.add(mnProfessor);
		
		JMenuItem mntmVisualizarCalendario = new JMenuItem("Visualizar Calendario");
		mnProfessor.add(mntmVisualizarCalendario);
		
		JMenuItem mntmRealizarChamada = new JMenuItem("Realizar Chamada");
		mnProfessor.add(mntmRealizarChamada);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		JMenuItem mntmSeguranaEUsurios = new JMenuItem("Seguran\u00E7a e Usu\u00E1rios");
		mnOpes.add(mntmSeguranaEUsurios);
		
		JSeparator separator = new JSeparator();
		mnOpes.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnOpes.add(mntmSair);
		contentPane = new Wallpaper();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0 };
		gbl_contentPane.rowHeights = new int[] { 0 };
		gbl_contentPane.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
