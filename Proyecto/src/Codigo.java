import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Codigo {
    private JTextField verifica;
    private JButton volverButton;
    private JButton verficiarButton;
    private JLabel title;
    private JLabel dragon;
    private JLabel link;
    private JLabel codig;
    private JPanel des;

    public Codigo() {

        JFrame descuento=new JFrame("Menu");
        descuento.setSize(400,400);
        descuento.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        descuento.setLocationRelativeTo(null);

        JLabel background =new JLabel(new ImageIcon("src/fondos/mario.png"));
        background.setLayout(new BorderLayout());

        des =new JPanel();
        des.setOpaque(false);


        dragon =new JLabel(new ImageIcon("src/images/chimuelo.jpg"));
        link=new JLabel(new ImageIcon("src/images/link.jpg"));
        verifica=new JTextField(15);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setOpaque(false);
        imagePanel.add(dragon);
        imagePanel.add(link);

        title =new JLabel("Premios / Descuentos");
        title.setFont(new Font("Arial",Font.BOLD,17));
        title.setBounds(100,270,200,30);
        title.setForeground(Color.YELLOW);
        title.setBackground(Color.BLUE);
        imagePanel.add(title);

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=0;
        gbc.gridy=0;
        des.add(title,gbc);

        gbc.gridy=1;
        des.add(imagePanel,gbc);

        gbc.gridy=2;
        des.add(verifica,gbc);

        gbc.gridy=3;
        des.add(verficiarButton,gbc);

        gbc.gridy=4;
        des.add(volverButton,gbc);

        background.add(des, BorderLayout.CENTER);

        descuento.setContentPane(background);
        descuento.setVisible(true);


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            descuento.dispose();
            new Menu();

            }
        });
        verficiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String validacion =verifica.getText();

                try (Connection conexion = BD.conectar()){
                    String sql = "SELECT Codigo From codig WHERE Codigo=?";

                    try (PreparedStatement pstmt =conexion.prepareStatement(sql)){
                        pstmt.setString(1,validacion);

                        try (ResultSet rs=pstmt.executeQuery()){
                            if (rs.next()){
                                String descuent=rs.getString("Codigo");
                                JOptionPane.showMessageDialog(null,"Codigo ganador Felicidades");
                            }else {
                                JOptionPane.showMessageDialog(null,"Codigo no Favorecido");
                            }

                        }

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error en la base de datos");
                }
            }
        });
    }
}
