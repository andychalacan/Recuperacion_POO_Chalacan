import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JTextField user;
    private JPasswordField pass;
    private JButton ingresarButton;
    private JButton salirButton;
    private JLabel txr_user;
    private JLabel txt_pass;
    private JPanel log;

    public Login() {
        JFrame login=new JFrame("Login");
        login.setSize(400,400);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);

        log=new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon =new ImageIcon("src/fondos/hollow.jpg");
                if (imageIcon.getIconWidth()==-1){
                    System.out.println("ERROR falla de ruta imagen");
                }else {
                    System.out.println("Imagen Cargado con exito");

                Image image =imageIcon.getImage();
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
                }
            }
        };

        log.setLayout(null);

        txr_user=new JLabel("Usuario");
        txr_user.setForeground(Color.GREEN);
        txr_user.setFont(new Font("Arial",Font.BOLD,17));

        txr_user.setBounds(100,120,200,30);
        log.add(txr_user);

        txt_pass=new JLabel("Contraseña");
        txt_pass.setForeground(Color.GREEN);
        txt_pass.setFont(new Font("Arial",Font.BOLD,17));

        txt_pass.setBounds(100,190,200,30);
        log.add(txt_pass);

        user=new JTextField();
        user.setBounds(100,150,200,30);
        log.add(user);

        pass=new JPasswordField();
        pass.setBounds(100,220,200,30);
        log.add(pass);

        ingresarButton=new JButton("Ingresar");
        ingresarButton.setBounds(100,270,200,30);
        ingresarButton.setForeground(Color.BLACK);
        ingresarButton.setBackground(Color.BLUE);
        log.add(ingresarButton);

        salirButton=new JButton("Salir");
        salirButton.setBounds(100,310,200,30);
        salirButton.setForeground(Color.BLACK);
        salirButton.setBackground(Color.RED);
        log.add(salirButton);



        login.setContentPane(log);
        login.setVisible(true);



        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente=user.getText();
                String password=new String(pass.getPassword());

                try (Connection conexion = BD.conectar()){
                    String sql = "SELECT * FROM Credenciales WHERE Usuario =? AND Password =?";
                    try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
                        pstmt.setString(1,cliente);
                        pstmt.setString(2,password);

                        try (ResultSet rs=pstmt.executeQuery()){
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null,"Login Exitoso");
                                new Menu();
                                login.dispose();
                            }else {
                                JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrecta");
                            }

                        }

                    }


                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR falla conexion base de datos");
                }

            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }
}
