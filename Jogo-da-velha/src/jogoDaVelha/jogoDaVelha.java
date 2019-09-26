package jogoDaVelha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class jogoDaVelha extends JFrame {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	ImageIcon iconCirculo = new ImageIcon(getClass().getResource("JogodaVelha01.jpg"));
	ImageIcon iconX = new ImageIcon(getClass().getResource("JogodaVelha02.jpg"));

	JPanel telaJogo = new JPanel(new GridLayout(3, 3, 10, 10));

	Bloco[] blocos = new Bloco[9];

	int rodadas = 0;

	final int JOGADOR_1 = 1;
	final int JOGADOR_2 = 2;

	int jogadorVez = JOGADOR_1;

	JLabel informacaoJogador = new JLabel("Jogador " + JOGADOR_1);

	public jogoDaVelha() {
		configurarJanela();
		configurarTela();
	}

	public void configurarTela() {
		add(BorderLayout.CENTER, telaJogo);
		add(BorderLayout.NORTH, informacaoJogador);
		telaJogo.setBackground(Color.BLACK);
		informacaoJogador.setFont(new Font("Arial", Font.BOLD, 35));
		informacaoJogador.setForeground(new Color(50, 200, 50));
		informacaoJogador.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < 9; i++) {
			Bloco bloco = new Bloco();
			blocos[i] = bloco;
			telaJogo.add(bloco);
		}
	}

	public void mudarVez() {
		if (jogadorVez == 1) {
			jogadorVez = 2;
			informacaoJogador.setText("Jogador 2");
			informacaoJogador.setForeground(Color.RED);
		} else {
			jogadorVez = 1;
			informacaoJogador.setText("Jogador 1");
			informacaoJogador.setForeground(new Color(50, 200, 50));
		}
	}

	public boolean testarVitoria(int jog) {
		if (blocos[0].quemJoga == jog && blocos[1].quemJoga == jog && blocos[2].quemJoga == jog) {
			return true;
		}

		if (blocos[0].quemJoga == jog && blocos[3].quemJoga == jog && blocos[6].quemJoga == jog) {
			return true;
		}

		if (blocos[0].quemJoga == jog && blocos[4].quemJoga == jog && blocos[8].quemJoga == jog) {
			return true;
		}

		if (blocos[3].quemJoga == jog && blocos[4].quemJoga == jog && blocos[5].quemJoga == jog) {
			return true;
		}

		if (blocos[6].quemJoga == jog && blocos[7].quemJoga == jog && blocos[8].quemJoga == jog) {
			return true;
		}

		if (blocos[1].quemJoga == jog && blocos[4].quemJoga == jog && blocos[7].quemJoga == jog) {
			return true;
		}

		if (blocos[2].quemJoga == jog && blocos[5].quemJoga == jog && blocos[8].quemJoga == jog) {
			return true;
		}

		if (blocos[2].quemJoga == jog && blocos[4].quemJoga == jog && blocos[6].quemJoga == jog) {
			return true;
		}

		if (blocos[6].quemJoga == jog && blocos[4].quemJoga == jog && blocos[2].quemJoga == jog) {
			return true;
		}

		return false;

	}

	public void configurarJanela() {
		setTitle("Jogo Da Velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new jogoDaVelha();
	}

	public class Bloco extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int quemJoga = 0;

		public Bloco() {
			setBackground(Color.WHITE);
			addActionListener(e -> {
				if (quemJoga == 0) {
					if (jogadorVez == JOGADOR_1) {
						setIcon(iconCirculo);
						quemJoga = JOGADOR_1;
					} else {
						setIcon(iconX);
						quemJoga = JOGADOR_2;
					}

					if (testarVitoria(quemJoga)) {
						JOptionPane.showMessageDialog(null, "Jogador " + quemJoga + " venceu!");
						System.exit(0);
					}

					rodadas++;
					if (rodadas == 9) {
						JOptionPane.showMessageDialog(null, "Deu Velha!");
						System.exit(0);
					}
					mudarVez();
				}
			});
		}
	}

}
