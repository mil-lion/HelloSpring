/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.model.entity;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * XML Адаптер для преобразования java.util.Date в XML и обратно
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String marshal(Date bt) throws Exception {
        return df.format(bt);
    }

    @Override
    public Date unmarshal(String vt) throws Exception {
        return df.parse(vt);
    }

}
