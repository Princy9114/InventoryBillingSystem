/*
 * // --- DownloadInvoiceServlet.java --- package com.inventory.servlet;
 * 
 * import com.inventory.utils.InvoicePDFGenerator; import
 * com.inventory.dao.BillingDAO;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import
 * java.io.IOException; import java.sql.*; import java.util.*;
 * 
 * @WebServlet("/DownloadInvoice") public class DownloadInvoiceServlet extends
 * HttpServlet { protected void doGet(HttpServletRequest request,
 * HttpServletResponse response) throws ServletException, IOException { String
 * invoiceId = request.getParameter("invoice_id");
 * 
 * try { Class.forName("com.mysql.cj.jdbc.Driver"); Connection conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem",
 * "root", "Princy@123");
 * 
 * BillingDAO dao = new BillingDAO(conn); Map<String, Object> invoice =
 * dao.getInvoiceById(invoiceId); List<Map<String, Object>> items =
 * dao.getInvoiceItems(invoiceId);
 * 
 * response.setContentType("application/pdf");
 * response.setHeader("Content-Disposition", "attachment; filename=invoice_" +
 * invoiceId + ".pdf");
 * 
 * InvoicePDFGenerator.generateInvoice(response.getOutputStream(), invoice,
 * items); conn.close(); } catch (Exception e) { e.printStackTrace();
 * response.getWriter().println("Error: " + e.getMessage()); } } }
 */

package com.inventory.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.inventory.dao.BillingDAO;
import com.inventory.utils.DBConnection;
import com.inventory.utils.InvoicePDFGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/DownloadInvoice")
public class DownloadInvoiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String invoiceId = request.getParameter("invoice_id");

        try (Connection conn = DBConnection.getConnection()) {
            BillingDAO dao = new BillingDAO(conn);
            Map<String, Object> invoice = dao.getInvoiceById(invoiceId);
            List<Map<String, Object>> items = dao.getInvoiceItems(invoiceId);
            InvoicePDFGenerator.generateInvoice(response.getOutputStream(), invoice, items);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_" + invoiceId + ".pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("INVOICE #" + invoiceId, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph("Customer: " + invoice.get("customer_name")));
            document.add(new Paragraph("Date: " + invoice.get("date")));
            document.add(new Paragraph("GST: " + invoice.get("gst") + "%"));
            document.add(new Paragraph("Grand Total: ₹" + invoice.get("grand_total")));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell("Product");
            table.addCell("Quantity");
            table.addCell("Unit Price");
            table.addCell("Total");
            table.addCell("Date");

            for (Map<String, Object> item : items) {
                table.addCell(item.get("product_name").toString());
                table.addCell(item.get("quantity").toString());
                table.addCell(item.get("unit_price").toString());
                table.addCell(item.get("total_price").toString());
                table.addCell(item.get("date").toString());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
