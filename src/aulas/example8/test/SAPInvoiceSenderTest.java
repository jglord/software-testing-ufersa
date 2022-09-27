package aulas.example8.test;

import aulas.example8.main.Invoice;
import aulas.example8.main.InvoiceFilter;
import aulas.example8.main.SAP;
import aulas.example8.main.SapInvoice;
import aulas.example8.main.SAPInvoiceSender;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import java.util.List;
import static java.util.Collections.emptyList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class SAPInvoiceSenderTest {
    // continues

    private InvoiceFilter filter =
            mock(InvoiceFilter.class);
    private SAP sap = mock(SAP.class);

    //Passing the stub and the mock to the class
    private SAPInvoiceSender sender =
            new SAPInvoiceSender(filter, sap);
    //continues

    @Test
    void sendToSap() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 99);

        //Setting up the InvoiceFilter stub. It will return two
        //invoices whenever lowValueInvoices() is called.
        List<Invoice> invoices = Arrays.asList(mauricio, frank);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        //Calling the method under test, knowing
        //that these two invoices will be sent to SAP
        sender.sendLowValuedInvoices();

        //Ensuring that the send method
        //was called for both invoices
        verify(sap).send(mauricio);
        verify(sap).send(frank);
        verify(sap).send(mauricio);
        verify(sap).send(frank);
        //Verifies that the send method was
        //called precisely twice for any invoice.
        verify(sap, times(2)).send(any(Invoice.class));
        //Verifies that the send method was called once
        //for “mauricio” and “frank” invoices.
        verify(sap, times(1)).send(mauricio);
        verify(sap, times(1)).send(frank);

    }
    //case where there are no low-valued invoices.
    @Test
    void noLowValueInvoices() {
        //Now, the stub will return an empty list.
        List<Invoice> invoices = emptyList();
        when(filter.lowValueInvoices()).
                thenReturn(invoices);
        sender.sendLowValuedInvoices();
        //ensuring that to send() was not invoked for
        //any invoice.
        verify(sap, never()).send(any(Invoice.class));
    }

}


