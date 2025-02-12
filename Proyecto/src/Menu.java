import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private JButton buttonCodigo;
    private JButton buttonvolver;
    private JLabel merca;
    private JLabel mosca;
    private JLabel caballero;
    private JTable table1;
    private DefaultTableModel model;
    private JPanel base_tabla;
    private JScrollPane scrollPane;



    public Menu(){
        JFrame menu=new JFrame("Menu");
        menu.setSize(400,400);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JLabel background=new JLabel(new ImageIcon("src/fondos/zelda.jpg"));
        background.setLayout(new BorderLayout());

        base_tabla=new JPanel();
        base_tabla.setOpaque(false);
        base_tabla.setLayout(new BorderLayout());

        mosca=new JLabel(new ImageIcon("src/images/mosca.jpg"));
        mosca.setHorizontalAlignment(SwingConstants.CENTER);

        caballero=new JLabel(new ImageIcon("src/images/peluche.jpg"));
        caballero.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel =new JPanel();
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new GridLayout(1,2));
        imagePanel.add(mosca);
        imagePanel.add(caballero);

        base_tabla.add(imagePanel,BorderLayout.NORTH);


        model=new DefaultTableModel(new String[]{"ID","Producto","Cantidad"},0);
        table1 =new JTable(model);
        scrollPane=new JScrollPane(table1);
        base_tabla.add(scrollPane, BorderLayout.CENTER);


        JPanel buttonpanel=new JPanel();
        buttonpanel.setOpaque(false);
        buttonCodigo=new JButton("Codigo/Premios");
        buttonvolver=new JButton("Volver");
        buttonpanel.add(buttonCodigo);
        buttonpanel.add(buttonvolver);

        buttonCodigo.setForeground(Color.BLACK);
        buttonCodigo.setBackground(Color.GREEN);

        buttonvolver.setForeground(Color.BLACK);
        buttonvolver.setBackground(Color.RED);

        background.add(base_tabla,BorderLayout.CENTER);
        background.add(buttonpanel, BorderLayout.SOUTH);
        menu.setContentPane(background);
        menu.setVisible(true);

        cargarDatons();

        buttonCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Codigo();
                menu.dispose();
            }
        });
        buttonvolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                menu.dispose();
            }
        });
    }


    private void cargarDatons(){
        try (Connection conexion = BD.conectar();){
            PreparedStatement statement= conexion.prepareStatement("SELECT * FROM merch");
            ResultSet resultSet = statement.executeQuery();
                model.setRowCount(0);
                while (resultSet.next()){
                    int ID = resultSet.getInt("ID");
                    String Nombre=resultSet.getString("Producto");
                    int Existencias=resultSet.getInt("Cantidad");
                    model.addRow(new Object[]{ID,Nombre,Existencias});
                }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en la base de datos");
        }
    }
 }
