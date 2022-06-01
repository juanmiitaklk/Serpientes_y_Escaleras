package es.studium.Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Tablero extends Frame
{
	private static final long serialVersionUID = 1L;
	Toolkit herramientas;
	Image tablero;
	int numJugadores;
	String jugador1, jugador2, jugador3, jugador4;
	int turnoJugador = 1;
	int tirada = 0;
	Font fuenteTirada = new Font("Jokerman", Font.BOLD, 24);
	Font fuenteTurno = new Font("Jokerman", Font.BOLD, 24);
	Font fuenteJugadores = new Font("Jokerman", Font.BOLD, 22);
	
	int xRoja=100, yRoja=380;
	int xRosa=95, yRosa=380;
	int xVerde=90, yVerde=380;
	int xAzul=85, yAzul=380;
	
	public Tablero(int n, String j1, String j2, String j3, String j4)
	{
		numJugadores = n;
		jugador1 = j1;
		jugador2 = j2;
		jugador3 = j3;
		jugador4 = j4;
		herramientas = getToolkit();
		tablero = herramientas.getImage("tablero600x400.jpg");
		setTitle("Jugando a Serpientes y Escaleras"); // Título
		setLayout(null); // Para poder posicionar los elementos en posiciones absolutas
		setSize(600,420); // Tamaño de Frame
		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(tablero, 0, 30, this);
		g.setFont(fuenteTirada);
		g.setColor(Color.black);
		if(tirada!=0)
		{
			g.drawString(tirada+"", 53, 255);
		}
		g.setFont(fuenteTurno);
		// Turno
		switch(turnoJugador)
		{
			case 1:
				g.setColor(Color.red);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador1, 10, 90);
				break;
			case 2:
				g.setColor(Color.pink);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador2, 10, 90);
				break;
			case 3:
				g.setColor(Color.green);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador3, 10, 90);
				break;
			case 4:
				g.setColor(Color.blue);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador4, 10, 90);
				break;
		}
		g.setFont(fuenteJugadores);
		// Jugadores
		g.setColor(Color.red);
		g.drawString(jugador1, 10, 320);
		g.fillOval(xRoja, yRoja, 20, 20); // Ficha Roja
		g.setColor(Color.pink);
		g.drawString(jugador2, 10, 350);
		g.fillOval(xRosa, yRosa, 20, 20); // Ficha Rosa
		switch(numJugadores)
		{
			case 3:
				g.setColor(Color.green);
				g.drawString(jugador3, 10, 380);
				g.fillOval(xVerde, yVerde, 20, 20); // Ficha Verde
				break;
			case 4:
				g.setColor(Color.green);
				g.drawString(jugador3, 10, 380);
				g.fillOval(xVerde, yVerde, 20, 20);
				g.setColor(Color.blue);
				g.drawString(jugador4, 10, 410);
				g.fillOval(xAzul, yAzul, 20, 20); // Ficha Azul
				break;
		}
	}
	public void actualizarTurno(int t)
	{
		turnoJugador = t;
		repaint();
	}

	public void mostrarTirada(int t)
	{
		tirada = t;
		repaint();
	}
}
