package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JSeparator;

public class TelaPrincipal extends JFrame {

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
		setBounds(100, 100, 561, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		MaterialButton addFileBtn = new MaterialButton("Adicionar arquivo");
		addFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		addFileBtn.addActionListener(new AddFileAction());
		addFileBtn.setBounds(164, 253, 256, 58);
		contentPane.add(addFileBtn);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/view/Screenshot_3.png")));
		label.setBounds(110, 29, 310, 198);
		contentPane.add(label);

		MaterialButton searchFileBtn = new MaterialButton("Adicionar arquivo");
		searchFileBtn.setText("Procurar");
		searchFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		searchFileBtn.setBounds(57, 322, 97, 58);
		contentPane.add(searchFileBtn);

		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Roboto Light", Font.PLAIN, 11));
		searchTextField.setBounds(164, 342, 256, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		MaterialButton removeFileBtn = new MaterialButton("Adicionar arquivo");
		removeFileBtn.setText("Remover");
		removeFileBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		removeFileBtn.setBounds(57, 391, 97, 58);
		contentPane.add(removeFileBtn);

		removeTextField = new JTextField();
		removeTextField.setFont(new Font("Roboto Thin", Font.PLAIN, 11));
		removeTextField.setColumns(10);
		removeTextField.setBounds(164, 411, 256, 20);
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
				System.out.println(file.getPath());
			}
			else {
				JOptionPane.showMessageDialog(null, "Você deve escolher um arquivo para compartilhar");
			}
		}

	}

}
