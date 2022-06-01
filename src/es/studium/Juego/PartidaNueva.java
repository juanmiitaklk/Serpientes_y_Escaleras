package es.studium.Juego;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class PartidaNueva extends Frame
{
	private static final long serialVersionUID = 1L;
	Dialog pedirNumeroJugadores = new Dialog(this, "Serpientes y Escaleras: Nueva Partida", true);
	Dialog pedirNombresJugadores = new Dialog(this, "Serpientes y Escaleras: Nueva Partida", true);
	int numeroJugadores = 0; // Guarda el número de jugadores
	String[] nombresJugadores = null; // Guarda los nombres de los jugadores
	Choice choNumeroJugadores = new Choice();
	Button btnContinuar = new Button("Continuar...");
	
	TextField txfNombre1 = new TextField(15);
	Label lblEtiqueta1 = new Label("Jugador 1:");
	TextField txfNombre2 = new TextField(15);
	Label lblEtiqueta2 = new Label("Jugador 2:");
	TextField txfNombre3 = new TextField(15);
	Label lblEtiqueta3 = new Label("Jugador 3:");
	TextField txfNombre4 = new TextField(15);
	Label lblEtiqueta4 = new Label("Jugador 4:");
	Button btnComenzarPartida = new Button("Comenzar Partida");

	public PartidaNueva()
	{
		// Preguntamos el número de jugadores
		pedirNumeroJugadores.setBackground(Color.GREEN); // Color de fondo del Dialog
		pedirNumeroJugadores.setLayout(new FlowLayout()); // Layout del Dialog
		pedirNumeroJugadores.setSize(240,100); // Tamaño de Dialog
		pedirNumeroJugadores.setLocationRelativeTo(null); // Centrar el Dialog
		pedirNumeroJugadores.setResizable(false); // Evitar redimensionado

		choNumeroJugadores.add("Elegir número de jugadores...");
		choNumeroJugadores.add("2");
		choNumeroJugadores.add("3");
		choNumeroJugadores.add("4");
		pedirNumeroJugadores.add(choNumeroJugadores);
		pedirNumeroJugadores.add(btnContinuar);
	}

	public void MostrarDialogNumeroJugadores()
	{
		pedirNumeroJugadores.setVisible(true);
	}

	public void OcultarDialogNumeroJugadores()
	{
		pedirNumeroJugadores.setVisible(false);
	}

	public void PrepararDialogNombresJugadores(int numero)
	{
		pedirNombresJugadores.setBackground(Color.GREEN); // Color de fondo del Dialog
		pedirNombresJugadores.setLayout(new FlowLayout()); // Layout del Dialog
		pedirNombresJugadores.setSize(240,200); // Tamaño de Dialog
		pedirNombresJugadores.setLocationRelativeTo(null); // Centrar el Dialog
		pedirNombresJugadores.setResizable(false); // Evitar redimensionado
		// Preguntamos los nombres
		// Jugador 1, siempre existe
		pedirNombresJugadores.add(lblEtiqueta1);
		txfNombre1.selectAll(); // Reseteamos los cuadros de texto
		txfNombre1.setText("");
		pedirNombresJugadores.add(txfNombre1);
		// Jugador 2, siempre existe
		pedirNombresJugadores.add(lblEtiqueta2);
		txfNombre2.selectAll(); // Reseteamos los cuadros de texto
		txfNombre2.setText("");
		pedirNombresJugadores.add(txfNombre2);
		// Si existe jugador 3
		if(numero == 3)
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); // Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
		}
		else
		{
			pedirNombresJugadores.remove(lblEtiqueta3);
			txfNombre3.selectAll(); // Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.remove(txfNombre3);
		}
		if(numero == 4) // Son 4 jugadores
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); // Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
			pedirNombresJugadores.add(lblEtiqueta4);
			txfNombre4.selectAll(); // Reseteamos los cuadros de texto
			txfNombre4.setText("");
			pedirNombresJugadores.add(txfNombre4);
		}
		else
		{
			pedirNombresJugadores.remove(lblEtiqueta4);
			txfNombre4.selectAll(); // Reseteamos los cuadros de texto
			txfNombre4.setText("");
			pedirNombresJugadores.remove(txfNombre4);
		}
		pedirNombresJugadores.add(btnComenzarPartida);
		this.MostrarDialogNombresJugadores();
	}

	public void MostrarDialogNombresJugadores()
	{
		pedirNombresJugadores.setVisible(true);
	}

	public void OcultarDialogNombresJugadores()
	{
		pedirNombresJugadores.setVisible(false);
	}
}
