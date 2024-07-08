package NodoInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NodoForm extends JFrame {
    private JTextField nombreField;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton preordenButton;
    private JButton inordenButton;
    private JButton postordenButton;
    private JTextArea nodosArea;
    private Nodo raiz;

    public NodoForm() {
        // Configuración de la ventana
        setTitle("Formulario de Nodos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Valor del Nodo:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        agregarButton = new JButton("Agregar Nodo");
        inputPanel.add(agregarButton);
        eliminarButton = new JButton("Eliminar Nodo");
        inputPanel.add(eliminarButton);

        // Panel de botones de orden
        JPanel ordenPanel = new JPanel(new GridLayout(1, 3));
        preordenButton = new JButton("Preorden");
        inordenButton = new JButton("Inorden");
        postordenButton = new JButton("Postorden");
        ordenPanel.add(preordenButton);
        ordenPanel.add(inordenButton);
        ordenPanel.add(postordenButton);

        // Área de texto para mostrar nodos
        nodosArea = new JTextArea();
        nodosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(nodosArea);

        // Añadir componentes a la ventana
        add(inputPanel, BorderLayout.NORTH);
        add(ordenPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acción de los botones
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarNodo();
            }
        });

        preordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNodos("preorden");
            }
        });

        inordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNodos("inorden");
            }
        });

        postordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNodos("postorden");
            }
        });
    }

    private void agregarNodo() {
        String valorText = nombreField.getText();
        if (valorText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor para el nodo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int valor;
        try {
            valor = Integer.parseInt(valorText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        raiz = insertar(raiz, valor);
        nombreField.setText("");
        actualizarNodosArea();
    }

    private Nodo insertar(Nodo raiz, int valor) {
        if (raiz == null) {
            return new Nodo(valor);
        }
        if (valor < raiz.valor) {
            raiz.izquierdo = insertar(raiz.izquierdo, valor);
        } else if (valor > raiz.valor) {
            raiz.derecho = insertar(raiz.derecho, valor);
        }
        return raiz;
    }

    private void eliminarNodo() {
        String valorText = nombreField.getText();
        if (valorText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el valor del nodo a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int valor;
        try {
            valor = Integer.parseInt(valorText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        raiz = eliminar(raiz, valor);
        nombreField.setText("");
        actualizarNodosArea();
    }

    private Nodo eliminar(Nodo raiz, int valor) {
        if (raiz == null) {
            return null;
        }
        if (valor < raiz.valor) {
            raiz.izquierdo = eliminar(raiz.izquierdo, valor);
        } else if (valor > raiz.valor) {
            raiz.derecho = eliminar(raiz.derecho, valor);
        } else {
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
            }
            raiz.valor = encontrarMin(raiz.derecho).valor;
            raiz.derecho = eliminar(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    private Nodo encontrarMin(Nodo raiz) {
        while (raiz.izquierdo != null) {
            raiz = raiz.izquierdo;
        }
        return raiz;
    }

    private void mostrarNodos(String orden) {
        ArrayList<Integer> nodosOrdenados = new ArrayList<>();
        switch (orden) {
            case "preorden":
                preorden(raiz, nodosOrdenados);
                break;
            case "inorden":
                inorden(raiz, nodosOrdenados);
                break;
            case "postorden":
                postorden(raiz, nodosOrdenados);
                break;
        }
        nodosArea.setText(nodosOrdenados.toString());
    }

    private void preorden(Nodo raiz, ArrayList<Integer> resultado) {
        if (raiz != null) {
            resultado.add(raiz.valor);
            preorden(raiz.izquierdo, resultado);
            preorden(raiz.derecho, resultado);
        }
    }

    private void inorden(Nodo raiz, ArrayList<Integer> resultado) {
        if (raiz != null) {
            inorden(raiz.izquierdo, resultado);
            resultado.add(raiz.valor);
            inorden(raiz.derecho, resultado);
        }
    }

    private void postorden(Nodo raiz, ArrayList<Integer> resultado) {
        if (raiz != null) {
            postorden(raiz.izquierdo, resultado);
            postorden(raiz.derecho, resultado);
            resultado.add(raiz.valor);
        }
    }

    private void actualizarNodosArea() {
        ArrayList<Integer> nodosActuales = new ArrayList<>();
        inorden(raiz, nodosActuales); // Mostramos en inorden por defecto
        nodosArea.setText(nodosActuales.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NodoForm().setVisible(true);
            }
        });
    }
}

class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int valor) {
        this.valor = valor;
        izquierdo = derecho = null;
    }
}