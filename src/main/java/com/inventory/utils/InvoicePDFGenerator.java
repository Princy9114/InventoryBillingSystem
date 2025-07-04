package com.inventory.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class InvoicePDFGenerator {
	public static void generateInvoice(OutputStream out, Map<String, Object> invoice, List<Map<String, Object>> items)
			throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
		Font tableFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		Paragraph title = new Paragraph("Invoice", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		document.add(new Paragraph("Invoice ID: " + invoice.get("invoice_id")));
		document.add(new Paragraph("Customer Name: " + invoice.get("customer_name")));
		document.add(new Paragraph("Date: " + invoice.get("date")));
		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.addCell("Product");
		table.addCell("Quantity");
		table.addCell("Unit Price");
		table.addCell("Total");
		/*
		 * for (Map<String, Object> item : items) {
		 * table.addCell(item.get("product_name").toString());
		 * table.addCell(item.get("quantity").toString());
		 * table.addCell(item.get("unit_price").toString());
		 * table.addCell(item.get("total_price").toString()); }
		 */
		for (Map<String, Object> item : items) {
		    table.addCell(String.valueOf(item.get("product_name")));
		    table.addCell(String.valueOf(item.get("quantity")));
		    table.addCell(String.valueOf(item.get("unit_price")));
		    table.addCell(String.valueOf(item.get("total_price")));
		}

		document.add(table);
		document.add(new Paragraph(" "));
		document.add(new Paragraph("GST: " + invoice.get("gst")));
		document.add(new Paragraph("Grand Total: " + invoice.get("grand_total")));

		document.close();
	}
}
