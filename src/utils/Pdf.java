package utils;

import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JTable;

public class Pdf {
	public void print(JTable table, MessageFormat header) {
		try {
			MessageFormat footer = new MessageFormat("Page{0,number,integer}");
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void PrintFrameToPDF(File file) {
		try {
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(file));
			d.open();
	
			PdfContentByte cb = writer.getDirectContent();
			PdfTemplate template = cb.createTemplate(PageSize.A4.getWidth(),PageSize.A4.getHeight());
			cb.addTemplate(template, 0, 0);
	
			Graphics2D g2d = template.createGraphics(PageSize.A4.getW­idth(),PageSize.A4.getHeight());
			g2d.scale(0.4, 0.4);
	
			for(int i=0; i< this.getContentPane().getComponents().length; i++){
			Component c = this.getContentPane().getComponent(i);
			if(c instanceof JLabel || c instanceof JScrollPane){
			g2d.translate(c.getBounds().x,c.getBounds().y);
			if(c instanceof JScrollPane){c.setBounds(0,0,(int)PageSize.A4.getWidth()*2,(int)PageSize.A4.getHeight()*2);}
			c.paintAll(g2d);
			c.addNotify();
			}
			}
	
	
			g2d.dispose();
	
			d.close();
			} catch (Exception e) {
			System.out.println("­ERROR: " + e.toString());
			}
		}
		*/
}
