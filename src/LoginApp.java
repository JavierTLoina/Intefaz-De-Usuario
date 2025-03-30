import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginApp {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static JFrame loginFrame, userFrame;
    private static JTextField usuarioField;
    private static JPasswordField passwordField;
    private static Font interFont = new Font("Roboto", Font.BOLD, 14);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginApp::mostrarLogin);
    }

    private static void mostrarLogin() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(350, 250);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.getContentPane().setBackground(new Color(44, 62, 80)); 

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(44, 62, 80));

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        panel.add(usuarioLabel);
        usuarioField = new JTextField(15);
        usuarioField.setBackground(Color.WHITE);
        usuarioField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
        panel.add(usuarioField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        panel.add(passwordLabel);
        passwordField = new JPasswordField(15);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
        panel.add(passwordField);

        JButton loginButton = crearBoton("Iniciar Sesión");
        loginButton.addActionListener(e -> iniciarSesion());

        JButton registerButton = crearBoton("Registrarse");
        registerButton.addActionListener(e -> mostrarRegistro());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(44, 62, 80));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        loginFrame.add(panel, BorderLayout.CENTER);
        loginFrame.add(buttonPanel, BorderLayout.SOUTH);
        loginFrame.setVisible(true);
    }

    private static void mostrarRegistro() {
        JDialog registroFrame = new JDialog(loginFrame, "Registro", true);
        registroFrame.setSize(400, 400);
        registroFrame.setLayout(new GridLayout(8, 2, 10, 10));
        registroFrame.getContentPane().setBackground(new Color(44, 62, 80));  // Azul oscuro más suave

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setForeground(Color.WHITE);
        registroFrame.add(usuarioLabel);
        JTextField usuarioField = new JTextField();
        usuarioField.setBackground(Color.WHITE);
        usuarioField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(usuarioField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        registroFrame.add(nombreLabel);
        JTextField nombreField = new JTextField();
        nombreField.setBackground(Color.WHITE);
        nombreField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(nombreField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setForeground(Color.WHITE);
        registroFrame.add(apellidoLabel);
        JTextField apellidoField = new JTextField();
        apellidoField.setBackground(Color.WHITE);
        apellidoField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(apellidoField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setForeground(Color.WHITE);
        registroFrame.add(telefonoLabel);
        JTextField telefonoField = new JTextField();
        telefonoField.setBackground(Color.WHITE);
        telefonoField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(telefonoField);

        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailLabel.setForeground(Color.WHITE);
        registroFrame.add(emailLabel);
        JTextField emailField = new JTextField();
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(emailField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        registroFrame.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        confirmPasswordLabel.setForeground(Color.WHITE);
        registroFrame.add(confirmPasswordLabel);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));  // Borde más moderno
        registroFrame.add(confirmPasswordField);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBackground(new Color(52, 152, 219)); // Azul claro
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Roboto", Font.BOLD, 14));  // Cambiar la fuente
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añadir margen
        registerButton.addActionListener(e -> {
            if (validarRegistro(usuarioField, nombreField, apellidoField, telefonoField, emailField, passwordField, confirmPasswordField)) {
                usuarios.add(new Usuario(usuarioField.getText(), nombreField.getText(), apellidoField.getText(), telefonoField.getText(), emailField.getText(), new String(passwordField.getPassword())));
                JOptionPane.showMessageDialog(registroFrame, "Registro exitoso.");
                registroFrame.dispose();
            }
        });
        registroFrame.add(registerButton);

        registroFrame.setVisible(true);
    }

    private static boolean validarRegistro(JTextField usuarioField, JTextField nombreField, JTextField apellidoField, JTextField telefonoField, JTextField emailField, JPasswordField passwordField, JPasswordField confirmPasswordField) {
        if (usuarioField.getText().isEmpty() || nombreField.getText().isEmpty() || apellidoField.getText().isEmpty()
                || telefonoField.getText().isEmpty() || emailField.getText().isEmpty()
                || passwordField.getPassword().length == 0 || confirmPasswordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }
        if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }

    private static void iniciarSesion() {
        String user = usuarioField.getText();
        String pass = new String(passwordField.getPassword());

        for (Usuario u : usuarios) {
            if (u.username.equals(user) && u.password.equals(pass)) {
                loginFrame.dispose();
                mostrarUsuarios();
                return;
            }
        }
        JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrectos.");
    }

    private static void mostrarUsuarios() {
        userFrame = new JFrame("Usuarios Registrados");
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setSize(400, 300);
        userFrame.setLayout(new BorderLayout());
        userFrame.getContentPane().setBackground(new Color(44, 62, 80));  // Azul oscuro más suave

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Usuario u : usuarios) {
            listModel.addElement(u.username + " - " + u.nombre + " " + u.apellido + " - " + u.telefono + " - " + u.email);
        }

        JList<String> userList = new JList<>(listModel);
        userList.setFont(interFont);
        userList.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(userList);

        JButton logoutButton = crearBoton("Cerrar Sesión");
        logoutButton.addActionListener(e -> {
            userFrame.dispose();
            mostrarLogin();
        });

        JButton deleteButton = crearBoton("Eliminar Usuario");
        deleteButton.addActionListener(e -> {
            int selectedIndex = userList.getSelectedIndex();
            if (selectedIndex != -1) {
                usuarios.remove(selectedIndex);
                listModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(userFrame, "Seleccione un usuario para eliminar.");
            }
        });

        JButton editButton = crearBoton("Editar Usuario");
        editButton.addActionListener(e -> {
            int selectedIndex = userList.getSelectedIndex();
            if (selectedIndex != -1) {
                Usuario selectedUser = usuarios.get(selectedIndex);
                mostrarEdicionUsuario(selectedUser);  // Llamar al método de edición
            } else {
                JOptionPane.showMessageDialog(userFrame, "Seleccione un usuario para editar.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(44, 62, 80));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        userFrame.add(scrollPane, BorderLayout.CENTER);
        userFrame.add(buttonPanel, BorderLayout.SOUTH);
        userFrame.setVisible(true);
    }
    private static void mostrarEdicionUsuario(Usuario usuario) {
        JDialog editFrame = new JDialog(userFrame, "Editar Usuario", true);
        editFrame.setSize(400, 400);
        editFrame.setLayout(new GridLayout(8, 2, 10, 10));
        editFrame.getContentPane().setBackground(new Color(44, 62, 80));  // Azul oscuro más suave

        JTextField usuarioField = new JTextField(usuario.username);
        usuarioField.setBackground(Color.WHITE);
        usuarioField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JTextField nombreField = new JTextField(usuario.nombre);
        nombreField.setBackground(Color.WHITE);
        nombreField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JTextField apellidoField = new JTextField(usuario.apellido);
        apellidoField.setBackground(Color.WHITE);
        apellidoField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JTextField telefonoField = new JTextField(usuario.telefono);
        telefonoField.setBackground(Color.WHITE);
        telefonoField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JTextField emailField = new JTextField(usuario.email);
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JPasswordField passwordField = new JPasswordField(usuario.password);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        JButton saveButton = crearBoton("Guardar Cambios");
        saveButton.addActionListener(e -> {
            usuario.username = usuarioField.getText();
            usuario.nombre = nombreField.getText();
            usuario.apellido = apellidoField.getText();
            usuario.telefono = telefonoField.getText();
            usuario.email = emailField.getText();
            usuario.password = new String(passwordField.getPassword());
            editFrame.dispose();
            mostrarUsuarios();
        });

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setForeground(Color.WHITE);
        editFrame.add(usuarioLabel);
        editFrame.add(usuarioField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        editFrame.add(nombreLabel);
        editFrame.add(nombreField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setForeground(Color.WHITE);
        editFrame.add(apellidoLabel);
        editFrame.add(apellidoField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setForeground(Color.WHITE);
        editFrame.add(telefonoLabel);
        editFrame.add(telefonoField);

        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailLabel.setForeground(Color.WHITE);
        editFrame.add(emailLabel);
        editFrame.add(emailField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        editFrame.add(passwordLabel);
        editFrame.add(passwordField);


        editFrame.add(saveButton);

        editFrame.setVisible(true);
    }
    private static JButton crearBoton(String texto) {
        JButton button = new JButton(texto);
        button.setBackground(new Color(52, 152, 219)); // Azul claro
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }
}

class Usuario {
    String username;
    String nombre;
    String apellido;
    String telefono;
    String email;
    String password;

    public Usuario(String username, String nombre, String apellido, String telefono, String email, String password) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }
}


