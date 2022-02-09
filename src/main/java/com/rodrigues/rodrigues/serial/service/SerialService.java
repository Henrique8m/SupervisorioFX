package com.rodrigues.rodrigues.serial.service;

import org.apache.el.stream.Optional;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialService implements Runnable, SerialPortEventListener {

	private final byte[] bufferRead = new byte[7];

	private String display;
	private final String Porta;

	private final int baudrate;
	private final int timeout;

	private CommPortIdentifier cp;
	private SerialPort porta;

	private OutputStream saida;
	private InputStream entrada;

	private boolean IDPortaOK; // true porta EXISTE
	private boolean PortaOK;// true porta aberta
	private boolean runExec = true;

	private Thread thread;

	public String getDisplay() {
		return display;
	}

	public SerialService(String p, int b, int t) {
		this.Porta = p;
		this.baudrate = b;
		this.timeout = t;

	}

	public Boolean getPortIdentifier() {
		try {
			cp = CommPortIdentifier.getPortIdentifier(Porta);
			return cp != null;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("static-access")
	public Boolean AbrirPorta() {
		try {
			porta = (SerialPort) cp.open("SerialService", timeout);
			porta.setSerialPortParams(baudrate, porta.DATABITS_8, porta.STOPBITS_2, porta.PARITY_NONE);
			return true;
		} catch (Exception e) {
			System.out.println("Error Open Port! STATUS: " + e);
			return false;
		}
	}

	public void WriteData(byte[] bufferWrite) {
		if (runExec) {
			try {
				saida = porta.getOutputStream();
			} catch (Exception e) {
				System.out.println("Erro.STATUS: " + e);
			}

			try {
				saida.write(bufferWrite);
				saida.flush();
			} catch (Exception e) {
				e.printStackTrace();
				FecharCom();
			}

		} else {
			thread.interrupt();
			System.out.println("thread.interrupt");
			PortaOK = false;
			runExec = true;
		}

	}

	
	public void formatDados() {
		if (Byte.toUnsignedInt(this.bufferRead[3]) > 0) {
			try {
				this.display = Integer
						.toString(Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			this.display = Integer.toString(Byte.toUnsignedInt(this.bufferRead[4]));
		}
		System.out.println(display);
	}

	public void FecharCom() {
		try {
			porta.close();
			PortaOK = false;
			System.out.println("CONEXAO FECHADA>>FIM..");
		} catch (Exception e) {
			System.out.println("ERRO AO FECHAR. STATUS: " + e);
		}
	}

	@Override
	public void serialEvent(SerialPortEvent ev) {
	}

	public Boolean LerDados() throws InterruptedException {
		thread = new Thread(this);
		thread.start();
		thread.join(5*100);
		return true;
	}

	@Override
	public void run() {
		runExec = false;
		try {
			porta.setInputBufferSize(7);
			entrada = porta.getInputStream();
		} catch (Exception e) {
			System.out.println(" Entrada vazia");
			e.printStackTrace();
			runExec = true;
			Thread.interrupted();
		}

		try {
			entrada.read(bufferRead);
			// System.out.println("Fim do read");
			runExec = true;
			formatDados();
			Thread.interrupted();

		} catch (NullPointerException e) {
			// System.out.println("Endereço errado ");
			runExec = true;
			Thread.interrupted();
		} catch (Exception e) {
			// System.out.println("Erro durante a leitura: " + e);
			runExec = true;
			Thread.interrupted();
		}

	}
}