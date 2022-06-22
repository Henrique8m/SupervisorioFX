package com.rodrigues.rodrigues.relatorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.entities.Balancas;
import com.rodrigues.rodrigues.entities.Carvao;
import com.rodrigues.rodrigues.entities.Pirometro;
import com.rodrigues.rodrigues.entities.Pyrometry;
import com.rodrigues.rodrigues.serial.utilitary.Format;

public class GeneratorPDF {
	
	String[] letters = new String[]{ "A:", "B:", "D:", "E:", "F:", "G:", "H:", "I:"};
	File[] drives = new File[letters.length];
	boolean[] isDrive = new boolean[letters.length];
		
	public static void newDocument(List<?> list) {
		String data = Format.formatData.format(new Date( System.currentTimeMillis() ) );
		Paragraph paragraph;
		Document document = new Document();
		
		String local = System.getProperty("user.home")
						.toString() + 
						MainApp.caminhoPDF;
		try {
			File diretorio1 = new File(local);
			diretorio1.mkdir();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String nameArquivo = "\\" + 
						list.get(0).getClass().getSimpleName() +
						"  " + 
						data +
						".pdf";
		String caminho = local + 
				nameArquivo;
		
		File file = new File(caminho);		
		if(file.exists()) {
			file.delete();
		}

		try { 
		   	PdfWriter.getInstance(document, new FileOutputStream(caminho) );
	    	document.open();
	    	document.addTitle("Relatorio");
	    	
        	paragraph = new Paragraph("Relatorio");
    		paragraph.setAlignment(Element.ALIGN_CENTER);    		
    		document.add(paragraph);
    		
    		paragraph = new Paragraph(" ");    		
    		document.add(paragraph);
    		    		
    		switch (list.get(0).getClass().getSimpleName() ) {
			case "Balancas":
				@SuppressWarnings("unchecked") 
				List<Balancas> listBal = (List<Balancas>) list;
				for(Balancas bal: listBal ) {
					paragraph = new Paragraph( stringBalancas(bal) );
					paragraph.setAlignment(Element.ALIGN_CENTER);
					document.add(paragraph);
				}				
				break;
				
			case "Pyrometry":
				@SuppressWarnings("unchecked") 
				List<Pyrometry> listPyrometry = (List<Pyrometry>) list;
				for(Pyrometry Pyr: listPyrometry ) {
					paragraph = new Paragraph( stringPyrometria(Pyr) );
					paragraph.setAlignment(Element.ALIGN_CENTER);
					document.add(paragraph);
				}
				break;
				
			case "Carvao":
				@SuppressWarnings("unchecked") 
				List<Carvao> listCarvao = (List<Carvao>) list;
				for(Carvao carvao: listCarvao ) {
					paragraph = new Paragraph( stringCarvao(carvao) );
					paragraph.setAlignment(Element.ALIGN_CENTER);
					document.add(paragraph);
				}		
				break;		
				
			case "Pirometro":
				@SuppressWarnings("unchecked") 
				List<Pirometro> listPirometro = (List<Pirometro>) list;
				for(Pirometro pirometro: listPirometro ) {
					paragraph = new Paragraph( stringPirometro(pirometro) );
					paragraph.setAlignment(Element.ALIGN_CENTER);
					document.add(paragraph);
				}	
				break;
			}

    		paragraph = new Paragraph(" ");    		
    		document.add(paragraph);
	        
	        document.addCreationDate();  
	         
	    }catch(DocumentException de) {
	    	System.err.println(de.getMessage());
	     }
	     catch(IOException ioe) {
	         System.err.println(ioe.getMessage());
	     }
	     document.close();
    
	 }
	
	private static String stringPirometro(Pirometro pirometro) {
		return "Hora = " + pirometro.getDataTime() + " Temperatura medida " + pirometro.getTemp();
	}

	private static String stringCarvao(Carvao carvao) {
		return "Data = " + carvao.getDataCarvao() + " " + carvao.getHoraCarvao() +
				"  Peso " + carvao.getPesoCarvao()
				;
		
	}

	private static String stringPyrometria(Pyrometry pyr) {		
		return "Hora Inicio e Fim " + pyr.getTimeStartFinish() + "\n" +
				"Media pressao na coroa = " + pyr.getPressaoCoroa() + "\n" +
				"Media temperatura na coroa = " + pyr.getTempCoroa() + "\n" +
				"Media pressao no topo = " + pyr.getPressaoTopo() + "\n" +
				"Media temperatura no topo = " + pyr.getTempTopo() + "\n" +
				"Media temperatura no secador = " + pyr.getSecador() + "\n" +
				"Media vazao = " + pyr.getVazaoAr()
				;
	}

	private static String stringBalancas(Balancas balanca) {		
		return 
				"Data = " + balanca.getDate() +
				" Hora Inicio = " + balanca.getHInicio() +
				" Hora Final = " + balanca.getHFim() + "\n" + 				 
				"Balanca 01 = " + balanca.getBalanca01() + "\n" + 
				"Balanca 02 = " + balanca.getBalanca02() + "\n" + 
				"Balanca 03 = " + balanca.getBalanca03() + "\n" + 
				"Balanca 04 = " + balanca.getBalanca04() + "\n" + 
				"Balanca 05 = " + balanca.getBalanca05() + "\n" + 
				"Balanca 06 = " + balanca.getBalanca06() + "\n" + 
				"Balanca 07 = " + balanca.getBalanca07() + "\n" + 
				"Balanca 08 = " + balanca.getBalanca08() + "\n" + 
				"Balanca 09 = " + balanca.getBalanca09() + "\n" + 
				"Balanca 10 = " + balanca.getBalanca10()				
				;
		}
	
//	
//	public boolean copyPDF() {
//	    for ( int i = 0; i < letters.length; ++i ){
//			drives[i] = new File(letters[i]);		    	
//	        boolean pluggedIn = drives[i].canRead();
//	        if ( pluggedIn ) 
//	        {        	
//	    		try 
//	    		{
//	    			if( diretorio == 2 ) 
//	    			{
//	    				File newDiretorio = new File(letters[i] + "\\" + this.data);
//	    				newDiretorio.mkdir();
//	    				Files.copy(Paths.get( caminho ), Paths.get( letters[i] + "\\" + this.data + "\\"+ nameArquivo ) );
//	    			}
//	    			else
//	    			{
//	    				Files.copy(Paths.get( caminho ), Paths.get( letters[i] + "\\" + nameArquivo ) );
//	    			}
//										
//					//System.out.println("Drive "+letters[i]+" has been plugged in");					
//					return true;
//				} 
//	    		catch (FileAlreadyExistsException e) 
//	    		{					
//					System.out.println("Arquivo j� existe no pendriver");
//					return true;
//				} catch (IOException e) {
//					
//					e.printStackTrace();
//					return false;
//				}
//    
//	        }
//	        
//	    }
//	    return false;
//	}
}
