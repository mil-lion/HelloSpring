/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.model.entity;

import jakarta.json.bind.adapter.JsonbAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON Адаптер для преобразования java.util.Date в JSON и обратно
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JsonbDateAdapter implements JsonbAdapter<Date, String> {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String adaptToJson(Date orgnl) throws Exception {
        return df.format(orgnl);
    }

    @Override
    public Date adaptFromJson(String adptd) throws Exception {
        return df.parse(adptd);
    }
    
}
