package br.com.sge.tela;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sge.bean.AlunoBean;
import br.com.sge.dao.AlunoDao;
import br.com.sge.exception.DaoException;
import br.com.sge.util.FormatarNumero;
import br.com.sge.util.SwingUtil;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaSelecioneAluno extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTable table;
	
	private int telaRequisitante = 0;
	private JButton btAtualizar;

	public static void main(String[] args) {
		try {
			new TelaSelecioneAluno(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TelaSelecioneAluno(int requisicao){
		this.telaRequisitante = requisicao;
		montarComponentes();
		setVisible(true);
	}

	public void montarComponentes() {
		SwingUtil.lookWindows(this);
		setBounds(100, 100, 501, 384);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		{
			tfNome = new JTextField();
			tfNome.setToolTipText("Digite o nome do aluno");
			tfNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					buscar();
				}
			});
			tfNome.setBounds(24, 23, 361, 22);
			contentPanel.add(tfNome);
			tfNome.setColumns(10);
		}
		{
			btAtualizar = new JButton("Atualizar");
			btAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});
			btAtualizar.setBounds(392, 23, 91, 23);
			contentPanel.add(btAtualizar);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 473, 287);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() > 1){
					retornarRequisicao();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"RM", "Nome", "Turma"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(56);
		table.getColumnModel().getColumn(1).setPreferredWidth(211);
		scrollPane.setViewportView(table);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				buscar();
			}
		});
		
		setModal(true);
	}
	
	private void buscar(){
		try{
			List<AlunoBean> lista = new AlunoDao().consultarAluno(tfNome.getText());
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			for(AlunoBean aluno : lista){
				addTable(dtm, aluno);
			}
		}catch(DaoException e){
			e.printStackTrace();
		}
	}
	
	private void addTable(DefaultTableModel dtm, AlunoBean bean){
		Object[] object = new Object[3];
		int i = 0;
		
		object[i++] = FormatarNumero.formatNumero(2, bean.getRmAluno());
		object[i++] = bean.getNmAluno();
//		object[i++] = bean.getTurma().getNmTurma();
		
		dtm.addRow(object);
	}
	
	private void retornarRequisicao(){
		try{
			int row = table.getSelectedRow();
			if(row != -1){
				int rm = Integer.parseInt((String) table.getValueAt(row, 0));
				AlunoBean aluno = new AlunoDao().consultarAlunoRM(rm);
				if(aluno != null){
					if(this.telaRequisitante == 1){
						TelaOcorrencia.recebeRequisicao(aluno);
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "Selecione um registro!", "Atenção", JOptionPane.WARNING_MESSAGE);
			}
			dispose();
		}catch(DaoException e){
			e.printStackTrace();
		}
		
	}
}
