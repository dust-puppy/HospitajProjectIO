package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projekt.HospitalPharmacy;
import projekt.Product;
import projekt.ProductQuantity;
import projekt.StorageMainPanel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class OrderFromStorageDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox comboBox;

	public OrderFromStorageDialog(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy, StorageMainPanel storageMainPanel) {
		setVisible(true);
		hospitalPharmacyWindow.setVisible(false);
		setBounds(100, 100, 450, 217);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblWybierzLek = new JLabel("Wybierz lek:");
		lblWybierzLek.setBounds(10, 11, 112, 14);
		contentPanel.add(lblWybierzLek);
		
		comboBox = new JComboBox();
		
		String przykładoweNazwyLekow[]= new String[hospitalPharmacy.medicineList.size()];
		for(int i=0; i<hospitalPharmacy.medicineList.size();i++)
		{
			przykładoweNazwyLekow[i]=hospitalPharmacy.medicineList.get(i).name;
		}
		comboBox = new JComboBox(przykładoweNazwyLekow);
		comboBox.setBounds(10, 36, 414, 20);
		contentPanel.add(comboBox);
		
		JLabel lblWybierzIlo = new JLabel("Wybierz ilo\u015B\u0107:");
		lblWybierzIlo.setBounds(10, 82, 122, 14);
		contentPanel.add(lblWybierzIlo);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(10, 107, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Product product = new Product(1,comboBox.getSelectedItem().toString(),"producebt", new Date());
						//Product product= new Product(1,"lek","prod",new Date());
						int amount = Integer.parseInt(textField.getText());
						ProductQuantity pq=new ProductQuantity(product, amount);
					
						storageMainPanel.orderFromOutside(pq, "Apteka");
						hospitalPharmacyWindow.setVisible(true);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Wyjd");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hospitalPharmacyWindow.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Wyjd");
				buttonPane.add(cancelButton);
			}
		}
	}
}
