package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.ClientController;
import exceptions.HostNotFoundException;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<?> list;
	private String arquivoSelecionado;
	DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					frame.setTitle("Share my file");
					String imagePath = "Icon.png";
					InputStream imgStream = TelaPrincipal.class.getResourceAsStream(imagePath);
					BufferedImage myImg = ImageIO.read(imgStream);
					frame.setIconImage(myImg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public TelaPrincipal() throws UnsupportedLookAndFeelException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.setLookAndFeel(new MaterialLookAndFeel(GUITheme.LIGHT_THEME));
		setBounds(100,100, 607, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setBounds(10, 36, 221, 413);
		list.setVisibleRowCount(10);
		list.addListSelectionListener(new SelecaoDePaciente());
		contentPane.add(list);

		MaterialButton addFileBtn = new MaterialButton("Adicionar arquivo");
		addFileBtn.setBounds(282, 253, 250, 58);
		addFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		addFileBtn.addActionListener(new AddFileAction());
		contentPane.setLayout(null);
		contentPane.add(addFileBtn);

		JLabel label = new JLabel("");
		label.setBounds(241, 11, 340, 198);
		label.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/view/Screenshot_3.png")));
		contentPane.add(label);

		MaterialButton searchFileBtn = new MaterialButton("Adicionar arquivo");
		searchFileBtn.setBounds(282, 322, 250, 58);
		searchFileBtn.setText("Baixar");
		searchFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		searchFileBtn.addActionListener(new DownloadAction());
		contentPane.add(searchFileBtn);

		MaterialButton removeFileBtn = new MaterialButton("Adicionar arquivo");
		removeFileBtn.setBounds(282, 391, 250, 58);
		removeFileBtn.setText("Remover");
		removeFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		removeFileBtn.addActionListener(new removeAction());
		contentPane.add(removeFileBtn);
		
		JLabel lblArquivosCompartilhados = new JLabel("Arquivos compartilhados");
		lblArquivosCompartilhados.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblArquivosCompartilhados.setBounds(10, 11, 221, 14);
		contentPane.add(lblArquivosCompartilhados);
	}
	
	private void updateSharedFiles() {
		model.clear();
		String[] allFiles = ClientController.getInstance().getAllFiles();
		for(String file : allFiles)
			model.addElement(file);
	}

	private class AddFileAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			final JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					ClientController.getInstance().addFile(file);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar o arquivo");
					e1.printStackTrace();
					return;
				}
				JOptionPane.showMessageDialog(null, "Arquivo adicionado com sucesso");
			}
			else {
				JOptionPane.showMessageDialog(null, "Você deve escolher um arquivo para compartilhar");
			}
		}

	}
	
	private class SelecaoDePaciente implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if(e.getValueIsAdjusting()) {
				arquivoSelecionado = (String) list.getSelectedValue();
				System.out.println(arquivoSelecionado);
			}
		}

	}
	
	private class DownloadAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				ClientController.getInstance().searchFile(arquivoSelecionado);
			} catch (MalformedURLException | RemoteException | NotBoundException | HostNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private class removeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				ClientController.getInstance().removeFile(arquivoSelecionado);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateSharedFiles();
		}
		
	}
	
	
}
