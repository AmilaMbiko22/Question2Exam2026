import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {
    private final JTextField txtName=new JTextField(15);
    private final JComboBox<String>cmbProduct=new JComboBox<>(new String[]{"Cozy Nights Monarch","Restonic Barbados","Sealy Phuket"});
    private final JTextField txtPrice=new JTextField(15);
    private final JButton btnProcess=new JButton("PROCESS");
    
    public ProductApp(){
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        
        //Menus
        JMenuBar bar = new JMenuBar();
        JMenu file= new JMenu("File");
        JMenuItem exit=new JMenuItem("Exit");
        JMenu edit= new JMenu("Edit");
        JMenuItem process= new JMenuItem("Process");
        JMenuItem clear=new JMenuItem("Clear");
        file.add(exit);
        edit.add(process);
        edit.add(clear);
        bar.add(file);
        bar.add(edit);
        setJMenuBar(bar);
        
        //The form
        JPanel form=new JPanel(new GridLayout(3,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Customer Name:"));
        form.add(txtName);
        form.add(new JLabel("Select Product:"));
        form.add(cmbProduct);
        form.add(new JLabel("Enter Price:"));
        form.add(txtPrice);
        add(form,BorderLayout.CENTER);
        
        JPanel south=new JPanel(new BorderLayout());
        south.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        south.add(btnProcess,BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);
        
        //Different Button actions
        btnProcess.addActionListener(e -> processSale());
        process.addActionListener(e -> processSale());
        clear.addActionListener(e -> clearForm());
        exit.addActionListener(e -> dispose());
        pack();
        setLocationRelativeTo(null);
        
    }
    private void processSale(){
        String name= txtName.getText().trim();
        String product=(String)cmbProduct.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter the customer name.");
            return;
        }
        double price;
        try{
            price=Double.parseDouble(txtPrice.getText().trim());
            if (price <=0)throw new NumberFormatException();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Please enter a valid price.");
            return;
        }
        IProductCalculations calc=new ProductCalculations(price);
        double vat=calc.GetVat();
        double commission= calc.GetCommission();
        double total= calc.GetTotal();
        
        JOptionPane.showMessageDialog(this,"Customer Name: "+name+"\n"+"Product: "+product+"\n"+"VAT:R "+vat+"\n"+"COMMISSION:R "+commission+"\n"+"TOTAL:R"+total);
        saveToFile(name,product,vat,commission,total);
    }
    private void saveToFile(String name,String product,double vat,double commission,double total){
        try(PrintWriter out=new PrintWriter(new FileWriter("data.txt",true))){
            out.println("DATA LOG");
            out.println("*".repeat(60));
            out.println("Customer Name:"+name);
            out.println("Product:"+product);
            out.println("VAT:R"+vat);
            out.println("COMMISSION:R"+commission);
            out.println("TOTAL:R"+total);
            out.println("*".repeat(60));
            out.println();
        }catch (IOException ex){
            JOptionPane.showMessageDialog(this,"Could not write to data.txt"+ex.getMessage());
        }
    }
    private void clearForm(){
        txtName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
        txtName.requestFocus();
    }
}
