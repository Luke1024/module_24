package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ItemDao itemDao;

    @Test
    public void testInvoiceDaoSave() {
        //Given
        Product product1 = new Product("Good Product");
        Product product2 = new Product("Better Product");
        Item item1 = new Item(product1, new BigDecimal(5), 2, new BigDecimal(3));
        Item item2 = new Item(product2, new BigDecimal(10),2, new BigDecimal(6));
        Item item3 = new Item(product2, new BigDecimal(20),4,new BigDecimal(12));
        Invoice invoice = new Invoice("1");

        product1.setItemList(new ArrayList<>(Arrays.asList(item1)));
        product2.setItemList(new ArrayList<>(Arrays.asList(item2, item3)));
        invoice.setItems(new ArrayList<>(Arrays.asList(item1,item2,item3)));


        //When
        invoiceDao.save(invoice);
        productDao.save(product1);
        productDao.save(product2);
        itemDao.save(item1);
        itemDao.save(item2);
        itemDao.save(item3);

        Integer invoice_id = invoice.getId();
        Integer product1_id = product1.getId();
        Integer product2_id = product2.getId();
        Integer item1_id = item1.getId();
        Integer item2_id = item2.getId();
        Integer item3_id = item3.getId();

        System.out.println((invoice.getId()));
        System.out.println(invoice_id);

        //Then
        Assert.assertTrue(null != invoice_id);
        Assert.assertTrue(null != product1_id);
        Assert.assertTrue(null != product2_id);
        Assert.assertTrue(null != item1_id);
        Assert.assertTrue(null != item2_id);
        Assert.assertTrue(null != item3_id);
    }
}
