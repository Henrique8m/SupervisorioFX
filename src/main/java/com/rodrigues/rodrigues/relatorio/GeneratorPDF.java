package com.rodrigues.rodrigues.relatorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.hrodriguesdev.MainApp;
import com.hrodriguesdev.entities.Motorista;
import com.hrodriguesdev.entities.Pesagem;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratorPDF {
	private String caminho,nameArquivo, data;
	private int diretorio;
	private Document document;
	private Paragraph paragraph;
	private String nome, placa, cidade, estado, telefone;
	private double pesoTotal;
	
	String[] letters = new String[]{ "A:", "B:", "D:", "E:", "F:", "G:", "H:", "I:"};
	File[] drives = new File[letters.length];
	boolean[] isDrive = new boolean[letters.length];
		
	public Boolean newDocument(Motorista motoristaPDF, List<Pesagem> listPDF, int diretorio) {
		this.diretorio = diretorio;
		document = new Document();
		pesoTotal = 0d;				
		data = motoristaPDF.getData();
		this.data = data.replaceAll("/", "-");	
		
		String local = System.getProperty("user.home")
						.toString() + 
						MainApp.caminhoPDF;
		try {
			File diretorio1 = new File(local);
			diretorio1.mkdir();
			if(diretorio == 2) {
				local = local + 
						"\\" +
						data; 				
				
				File diretorio2 = new File(local);
				diretorio2.mkdir();

			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	

		nameArquivo = "\\" + 
						motoristaPDF.getPlaca() +
						"    " + 
						data +
						".pdf";
		caminho = local + 
				nameArquivo;
		
		File file = new File(caminho);		
		if(file.exists()) {
			file.delete();
		}

		try { 
		   	PdfWriter.getInstance(document, new FileOutputStream(caminho) );
	    	document.open();
	    	document.addTitle("Relatorio de Pesagem");
	    	
        	paragraph = new Paragraph("Relatorio de Pesagem");
    		paragraph.setAlignment(Element.ALIGN_CENTER);    		
    		document.add(paragraph);
    		
    		paragraph = new Paragraph(" ");    		
    		document.add(paragraph);
    		    		
        	paragraph = new Paragraph( coletaDeDados(motoristaPDF) );        	
    		paragraph.setAlignment(Element.ALIGN_CENTER);
    		document.add(paragraph);
    		
    		paragraph = new Paragraph(" ");    		
    		document.add(paragraph);
    		    		
	        for(Pesagem p: listPDF) {
	        	paragraph = new Paragraph(p.getNumeroCaixote() + "      " + p.getPeso() + "Kg        " + p.getData() + " " + p.getHora() );
	        	pesoTotal += p.getPeso();
	    		paragraph.setAlignment(Element.ALIGN_CENTER); 
	    		document.add(paragraph);
	    		 
	    	}
    		paragraph = new Paragraph(" ");    		
    		document.add(paragraph);
	        
	      	paragraph = new Paragraph( fechamento( pesoTotal, listPDF.size() ) );
	    	paragraph.setAlignment(Element.ALIGN_CENTER);    		
	    	document.add(paragraph);
	        
	        document.addCreationDate();  
	         
	    }catch(DocumentException de) {
	    	System.err.println(de.getMessage());
	    	return false;
	     }
	     catch(IOException ioe) {
	         System.err.println(ioe.getMessage());
	         return false;
	     }
	     document.close();
	     return true;
	     
	 }
	
	private String coletaDeDados(Motorista motoristaPDF) {
		if(motoristaPDF.getName() == null) nome = "";
		else nome = motoristaPDF.getName();
		
		if(motoristaPDF.getPlaca() == null) placa = "";
		else placa = motoristaPDF.getPlaca();
		
		if(motoristaPDF.getCidade() == null) cidade = "";
		else cidade = motoristaPDF.getCidade();
		
		if(motoristaPDF.getEstado() == null) estado = "";
		else estado = motoristaPDF.getEstado();
		
		if(motoristaPDF.getPhone() == null) telefone = "";
		else telefone = motoristaPDF.getPhone();
		
		return 	nome + "  " + 
				placa + "  " + 
    			motoristaPDF.getData() + "\n" + 
				cidade + "  " + 
    			estado + "  " + 
				telefone ;
		
	}
	
	private String fechamento(double total, int size) {
		return "Peso Total: " + total + "Kg " + "\n" + "Numero de Caixotes = " + size; 
	}
	
	public boolean copyPDF() {
	    for ( int i = 0; i < letters.length; ++i ){
			drives[i] = new File(letters[i]);		    	
	        boolean pluggedIn = drives[i].canRead();
	        if ( pluggedIn ) 
	        {        	
	    		try 
	    		{
	    			if( diretorio == 2 ) 
	    			{
	    				File newDiretorio = new File(letters[i] + "\\" + this.data);
	    				newDiretorio.mkdir();
	    				Files.copy(Paths.get( caminho ), Paths.get( letters[i] + "\\" + this.data + "\\"+ nameArquivo ) );
	    			}
	    			else
	    			{
	    				Files.copy(Paths.get( caminho ), Paths.get( letters[i] + "\\" + nameArquivo ) );
	    			}
										
					//System.out.println("Drive "+letters[i]+" has been plugged in");					
					return true;
				} 
	    		catch (FileAlreadyExistsException e) 
	    		{					
					System.out.println("Arquivo j� existe no pendriver");
					return true;
				} catch (IOException e) {
					
					e.printStackTrace();
					return false;
				}
    
	        }
	        
	    }
	    return false;
	}
     
	public String getCaminho() 
	{
		return caminho;
	}
	
	public void criarDiretorioUSB() {
		
	}
	
	
}

//
//	            File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt");
//	              
//	            // renaming the file and moving it to a new location
//	            if(file.renameTo (new File("C:\\Users\\Mayank\\Desktop\\dest\\newFile.txt"))) {
//	                file.delete();
//	                System.out.println("File moved successfully");
//	            }
//	            else
//	            {
//	                System.out.println("Failed to move the file");
//	        	}     

//  private static final String command = "wmic logicaldisk get name";  
//        try {
//        	Process SerialNumberProcess = Runtime.getRuntime().exec(command);
//        	InputStreamReader ISR = new InputStreamReader(SerialNumberProcess.getInputStream());
//        	BufferedReader br = new BufferedReader(ISR);
//        	String items = br.readLine();    		
//			while(items != null) {	
//				System.out.println(items);
//				items = br.readLine();
//			}	
//		  	
//        	SerialNumberProcess.waitFor();
//        	br.close();
//        }catch (Exception e) {
//        	e.printStackTrace();
//
//        }


/*
document.open();
document.setPageSize(PageSize.A3);

document.newPage();
document.add(new Paragraph("Novo parágrafo na nova página"));

pageSize.setBackgroundColor(new java.awt.Color(0xFF, 0xFF, 0xDE));

Image figura = Image.getInstance("C:\\imagem.jpg");
document.add(figura);

*/
