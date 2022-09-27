package aulas.example8.test;

import aulas.example8.main.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    void returnFailedInvoices(){
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 25);
        Invoice steve = new Invoice("Steve", 48);

        List<Invoice> invoices = Arrays.asList(mauricio, frank, steve);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");

        doThrow(new SAPException()).when(sap).send(franksInvoice);

        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();

        assertThat(failedInvoices).containsExactly(frank);

        SapInvoice mauriciosInvoice = new SapInvoice("Mauricio", 20, date + "Ma");
        verify(sap).send(mauriciosInvoice);

        SapInvoice stevesInvoice = new SapInvoice("Steve", 48, date + "St");
        verify(sap).send(stevesInvoice);



    }


    @ParameterizedTest
    @CsvSource({
            "Mauricio,Ma",
            "M,X"}
    )
    void sendToSapWithTheGeneratedId(String customer, String initialId) {
        Invoice mauricio = new Invoice(customer, 20);

        List<Invoice> invoices = Arrays.asList(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        //Instantiates an ArgumentCaptor with the type
        //of the object we are expecting to capture.
        ArgumentCaptor<SapInvoice> captor =
                ArgumentCaptor.forClass(SapInvoice.class);

        //Calls the verify method and passes the argument
        //captor as the parameter of the method.
        verify(sap).send(captor.capture());

        //The argument was already captured. Extract it.
        SapInvoice generatedSapInvoice =
                captor.getValue();

        String date = LocalDate.now().format(
                DateTimeFormatter.ofPattern("MMddyyyy"));

        //Uses a traditional assertion, ensuring
        //that the ID matches what is expected.
        assertThat(generatedSapInvoice).isEqualTo(new
                SapInvoice(customer, 20, date + initialId));
    }
/*
    @Test
    void sendToSap() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 20);

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
*/
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
        verify(sap, never()).send(any(SapInvoice.class));
    }

}


