package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import control.ClientController;

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
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTextField removeTextField;

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
		setBounds(100,100, 560, 499);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		MaterialButton addFileBtn = new MaterialButton("Adicionar arquivo");
		addFileBtn.setBounds(155, 253, 250, 58);
		addFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		addFileBtn.addActionListener(new AddFileAction());
		contentPane.setLayout(null);
		contentPane.add(addFileBtn);

		JLabel label = new JLabel("");
		label.setBounds(110, 30, 340, 198);
		label.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/view/Screenshot_3.png")));
		contentPane.add(label);

		MaterialButton searchFileBtn = new MaterialButton("Adicionar arquivo");
		searchFileBtn.setBounds(20, 322, 147, 58);
		searchFileBtn.setText("Procurar");
		searchFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		searchFileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClientController.getInstance().searchFile(searchTextField.getText());
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(searchFileBtn);

		searchTextField = new JTextField();
		searchTextField.setBounds(180, 342, 310, 20);
		searchTextField.setFont(new Font("Roboto Light", Font.PLAIN, 11));
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		MaterialButton removeFileBtn = new MaterialButton("Adicionar arquivo");
		removeFileBtn.setBounds(20, 391, 147, 58);
		removeFileBtn.setText("Remover");
		removeFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		removeFileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClientController.getInstance().removeFile(removeTextField.getText());
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(removeFileBtn);

		removeTextField = new JTextField();
		removeTextField.setBounds(180, 411, 310, 20);
		removeTextField.setFont(new Font("Roboto Thin", Font.PLAIN, 11));
		removeTextField.setColumns(10);
		contentPane.add(removeTextField);
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
	
	

}
