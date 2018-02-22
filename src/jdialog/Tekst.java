package jdialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tekst extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField naziv;
	private JTextArea vrednost;
	private String naziv_par;
	private String vrednost_par;
	private String tip;
	private boolean zavrseno;
	private JScrollPane scrollPane;
	private boolean prazno=false;
	private Properties jezik;

	public boolean getPrazno(){
		return prazno;
	}
	
	private void submitAction() {
        // You can do some validation here before assign the text to the variable 
        naziv_par=naziv.getText();
		vrednost_par=vrednost.getText();
	}

	/**
	 * Create the dialog.
	 */
	public Tekst(Properties j) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		tip="Tekst";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.5; 
		setBounds(100, 100, (int)width, (int)height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 696, 50, 0};
		gbl_contentPanel.rowHeights = new int[]{50, 50, 50, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setModal(true);
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel(jezik.getProperty("UnesiteNazivParametra"));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			naziv = new JTextField();
			GridBagConstraints gbc_naziv = new GridBagConstraints();
			gbc_naziv.insets = new Insets(0, 0, 5, 5);
			gbc_naziv.fill = GridBagConstraints.HORIZONTAL;
			gbc_naziv.gridx = 1;
			gbc_naziv.gridy = 1;
			contentPanel.add(naziv, gbc_naziv);
			naziv.setColumns(10);
		}
		{
			JLabel lblUnesiteVrednostParametra = new JLabel(jezik.getProperty("UnesiteVrednostParametra"));
			GridBagConstraints gbc_lblUnesiteVrednostParametra = new GridBagConstraints();
			gbc_lblUnesiteVrednostParametra.insets = new Insets(0, 0, 5, 5);
			gbc_lblUnesiteVrednostParametra.gridx = 1;
			gbc_lblUnesiteVrednostParametra.gridy = 2;
			contentPanel.add(lblUnesiteVrednostParametra, gbc_lblUnesiteVrednostParametra);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				vrednost = new JTextArea();
				vrednost.setWrapStyleWord(true);
				vrednost.setLineWrap(true);
				scrollPane.setViewportView(vrednost);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(jezik.getProperty("Sacuvaj"));
				okButton.setActionCommand("Sacuvaj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submitAction();
						zavrseno=true;
			      		 if (naziv_par.trim().equals("") && vrednost_par.trim().equals("")){
			  				JOptionPane.showMessageDialog(null,jezik.getProperty("NistePopuniliVrednostiParametara"));
			  				prazno=true;
			  				return;
			  			}
						dispose();
					}
				});
				
				
				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
			        	vrednost_par="";
						naziv_par="";
						prazno=true;
						zavrseno=false;
						dispose();
			        }
			});
				
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(jezik.getProperty("Izadji"));
				cancelButton.setActionCommand("Izlazak");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						vrednost_par="";
						naziv_par="";
						prazno=true;
						zavrseno=false;
						dispose();
				        
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNaziv(){
		return naziv_par;
	}
	
	public String getVrednost(){
		return vrednost_par;
	}
	public String getTip(){
		
		return tip;
	}
	
	public boolean getUspesno() {
		return zavrseno;
	}
	
	public boolean getCheck(){
		
		return false;
	}


}
