package es.studium.Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener, MouseListener
{
	VistaPrincipal vistaPrincipal;
	VistaTopTen vistaTopTen = new VistaTopTen(); // Top Ten
	PartidaNueva vistaPartidaNueva = new PartidaNueva(); // Partida Nueva
	Tablero tablero; // Tablero
	Modelo modelo;
	int numJugadores;
	int turno = 1;
	int tirada;
	int tiradasRojo = 0;
	int tiradasRosa = 0;
	int tiradasVerde = 0;
	int tiradasAzul = 0;

	public Controlador(Modelo m, VistaPrincipal v)
	{
		modelo = m;
		vistaPrincipal = v;
		vistaPrincipal.addWindowListener(this);
		vistaPrincipal.btnTopTen.addActionListener(this); // Top Ten
		vistaPrincipal.btnPartidaNueva.addActionListener(this); // Partida Nueva
		vistaPrincipal.btnSalir.addActionListener(this);
		vistaTopTen.addWindowListener(this);
		vistaTopTen.btnVolver.addActionListener(this);
		vistaPartidaNueva.pedirNumeroJugadores.addWindowListener(this);
		vistaPartidaNueva.btnContinuar.addActionListener(this);
		vistaPartidaNueva.pedirNombresJugadores.addWindowListener(this);
		vistaPartidaNueva.btnComenzarPartida.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object botonPulsado = ae.getSource();
		if(botonPulsado.equals(vistaPrincipal.btnSalir))
		{
			System.exit(0);
		}
		else if(botonPulsado.equals(vistaPrincipal.btnTopTen)) // Top Ten
		{
			vistaTopTen.MostrarTopTen();
			vistaPrincipal.setVisible(false);
		}
		else if(botonPulsado.equals(vistaTopTen.btnVolver)) // Top Ten
		{
			vistaTopTen.OcultarTopTen();
			vistaPrincipal.setVisible(true);
		}
		else if(botonPulsado.equals(vistaPrincipal.btnPartidaNueva)) // Partida Nueva
		{
			vistaPartidaNueva.MostrarDialogNumeroJugadores();
			vistaPrincipal.setVisible(false);
		}
		else if(botonPulsado.equals(vistaPartidaNueva.btnContinuar)) // Partida Nueva
		{
			if(!vistaPartidaNueva.choNumeroJugadores.getSelectedItem().equals("Elegir número de jugadores..."))
			{
				vistaPartidaNueva.PrepararDialogNombresJugadores(Integer.parseInt(vistaPartidaNueva.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(vistaPartidaNueva.btnComenzarPartida)) // Partida Nueva
		{
			numJugadores = Integer.parseInt(vistaPartidaNueva.choNumeroJugadores.getSelectedItem());
			if((numJugadores==4)&&(!vistaPartidaNueva.txfNombre1.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre2.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre3.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre4.getText().equals("")))
			{
				tablero = new Tablero(4, vistaPartidaNueva.txfNombre1.getText(),vistaPartidaNueva.txfNombre2.getText(),vistaPartidaNueva.txfNombre3.getText(),vistaPartidaNueva.txfNombre4.getText());
				tablero.addWindowListener(this);
				tablero.addMouseListener(this);
				tablero.setVisible(true);
				vistaPartidaNueva.setVisible(false);
			}
			else if((numJugadores==3)&&(!vistaPartidaNueva.txfNombre1.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre2.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre3.getText().equals("")))
			{
				tablero = new Tablero(3,vistaPartidaNueva.txfNombre1.getText(),vistaPartidaNueva.txfNombre2.getText(),vistaPartidaNueva.txfNombre3.getText(),"");
				tablero.addWindowListener(this);
				tablero.addMouseListener(this);
				tablero.setVisible(true);
				vistaPartidaNueva.setVisible(false);
			}
			else if((numJugadores==2)&&(!vistaPartidaNueva.txfNombre1.getText().equals(""))
					&&(!vistaPartidaNueva.txfNombre2.getText().equals("")))
			{
				tablero = new Tablero(2, vistaPartidaNueva.txfNombre1.getText(),vistaPartidaNueva.txfNombre2.getText(),"","");
				tablero.addWindowListener(this);
				tablero.addMouseListener(this);
				tablero.setVisible(true);
				vistaPartidaNueva.setVisible(false);
			}
			else
			{
				vistaPartidaNueva.txfNombre1.requestFocus();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(vistaTopTen.isActive()) // Top Ten
		{
			vistaTopTen.OcultarTopTen();
			vistaPrincipal.setVisible(true);
		}
		else if(vistaPartidaNueva.pedirNumeroJugadores.isActive()) // Partida Nueva, pidiendo número de jugadores
		{
			vistaPartidaNueva.pedirNumeroJugadores.removeWindowListener(this);
			vistaPartidaNueva.btnContinuar.removeActionListener(this);
			vistaPartidaNueva.OcultarDialogNumeroJugadores();
		}
		else if(vistaPartidaNueva.pedirNombresJugadores.isActive()) // Partida Nueva, pidiendo nombres jugadores
		{
			vistaPartidaNueva.pedirNombresJugadores.removeWindowListener(this);
			vistaPartidaNueva.btnComenzarPartida.removeActionListener(this);
			vistaPartidaNueva.choNumeroJugadores.select(0); // Reseteamos el desplegable
			vistaPartidaNueva.removeAll();
			vistaPartidaNueva.OcultarDialogNombresJugadores();
		}
		else if((tablero!=null)&&(tablero.isActive())) // Tablero
		{
			tablero.removeWindowListener(this);
			tablero.removeMouseListener(this);
			tablero.setVisible(false);
			vistaPartidaNueva.OcultarDialogNombresJugadores();
			vistaPrincipal.setVisible(true);
		}
		else
		{
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}

	@Override
	public void mouseClicked(MouseEvent click)
	{
		int x = click.getX();
		int y = click.getY();
		// Pulsamos sobre el dado
		if((x>=33)&&(x<=73)&&(y>=217)&&(y<=277))
		{
			tirada = modelo.tirada();
			tablero.mostrarTirada(tirada);
			if(true) // Analizar tirada
			{
				// Actualizar la posición de la ficha
				switch(turno)
				{
				case 1:
					tablero.xRoja = tablero.xRoja+tirada*38;
					tiradasRojo++;
					if((tablero.xRoja>=444)&&(tablero.yRoja<=38))
					{
						// Gana Roja
						System.out.println("GANA ROJA!");
					}
					else if((tablero.xRoja==290)&&(tablero.yRoja==380))
					{
						tablero.xRoja = 176;
						tablero.yRoja = 266;
					}
					else if(tablero.xRoja>420)
					{
						tablero.xRoja = 140;
						tablero.yRoja = tablero.yRoja-38;
					}
					break;
				case 2:
					tablero.xRosa = tablero.xRosa+tirada*38;
					tiradasRosa++;
					if((tablero.xRosa>=444)&&(tablero.yRosa<=38))
					{
						// Gana Rosa
						System.out.println("GANA ROSA!");
					}
					else if((tablero.xRosa==285)&&(tablero.yRosa==380))
					{
						tablero.xRosa = 176;
						tablero.yRosa = 266;
					}
					else if(tablero.xRosa>420)
					{
						tablero.xRosa = 140;
						tablero.yRosa = tablero.yRosa-38;
					}
					break;
				case 3:
					tablero.xVerde = tablero.xVerde+tirada*38;
					tiradasVerde++;
					if((tablero.xVerde>=444)&&(tablero.yVerde<=38))
					{
						// Gana Verde
						System.out.println("GANA VERDE!");
					}
					else if((tablero.xVerde==280)&&(tablero.yVerde==380))
					{
						tablero.xVerde = 176;
						tablero.yVerde = 266;
					}
					else if(tablero.xVerde>420)
					{
						tablero.xVerde = 140;
						tablero.yVerde = tablero.yVerde-38;
					}
					break;
				case 4:
					tablero.xAzul = tablero.xAzul+tirada*38;
					tiradasAzul++;
					if((tablero.xAzul>=444)&&(tablero.yAzul<=38))
					{
						// Gana Azul
						System.out.println("GANA AZUL!");
					}
					else if((tablero.xAzul==275)&&(tablero.yAzul==380))
					{
						tablero.xAzul = 176;
						tablero.yAzul = 266;
					}
					else if(tablero.xAzul>420)
					{
						tablero.xAzul = 140;
						tablero.yAzul = tablero.yAzul-38;
					}
					break;
				}
				System.out.println("Roja: "+tablero.xRoja+"-"+tablero.yRoja);
				System.out.println("Rosa: "+tablero.xRosa+"-"+tablero.yRosa);
				System.out.println("Verde: "+tablero.xVerde+"-"+tablero.yVerde);
				System.out.println("Azul: "+tablero.xAzul+"-"+tablero.yAzul);
				turno++;
				if(turno>numJugadores)
				{
					turno = 1;
				}
				tablero.actualizarTurno(turno);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{}

	@Override
	public void mouseExited(MouseEvent arg0)
	{}

	@Override
	public void mousePressed(MouseEvent arg0)
	{}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{}
}


