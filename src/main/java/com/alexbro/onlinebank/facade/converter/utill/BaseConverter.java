package com.alexbro.onlinebank.facade.converter.utill;

public interface BaseConverter<SOURCE, TARGET> {

    TARGET convert(SOURCE source);
}
